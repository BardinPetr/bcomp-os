from math import *

def encode(x: str, encoding: str = "ascii") -> bytes:
  enc = x.encode(encoding)
  return enc
  

def to_bin(x: bytes, word_bits: int = 8) -> str:
  full = bin(int(x.hex(), 16))[2:]
  cnt = ceil(len(full)/word_bits)
  full = full.zfill(word_bits * cnt)
  return full, [full[word_bits*i:word_bits*(i+1)] for i in range(cnt)]


s = "testтест"
for e in [to_bin(encode(s, i))[1] for i in ["koi8-r", "utf-8", "utf-16"]]:
  print(*e)
