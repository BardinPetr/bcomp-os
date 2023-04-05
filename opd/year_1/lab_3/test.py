def inv(x, cnt=16):
  mask = ((1 << cnt) - 1)
  return mask & (~x)

def neg(x, cnt=16):
  return inv(x, cnt) + 1



b = lambda x: f"{x:016b}"
h = lambda x: hex(x)
B = lambda x: print(b(x))
H = lambda x: print(h(x))


a = 0xF7
# an = neg(a, 8)
# print(an)
# B(an)
B(0xFD)
B(neg(0xFF))
# B(0x04BC - an)
# H(0x04BC - an)

13213 - 1234
# 0011 0011 1001 1101
# 0000 0100 1101 0010
# 0010 1110 1100 1011
# 1101 0001 0011 0101
