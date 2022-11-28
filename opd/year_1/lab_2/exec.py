from re import M


mask = ((1 << 16) - 1)

def inv(x):
  return mask & (~x)

def neg(x):
  return inv(x) + 1



q = lambda x: f"{x:016b}"
p = lambda x: print(q(x))

# p(2**14)
# p(2**14-1)
# p(2**15)
# p(neg(2**14)-1)
# p(neg(2**14))
# p(neg(2**13))


X = 0xAEA6  # 1010111010100110
Y = 0xBEEF  # 1011111011101111
Z = 0x8000  # 1000000000000000

p(X)
p(Y)
p(Z)

print(X, Y, Z)


AC-=M
AC = AC + (~DR + 1)


# AC | X = inv( inv(AC) & inv(X) )
# inv( 1111111111111111 & 0101000101011001 )
# inv( 101000101011001 = 5159 )
p(inv(0))
p(inv(X))
p(inv(0) & inv(X))

print("#")

T = X & Y # 1010111010100110
# ~T      = 0101000101011001
# ~T+1=-T = 0101000101011010


# Z=1000000000000000     Z - T

# Z + T
#     1000000000000000
#     0101000101011010
# R = 1101000101011010 = -11942 = 

print("@@", neg(0xd15a))
R = Z + neg(T)
p(R)
print(neg(R))
print(hex(R))

p(0b0101000101011010 + 0b1000000000000000)

# p(neg(T) - 1)
# p(neg(T))
# print(neg(Z))
# print(0b1010111010100110)
# print(neg(0b1010111010100110))
# print(T)
# print(f"{T:b} {hex(T)} ")

