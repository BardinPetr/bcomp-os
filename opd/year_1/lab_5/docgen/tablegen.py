import re

out_file = open("mnemtable.tex", "w")

code = open("short.basm", "r").read()
code = re.sub("\s{2:}", " ", code)
code = re.sub("(\w+:)\s*\n", r"\1 ", code)
code = [s for i in code.split('\n') if len(s := i.strip()) > 0]

opc = {int(i.split()[0],16): i.split()[1] for i in open("memory.txt", "r").read().split('\n')}

pos = 0
for m in code:
  if 'ORG' in m:
    pos = int(m.split()[1][2:], 16)
    continue
  
  res = ""

  label = None
  if ":" in m:
    split = [i.strip() for i in m.split(":")]
    label = ' '.join(split[:-1])
    m = split[-1].strip()

    res += "\\sbf{" + label + ":} \\\\"

  res += m

  if '\\' in res:
    res = "\\mc{" + res + "}"

  res = re.sub(r"([\$&\_\#])", r"\\\1", res)

  print(f"{pos:X} & {opc[pos]} & {res} & \\tre", file=out_file)
  pos += 1