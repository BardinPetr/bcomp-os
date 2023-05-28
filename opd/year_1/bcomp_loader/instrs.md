0000 1111 1000 0000 (0F00) SP -> AC : RSP
0000 1111 1100 0000 (0F80) AC -> SP : WSP

1111 11YY XXXX XXXX (FCXX - FFXX) IMM8: SEXT(CR) -> BR
1111 1100 XXXX XXXX (FCXX) BR + SP -> 


Addressed commands format
0 0MMM $L
8 1000 &N
9 1001 (&N)
A 1010 (&N)+
B 1011 -(&N)
C 1100 (L)
D 1101 (L)+
E 1110 L 
F 1111 #N

FCALL ($L)

N - number, L - label


fix:
8 -> C
A -> D
B -> x
C -> 8
E -> E