#!/usr/bin/python

import sys
import re

DEBUG = False

CODE_BEGIN = 0x30

ESCAPE_TABLE = { repr(i)[1:-1]: i for i in ['\\', '\n', '\t', '\b', '\f', '\r', '\v', '\'', '\"'] }
def encode_string(text: str, encoding="koi8-r", little_endian=True):
  for txt, escape in ESCAPE_TABLE.items():
    text = text.replace(txt, escape)

  enc = bytearray(text.encode(encoding))
  enc += b'\0' * (2 - len(text) % 2) # add EOS byte(-s)

  words = (enc[i:i+2][::(-1 if little_endian else 1)] for i in range(0, len(enc), 2))
  return [f"0x{w[0]:02X}{w[1]:02X}" for w in words]


def preprocess_strings(data: str):
  for match in re.finditer("(\w+): +WORD \"([^\"]*)\"", data):
    name, text = match.groups()
    encoded_words = \
      f"""{name}: WORD {', '.join(encode_string(text))}
      {name}_PTR: WORD ${name}"""
    data = data.replace(match.group(), encoded_words)
  return data


def preprocess_includes(data: str):
  for match in re.finditer(r"^INCLUDE (.*)\n", data, re.M):
    path = match.groups()[0]
    # print(match, match.group())
    with open(path, "r") as included_file:
      text = included_file.read()

      if len(re.findall(r"(?:ORG|INCLUDE)\s+0x", text)):
        sys.stderr.write("Use of ORG in includes is prohibited!")
        exit(1)
      
      include = \
f"""\n\n; {'-'*20}INCLUDED FROM {path}{'-'*20}
{text}\n
; {'-'*20}INCLUDED END {path}{'-'*20}"""
      data = data.replace(match.group(), include)

  return data


def preprocess_stack_names(data: str):
  output_str = list(data)
  for function in re.finditer(r"; func (\w+):", data, re.M):
    name, = function.groups()
    if DEBUG: print("func: ", name)
    
    func_start = data.find(f"; func {name}:")

    body_pos = [re.search(f"^{name}:", data, re.M),
                re.search(f";\s+end\s+func\s+{name}(?=\s)", data)]
    body_pos = [i.span() for i in body_pos if i is not None]
    if len(body_pos) != 2:
      continue

    body_pos = [body_pos[0][0], body_pos[1][1]]

    func_header = data[func_start:body_pos[0]]
    func_body = data[body_pos[0]:body_pos[1]]

    cur_stack_id = 0
    upper_than_ret = 0
    for i in re.finditer(r"; *&(\d*): *(\w+)", func_header):
      number, name = i.groups()

      if 'ret' in name or upper_than_ret > 0:
        upper_than_ret += 1

      # autonumbering or fixed
      if len(number) == 0:
        number = cur_stack_id
      else:
        number = int(number)
        cur_stack_id = number

      if DEBUG: print(name, number)
      
      for sub in re.finditer(f"&{name}(\+\d+)?(?=[\s\)])", func_body):
        shift, = sub.groups()
        txt = sub.group()
        num_delta = 0
        if shift is not None:
          num_delta = int(shift[1:])
        # print(sub, fr"{txt}(?=\s)", shift, f"&{number + num_delta}")
        func_body = re.sub(f"{re.escape(txt)}(?=[\s\)])", f"&{number + num_delta}", func_body)
      
      cur_stack_id += 1

    cur_stack_id -= upper_than_ret # working with local variables only
    # generate prologue and epilogue
    
    if cur_stack_id > 127:
      sys.stderr.write(f"func {name} have {cur_stack_id} local vars, hovewer SPADD supports up to 127 only")
      exit(1)

    func_body = func_body\
      .replace(f"APUSH", f"SPADD #{256 - cur_stack_id}" if cur_stack_id > 0 else "")\
      .replace(f"APOP", f"SPADD #{cur_stack_id}" if cur_stack_id > 0 else "")

    output_str = output_str[:body_pos[0]] + list(func_body) + output_str[body_pos[1]+1:] 
    data = ''.join(output_str)

  return data


def generate_headers(code: str) -> tuple[str, str]:
  clean_code = re.sub("EXPORT ", "", code)
  export_funcs = map(lambda x: x.group(1), re.finditer(r"EXPORT (\w+):", code))
  table = [f"{i}_PTR: WORD ${i}" for i in export_funcs]  
  table = '\n'.join(table)

  return clean_code, table

def insert_table(code, table):
  return code.replace('FUNCTABLE', table)

def build_abi(code, table):
  labels = dict()
  pos = 0
  for line in code.split('\n'):
    line = line.strip()
    
    if not len(line) or line.startswith(';'): continue

    if line.lower().startswith('org'):
      val = re.findall(r'(?:0[xd])?([a-f\d]+)', line, re.IGNORECASE)[0]
      try:
        pos = int(val, 16)
      except:
        pos = int(val)
      continue

    m = re.match(r'(\w+):(.*)', line)
    if m is not None:
      label, instr = m.groups()
      instr = instr.lower()
      labels[label] = pos
      
      if not instr: continue

      if 'word' in instr:
        if ',' in instr:
          pos += instr.count(',')
        if 'dup' in instr:
          pos += int(re.findall(r'word +(\d+) +dup', instr)[0]) - 1

    pos += 1
      
  if DEBUG:
    for i, j in sorted(labels.items(), key=lambda x: x[1]):
      print(f"{i}: {j:03X}")

  for label, addr in labels.items():
    table = re.sub(f'\${label}(?!\w)', f'0x{addr:X}', table)

  return table
