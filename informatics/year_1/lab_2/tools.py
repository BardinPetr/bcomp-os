from typing import Union

SUPERSCRIPT = str.maketrans("0123456789-", "⁰¹²³⁴⁵⁶⁷⁸⁹⁻")
SUBSCRIPT = str.maketrans("0123456789-", "₀₁₂₃₄₅₆₇₈₉₋")
XOR_SP = " ⊕ "


def txt_sub(text: Union[int, float, str]) -> str:
    text = str(text)
    return text.translate(SUBSCRIPT)


def txt_sup(text: Union[int, float, str]) -> str:
    text = str(text)
    return text.translate(SUPERSCRIPT)


def to_bin_list(a: Union[int, str]) -> list[int]:
    if isinstance(a, int):
        return list(map(int, bin(a)[2:]))
    elif isinstance(a, str):
        if not set(a).issubset({'0', '1'}):
            raise ValueError("Should be an string of 0 or 1 without any other symbols. Example: 01010010101")
        return [int(i) for i in a]
    else:
        raise TypeError("Argument type should be str")


def from_list_to_str(a: list[int]) -> str:
    return ''.join(map(str, a))


def from_list_to_int(a: list[int]) -> int:
    return int(from_list_to_str(a), 2)
