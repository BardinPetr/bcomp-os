#!/usr/bin/python

import sys
import re

ESCAPE_TABLE = {
  repr(i)[1:-1]: i for i in ['\\', '\n', '\t', '\b', '\f', '\r', '\v', '\'', '\"']
}
def encode_string(text: str, encoding="koi8-r", little_endian=True):
  for txt, escape in ESCAPE_TABLE.items():
    text = text.replace(txt, escape)

  enc = bytearray(text.encode(encoding))
  enc += b'\0' * (2 - len(enc) % 2) # add EOS byte(-s)

  words = (enc[i:i+2][::(-1 if little_endian else 1)] for i in range(0, len(text), 2))
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

      if "ORG" in text:
        sys.stderr.write("Use of ORG in includes is prohibited!")
        exit(1)
      
      include = \
        f"""\n\n
; {'-'*20}INCLUDED FROM {path}{'-'*20}
{text}\n
; {'-'*20}INCLUDED END {path}{'-'*20}
        """
      data = data.replace(match.group(), include)

  return data


def preprocess_stack_names(data: str):
  output_str = list(data)
  for function in re.finditer(r"; func (\w+)", data, re.M):
    name, = function.groups()
    
    func_start = function.span()[0]

    body_pos = [re.search(f"^{name}:", data, re.M),
                re.search(f";\s+end\s+func\s+{name}", data)]
    body_pos = [i.span()[0] for i in body_pos if i is not None]
    if len(body_pos) != 2:
      continue

    func_header = data[func_start:body_pos[0]]
    func_body = data[body_pos[0]:body_pos[1]]

    cur_stack_id = 0
    has_ret = False
    for i in re.finditer(r"; *&(\d*): *(\w+)", func_header):
      number, name = i.groups()
      if 'ret' in name:
        has_ret = True

      # autonumbering or fixed
      if len(number) == 0:
        number = cur_stack_id
        cur_stack_id += 1
      else:
        number = int(number)
        cur_stack_id = number
      func_body = func_body.replace(f"&{name}", f"&{number}")

    if has_ret:
      cur_stack_id -= 1 # not pushing additional ret addr
    # generate prologue and epilogue
    func_body = func_body\
      .replace(f"PUSHA", "  PUSH\n" * cur_stack_id)\
      .replace(f"POPA", "  POP\n" * cur_stack_id)

    output_str[body_pos[0]:body_pos[1]] = func_body

  return ''.join(output_str)


def main():
  path = sys.argv[1]

  with open(path, "r") as file:
    data = file.read()
    data = preprocess_includes(data)
    data = preprocess_strings(data)
    data = preprocess_stack_names(data)
    print(data)


if __name__ == "__main__":
  main()
