from math import ceil
from typing import Union

SUPERSCRIPT = str.maketrans("0123456789-", "⁰¹²³⁴⁵⁶⁷⁸⁹⁻")
SUBSCRIPT = str.maketrans("0123456789-", "₀₁₂₃₄₅₆₇₈₉₋")


def txt_sub(text: Union[int, float, str]) -> str:
    text = str(text)
    return text.translate(SUBSCRIPT)


def txt_sup(text: Union[int, float, str]) -> str:
    text = str(text)
    return text.translate(SUPERSCRIPT)


N2L = [chr(i) for i in [*range(ord('0'), ord('9') + 1), *range(ord('A'), ord('Z') + 1)]]


def verify_cs(number: str, cs: int, alphabet: dict[str, int]):
    return all(map(lambda x: x in '.,' or (x in alphabet) and alphabet[x] < abs(cs), number))


def round_in_cs(dec_num: list[int], frac_num: list[int], system: int, cnt: int = None):
    system = abs(system)

    if cnt is None:
        cnt = len(frac_num) - 1

    dec = dec_num.copy()
    frac = frac_num.copy()

    if cnt >= len(frac):
        raise ValueError("Invalid rounding count")

    last = frac[cnt]
    frac = frac[:cnt]
    if last >= ceil(system / 2):
        frac[-1] += 1
        for i in range(len(frac) - 1, -1, -1):
            if frac[i] == system:
                frac[i] = 0
                if i == 0:
                    dec[-1] += 1
                else:
                    frac[i - 1] += 1
            else:
                break

        for i in range(len(dec) - 1, -1, -1):
            if dec[i] == system:
                dec[i] = 0
                if i == 0:
                    dec.insert(0, 1)
                else:
                    dec[i - 1] += 1
            else:
                break
    return dec, frac
