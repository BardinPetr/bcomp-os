from tools import *

# LOW_LIMIT = 0xFF44
# SUB_VAL = 0x00CA
# def func():
#   AC = MEM(SP + 1)

#   if AC >= 0 or AC < LOW_LIMIT:  
#     AC = 4 * AC + MEM(SP + 1) - SUB_VAL
#   else:
#     AC = LOW_LIMIT
 
#   MEM(SP + 1) = AC
#   return

# Z = 0
# Y = 0
# X = 0

# def main():
#   AC = 0
#   SUM = 0

#   AC = Y 
#   AC = func(AC)
#   AC -= 1
#   AC -= SUM
#   SUM = AC
#   AC = Z
#   AC -= 1
#   AC = func(AC)
#   AC -= 1
#   AC += SUM
#   SUM = AC
#   AC = X
#   AC = func(AC)
#   AC += SUM
#   SUM = AC
#   return SUM



def func(x):
  if x < -188 or x >= 0:
    return 5 * x - 202 
  else:
    return -188

def main(X, Y, Z):
  SUM = func(X) + func(Y) + func(Z - 1) - 2
  return SUM


def M(x, y, z):
  print(*nv_map(['x', 'y', 'z'], [x, y, z]), sep="\n")

  fx = [func(i) for i in [x, y, z-1]]
  print(*nv_map(['f(x)', 'f(y)', 'f(z-1)'], fx), sep="\n")
  
  print(nv("res", main(x, y, z)))

  print(*nv_map(
    ["1 ST sum", "2 ST sum"],
    [fx[1] - 1, fx[1] + fx[2] - 2]
  ), sep="\n")

M(23, -99, -250)

