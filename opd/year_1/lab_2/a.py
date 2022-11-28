from re import A
from Crypto.Protocol.KDF import PBKDF2
from Crypto.Hash import SHA512
from Crypto.Cipher import AES


salt = "d5fg4df5sg4ds5fg45sdfg4".encode('ascii')
pw = b""

enc = open('/home/petr/Downloads/cinema/VenomLetBeCarn_FTR-X_OV-RU_CM_P_3x190.xte', 'rb').read()
dec = open('/home/petr/Downloads/cinema/res.xtt', 'wb')

keys = PBKDF2(pw, salt, 64, count=1000, hmac_hash_module=SHA512)

aes = AES.new(keys[:16], AES.MODE_CBC, iv=keys[16:])

# dec.write(text)