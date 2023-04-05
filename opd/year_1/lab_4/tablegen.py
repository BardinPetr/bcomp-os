import re
code = open("../lab_5/short.basm", "r").read()
code = re.sub("\s{2:}", " ", code)
code = re.sub("(\w+:)\s*\n", r"\1 ", code)
code = [s for i in code.split('\n') if len(s := i.strip()) > 0]

opc = {int(i.split()[0],16): i.split()[1] for i in open("task.txt", "r").read().split('\n')}

res = open("../lab_5/mnemtable.tex", "w")
import pprint
pprint.pprint(code)
pos = 0
for m in code:
  if 'ORG' in m:
    pos = int(m.split()[1][2:], 16)
    continue
  
  if ';' in m:
    mnem, info = [i.strip() for i in m.split(';')]
    print(mnem, "##", info, "L", mnem.split())
    mn_parts = mnem.split()
    info = f"{mn_parts[-2]} {info}"      
    m = info + "\\\\ \\si{" + ' '.join(mn_parts[-2:]) + "}"
    if len(mn_parts) == 3:
      m = "\\sbf{" + mn_parts[0] + "} \\\\" + m
  
  if '\\' in m:
    m = "\\mc{" + m + "}"

  m = re.sub(r"([\$&\_])", r"\\\1", m)

  print(f"{pos:X} & {opc[pos]} & {m} & \\tre", file=res)
  pos += 1