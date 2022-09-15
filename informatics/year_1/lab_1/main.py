from math import trunc, isclose, log, modf

from logs import *
from tools import *


def convert_from_fib(num: str) -> [str, dict]:
    if not verify_cs(num, FIB, N2L):
        raise ValueError("Not a Fibonacci")

    fib_numbers = [(i[0] + 1) for i in enumerate(num[::-1]) if i[1] == '1']
    fib = [get_fib(i) for i in sorted(fib_numbers, reverse=True)]  # reverse for cache speedup
    res = str(sum(fib))

    logs = {
        "mode":    LOG_MODE_FROM_FIB,
        "in":      num,
        "in_s":    FIB,
        "out":     res,
        "out_s":   10,
        "fib_ids": fib_numbers,
        "fib_val": sorted(fib)
    }

    return res, logs


def convert_to_fib(num: str) -> [str, dict]:
    res = ''
    num = float(num)

    fibs = [1]
    i = 2
    while fibs[-1] < num:
        fibs.append(get_fib(i))
        i += 1
    fibs = fibs[-2::-1]
    res = ''

    cur = num
    for i, val in enumerate(fibs):
        if val <= cur:
            cur -= val
            res += '1'
        else:
            res += '0'

    logs = {
        "mode":  LOG_MODE_TO_FIB,
        "in":    str(num),
        "in_s":  10,
        "out":   res,
        "out_s": FIB,
        "fibs":  fibs
    }

    return res, logs


def convert_to_10(src_num: str, from_s: Union[int, FIB]) -> [str, dict]:
    logs = {
        "mode":  LOG_MODE_TO_10,
        "in":    src_num,
        "in_s":  from_s,
        "out_s": 10
    }

    if from_s == FIB:
        return convert_from_fib(src_num)
    elif from_s != 10:
        num = src_num
        dec_len = len(num)
        frac_len = 0
        if ',' in num or '.' in num:
            dec_len = num.find(SEP)
            frac_len = len(num) - dec_len - 1
            num = num.replace(SEP, '')

        num = [L2N[i] for i in num]
        powers = list(range(dec_len - 1, -frac_len - 1, -1))
        res_powers = [pow(from_s, i) for i in powers]

        res = reduce(lambda acc, x: acc + x[0] * x[1], zip(num, res_powers), 0)

        logs['sep_numbers'] = num
        logs['powers'] = powers
        logs['powers_calc'] = res_powers
    else:
        res = float(src_num)
        logs['mode'] = LOG_MODE_PASSTHROUGH

    res = str(res)
    logs['out'] = res
    return res, logs


def convert_from_10(num: str,
                    to_s: Union[int, FIB],
                    dest_precision: int = 5,
                    is_zero_precision=1e-8) -> [str, dict]:
    logs = {
        "mode":  LOG_MODE_FROM_10,
        "in":    num,
        "in_s":  10,
        "out_s": to_s
    }

    if to_s == FIB:
        return convert_to_fib(num)
    elif to_s == 10:
        res = round(float(num), dest_precision)
        logs['mode'] = LOG_MODE_PASSTHROUGH
    else:
        logs['steps_dec'] = []
        logs['steps_frac'] = []
        dec_rem = []
        num = float(num)
        src = trunc(num)
        while src >= 1:
            cur = src % abs(to_s)
            dec_rem.append(cur)
            logs['steps_dec'].append((src, cur, N2L[cur], src // to_s))
            src //= to_s
        dec_rem.reverse()

        frac_rem = []
        # If we have fractional part, generate numbers until approx 0 or to specific count of digits
        if not isclose(num - trunc(num), 0, abs_tol=is_zero_precision):
            src = num - trunc(num)
            iters = 0
            while not isclose(src, 0, abs_tol=is_zero_precision) and \
                    (dest_precision is None or iters <= dest_precision):  # make one digit more for rounding
                tmp = src * to_s
                dec = trunc(tmp)
                frac_rem.append(dec)
                logs['steps_frac'].append((src, tmp, N2L[dec], tmp - dec))
                src = tmp - dec
                iters += 1

        res = join_number(dec_rem, frac_rem)

    res = str(res)
    logs['out'] = res
    return res, logs


def convert_powers(source_num: str, from_s: int, to_s: int, is_zero_precision: float = 1e-9) -> [str, dict]:
    if from_s == FIB or to_s == FIB:
        raise InvalidSystem("Cant use fibonacci here")

    if from_s < 0 or to_s < 0:
        raise InvalidSystem("Cant use negatives here")

    def check_exp(a, b):
        return isclose(modf(log(a, b))[0], 0, abs_tol=is_zero_precision)

    is_upscale = check_exp(to_s, from_s)
    is_downscale = check_exp(from_s, to_s)

    if not is_upscale and not is_downscale:
        raise InvalidSystem("Seems that there is no exponential connection between arguments")

    logs = {
        "mode":  LOG_MODE_DIRECT_POWER,
        "in":    source_num,
        "in_s":  from_s,
        "out_s": to_s,
        "steps": []
    }

    if is_upscale:
        upscale_by = int(modf(log(to_s, from_s))[1])  # to_s^(upscale_by) = from_s
        partial = []
        for i, part in enumerate(source_num.split('.')):
            n = len(part)
            # pad number to make length divisible by upscale_by
            add = upscale_by - (n % upscale_by)
            part = pad_to(part, n + (add if add != upscale_by else 0), '0', left=(i == 0))

            # converting each of groups of length=upscale_by using classic method
            for start in range(0, len(part), upscale_by):
                n = part[start:start + upscale_by]
                number = convert(n, from_s, to_s, straight_method=True)[0]
                logs['steps'].append((n, number))
                partial.append(number)
            if i == 0:
                partial.append('.')
    else:
        upscale_by = int(modf(log(from_s, to_s))[1])
        partial = []
        for i, part in enumerate(source_num.split(SEP)):
            # convert using classic method each input digit and join it
            for n in part:
                number = convert(n, from_s, to_s, straight_method=True)[0]
                number = pad_to(number, upscale_by, '0')
                logs['steps'].append((n, number))
                partial.append(number)
            if i == 0:
                partial.append(SEP)

    res = ''.join(partial).lstrip('0')
    logs['out'] = res

    return res, logs


def convert(source_num: str,
            from_s: Union[int, FIB],
            to_s: Union[int, FIB],
            dest_precision: int = 5,
            is_zero_precision: float = 1e-8,
            straight_method: bool = False):
    source_num = source_num.replace(',', SEP)

    if not verify_cs(source_num, from_s, L2N):
        raise ValueError("Invalid source counting system")

    if not straight_method:
        try:
            res, logs = convert_powers(source_num, from_s, to_s)
            res = round_in_cs(res, to_s, dest_precision)
            logs['out'] = res
            return res, [logs]
        except InvalidSystem:
            pass

    num_10, logs_to10 = convert_to_10(source_num, from_s)
    res, logs_from10 = convert_from_10(num_10, to_s, dest_precision, is_zero_precision)

    res = round_in_cs(res, to_s, dest_precision)

    if logs_from10['mode'] == LOG_MODE_PASSTHROUGH:
        logs_to10['out'] = res
    if logs_to10['mode'] == LOG_MODE_PASSTHROUGH:
        logs_from10['out'] = res

    return res, [logs_to10, logs_from10]


def run(val: str, src: Union[int, FIB], dst: Union[int, FIB], precision=5):
    if src != FIB:
        src = int(src)
    if dst != FIB:
        dst = int(dst)
    res, logs = convert(val, src, dst, dest_precision=precision)
    print(process_all_logs(logs))


if __name__ == "__main__":
    data = [[], ['34106', '10', '15'], ['16116', '7', '10'], ['21104', '5', '15'], ['51,96', '10', '2'],
            ['41,6C', '16', '2'],
            ['14,67', '8', '2'], ['0,001101', '2', '16'], ['0,001011', '2', '10'], ['1B,08', '16', '10'],
            ['42', '10', FIB], ['147', '-10', '10'], ['1000010101', FIB, '10'], ['1678', '-10', '10']]

    # run(*data[5])
    # exit(0)

    for i in range(1, 14):
        print("\n\n", i)
        run(*data[i])
