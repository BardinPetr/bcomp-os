def to_n(x, hx=True):
  res = 0xFFFF & ((~abs(x) + 1) if x < 0 else x)
  return f"0x{res:04X}" if hx else res

def from_n(x):
  return -(0xFFFF & (~x + 1)) if x & 0x8000 else x

def nv(name, val):
  return f"{name} \t = \t{val}\t= {to_n(val)}"

def nv_map(names, vals):
  return [nv(i, j) for i, j in zip(names, vals)]
