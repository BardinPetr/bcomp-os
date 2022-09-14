from functools import reduce
from math import trunc, isclose

from logs import process_logs_to10, process_logs_from10
from tools import N2L, verify_cs, round_in_cs


def convert(source_num: str, from_s: int, to_s: int,
            alphabet: list[str] = None,
            dest_precision=None,
            is_zero_precision=1e-8,
            sep='.'):
    source_num = source_num.replace(',', sep)

    logs_to10 = None
    logs_from10 = None

    if alphabet is None:
        alphabet = N2L
    rev_alph = {j: i for i, j in enumerate(N2L)}

    if not verify_cs(source_num, from_s, rev_alph):
        raise ValueError("Invalid source counting system")

    num_10 = 0
    if from_s != 10:
        num = source_num
        dec_len = len(num)
        frac_len = 0
        if ',' in num or '.' in num:
            dec_len = num.find(sep)
            frac_len = len(num) - dec_len - 1
            num = num.replace(sep, '')

        num = [rev_alph[i] for i in num]
        powers = list(range(dec_len - 1, -frac_len - 1, -1))
        res_powers = [pow(from_s, i) for i in powers]

        num_10 = reduce(lambda acc, x: acc + x[0] * x[1], zip(num, res_powers), 0)

        logs_to10 = (num, powers, res_powers)
    else:
        num_10 = float(source_num)

    if to_s == 10:
        num_10 = round(num_10, dest_precision)
        res = num_10
    else:
        logs_from10 = ([], [])
        dec_rem = []
        src = trunc(num_10)
        while src >= 1:
            cur = src % abs(to_s)
            dec_rem.append(cur)
            logs_from10[0].append((src, cur, alphabet[cur], src // to_s))
            src //= to_s
        dec_rem.reverse()

        frac_rem = []
        if not isclose(num_10 - trunc(num_10), 0, abs_tol=is_zero_precision):
            src = num_10 - trunc(num_10)
            iters = 0
            while not isclose(src, 0, abs_tol=is_zero_precision) and \
                    (dest_precision is None or iters <= dest_precision):  # make one digit more for rounding
                tmp = src * to_s
                dec = trunc(tmp)
                frac_rem.append(dec)
                logs_from10[1].append((src, tmp, alphabet[dec], tmp - dec))
                src = tmp - dec
                iters += 1

        # If we have dest_precision, so we need to check next digit after last to obey mathematical rounding rules
        if dest_precision is not None and len(frac_rem) > 0 and len(frac_rem) > dest_precision:
            dec_rem, frac_rem = round_in_cs(dec_rem, frac_rem, to_s)

        dec_rem = [alphabet[i] for i in dec_rem]
        frac_rem = [alphabet[i] for i in frac_rem]

        res = ('0' if len(dec_rem) == 0 else ''.join(dec_rem)) + \
              ('' if len(frac_rem) == 0 else f'{sep}{"".join(frac_rem)}')

    return res, (source_num, from_s, num_10, 10, res, to_s), logs_to10, logs_from10


def run(val, src, dst):
    res, log_conf, logs_t10, logs_f10 = convert(val, int(src), int(dst), dest_precision=5)
    print(f"Перевод числа {val} из СС{src} в СС{dst}")
    print(process_logs_to10(logs_t10, log_conf))
    print()
    print(process_logs_from10(logs_f10, log_conf))


if __name__ == "__main__":
    data = [[], ['34106', '10', '15'], ['16116', '7', '10'], ['21104', '5', '15'], ['51,96', '10', '2'],
            ['41,6C', '16', '2'],
            ['14,67', '8', '2'], ['0,001101', '2', '16'], ['0,001011', '2', '10'], ['1B,08', '16', '10'],
            ['42 10', '10', 'Fib'], ['147', '-10', '10'], ['1000010101', 'Fib', '10'], ['1678', '-10', '10']]

    for i in range(1, 14):
        try:
            print("\n\n", i)
            run(*data[i])
        except:
            pass
