from functools import lru_cache
from math import ceil
from typing import Union

SUPERSCRIPT = str.maketrans("0123456789-", "⁰¹²³⁴⁵⁶⁷⁸⁹⁻")
SUBSCRIPT = str.maketrans("0123456789-", "₀₁₂₃₄₅₆₇₈₉₋")

FIB = 'fib'


def txt_sub(text: Union[int, float, str]) -> str:
    text = str(text)
    return text.translate(SUBSCRIPT)


def txt_sup(text: Union[int, float, str]) -> str:
    text = str(text)
    return text.translate(SUPERSCRIPT)


N2L = [chr(i) for i in [*range(ord('0'), ord('9') + 1), *range(ord('A'), ord('Z') + 1)]]
L2N = {j: i for i, j in enumerate(N2L)}
SEP = '.'


def verify_cs(number: str, cs: Union[int, FIB], alphabet: dict[str, int]):
    if cs == FIB:
        return all(map(lambda x: x.isdigit(), number)) and ('11' not in number)
    return all(map(lambda x: x in '.,' or (x in alphabet) and alphabet[x] < abs(cs), number))


def join_number(dec, frac):
    if len(dec) == 0:
        dec = [0]
    if not isinstance(dec[0], str):
        dec = [N2L[i] for i in dec]

    if len(frac) == 1:
        if frac[0] in ['0', 0]:
            frac = []
    elif len(frac) > 0 and not isinstance(frac[0], str):
        frac = [N2L[i] for i in frac]

    return ('0' if len(dec) == 0 else ''.join(dec)) + \
           ('' if len(frac) == 0 else f'.{"".join(frac)}')


def partial_round_in_cs(dec_num: list[int], frac_num: list[int], system: Union[int, FIB], cnt: int = None) -> tuple[list[int], list[int]]:
    if system == FIB:
        return dec_num, frac_num

    system = abs(system)

    if cnt is None:
        cnt = len(frac_num) - 1

    dec = dec_num
    frac = frac_num

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


def round_in_cs(num: str, system: Union[int, FIB], cnt: int = None):
    parts = num.split('.')
    if len(parts) == 1:
        parts.append("")
    dec, frac = parts

    if cnt is not None and len(frac) > 0 and len(frac) > cnt:
        dec = [L2N[i] for i in dec]
        frac = [L2N[i] for i in frac]
        dec, frac = partial_round_in_cs(dec, frac, system, cnt)

    res = join_number(dec, frac)
    return res


@lru_cache(None)
def get_fib(n):
    return 1 if n < 2 else get_fib(n - 1) + get_fib(n - 2)


def pad_to(val: str, target_len: int, pad_with: str = '0', left=True):
    pad = (pad_with * max(0, target_len - len(val)))
    return (pad + val) if left else (val + pad)


class InvalidSystem(Exception):
    pass
