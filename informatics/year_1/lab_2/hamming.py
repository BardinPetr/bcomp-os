import itertools as it
from functools import reduce
from math import log2, ceil

from tools import *


def encode(input_data: list[int]) -> tuple[list[int], int, int]:
    if not isinstance(input_data, list) or \
            len(input_data) < 1 or \
            any(map(lambda x: x not in [0, 1], input_data)):
        raise ValueError("Not a valid sequence to encode")

    data = input_data.copy()
    n = len(data)
    cor_n = get_minimum_corr_count(n)

    for i in range(cor_n):
        data.insert(pow(2, i) - 1, 0)

    for i in range(cor_n):
        pos = _get_data_pos_for_corr(i, len(data))
        data[pow(2, i) - 1] = _calc_parity(data, pos)

    return data, n + cor_n, n


def decode(encoded: list[int], gen_logs=False) -> tuple[list[int], int, str]:
    if not isinstance(encoded, list) or \
            len(encoded) < 3 or \
            any(map(lambda x: x not in [0, 1], encoded)):
        raise ValueError("Not a valid sequence to encode")

    logs = ""
    n = len(encoded)
    cor_n = ceil(log2(n + 1))

    if gen_logs:
        logs = f'Исходное сообщение A={from_list_to_str(encoded)}{txt_sub("2")}\n' \
               f'Количество бит: информационных - {n - cor_n}, коррекции - {cor_n}\n'

        logs += gen_explanatory_table(encoded, cor_n)
        logs += f'Биты коррекции: {", ".join(f"A{txt_sub(i)}" for i in range(n) if not i & (i + 1))}\n' \
                f'Биты информации: {", ".join(f"A{txt_sub(i)}" for i in range(n) if i & (i + 1))}\n'

    positions = [list(_get_data_pos_for_corr(i, n)) for i in range(cor_n)]
    syndrome = [_calc_parity(encoded, pos) for pos in positions[::-1]]

    if gen_logs:
        logs += 'Синдром S\n'
        logs += '\n'.join(
            f'S{txt_sub(i)} = '
            f'{XOR_SP.join([f"A{txt_sub(pos)}" for pos in p])} = '
            f'{XOR_SP.join([str(encoded[pos]) for pos in p])} = '
            f'{s}'
            for i, p, s in zip(range(cor_n), positions, syndrome[::-1])
        )

    err_pos = from_list_to_int(syndrome)
    if gen_logs:
        logs += f"\n{from_list_to_str(syndrome)}{txt_sub(2)} = {err_pos}\n"

    err_pos -= 1

    if err_pos != -1:
        if gen_logs:
            logs += f"Ошибка в бите {err_pos}: {encoded[err_pos]} вместо {1 - encoded[err_pos]} \n"
        encoded[err_pos] = 1 - encoded[err_pos]
    elif gen_logs:
        logs += f"Ошибки нет \n"

    decoded = [v for i, v in enumerate(encoded) if i & (i + 1)]

    if gen_logs:
        logs += f'Исправленное сообщение B = {from_list_to_str(encoded)}{txt_sub(2)}\n' \
                f'Содержание сообщения: I = ' \
                f'{" ".join(f"B{txt_sub(i)}" for i in range(n) if i & (i + 1))} = ' \
                f'{from_list_to_str(decoded)}{txt_sub(2)}\n'

    return decoded, err_pos, logs


def get_minimum_corr_count(length: int) -> int:
    # 2^r >= r + length + 1
    corr_min = ceil(log2(length))
    while pow(2, corr_min) < (corr_min + length + 1):
        corr_min += 1
    return corr_min


def get_redundancy_coef(info_length, corr_length):
    return corr_length / (info_length + corr_length)


def _calc_parity(data: list[int], positions) -> int:
    return reduce(lambda acc, i: acc ^ data[i], positions, 0)


def _get_data_pos_for_corr(parity_num: int, max_cnt=None):
    parity = pow(2, parity_num)
    starts = it.count(parity - 1, parity * 2)
    poss = map(lambda x: it.islice(it.count(x), parity), starts)
    pos = it.chain.from_iterable(poss)
    if max_cnt is None:
        return pos
    return it.takewhile(lambda x: x < max_cnt, pos)


def gen_explanatory_table(data: list[int], corr_cnt: int) -> str:
    def fmt(x): return '{:4d}'.format(x)

    n = len(data)
    poss = [list(_get_data_pos_for_corr(i, n)) for i in range(corr_cnt)]
    res = f"№ {''.join(map(fmt, range(n)))}\n" \
          f"T {''.join('{:>4s}'.format(('i' if i & (i + 1) else 'r') + txt_sub(i)) for i in range(n))}\n" \
          f"A {''.join(map(fmt, data))}\n"
    res += '\n'.join(
        f"S{txt_sub(row_n)}{''.join('   ' + ('X' if i in row else ' ') for i in range(n))}"
        for row_n, row in enumerate(poss)
    )
    res += '\n'
    return res
