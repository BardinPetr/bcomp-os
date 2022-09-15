from functools import reduce

from tools import txt_sub, txt_sup, FIB


def process_logs_to10(logs):
    sum_str = reduce(lambda acc, x: acc + [f'{x[0]} * {logs["in_s"]}{txt_sup(x[1])}'],
                     zip(logs['sep_numbers'], logs['powers']), [])
    sum_str = ' + '.join(sum_str)
    calc_str = reduce(lambda acc, x: acc + [f'{x[0]} * {x[1]}'],
                      zip(logs['sep_numbers'], logs['powers_calc']), [])
    calc_str = ' + '.join(calc_str)

    return f'{logs["in"]}{txt_sub(logs["in_s"])} в СС{logs["out_s"]}\n' \
           f'{logs["in"]}{txt_sub(logs["in_s"])} = {sum_str} =\n= {calc_str} = {logs["out"]}{txt_sub(logs["out_s"])}\n' \
           f'Итог: {logs["out"]}{txt_sub(logs["out_s"])}'


def process_logs_from10(logs):
    dec_str = [f'{cur} = {logs["out_s"]} * {int_part} + {rem_alph}'
               for cur, rem, rem_alph, int_part in logs['steps_dec']]
    dec_str = '\n'.join(dec_str) + f'\nИтог: {logs["out"].split(".")[0]}{txt_sub(logs["out_s"])}'

    res = f'{logs["in"]}{txt_sub(logs["in_s"])} в СС{logs["out_s"]}\n' \
          f'Перевод целой части: \n{dec_str}\n'

    if len(logs['steps_frac']) > 0:
        frac_str = [f'{round(src, 5)} * {logs["out_s"]} = {round(src_mult, 5)} = {dec_part} + {round(frac_part, 5)}'
                    for src, src_mult, dec_part, frac_part in logs['steps_frac']]
        frac_str = '\n'.join(frac_str) + f'\nИтог (с округлением): {logs["out"].split(".")[1]}{txt_sub(logs["out_s"])}'

        res += f'Перевод дробной части: \n{frac_str}\n\n'

    res += f'\nРезультат: {logs["out"]}{txt_sub(logs["out_s"])}'
    return res


def process_logs_from_fib_to_10(logs):
    if logs["in_s"] != FIB or logs["out_s"] != 10:
        raise Exception("Got invalid source or destination counting systems, it is probably a misuse of log functions")

    fib_val = [str(i) for i in logs["fib_val"]]
    fib_id = [f'F{txt_sub(i)}' for i in logs["fib_ids"]]

    return f'Из {logs["in"]} Фибоначчи в СС10\n' \
           f'{logs["in"]} = {" + ".join(fib_id)} = {" + ".join(fib_val)} = {logs["out"]}\n' \
           f'Итог: {logs["out"]}'


def process_logs_from_10_to_fib(logs):
    if logs["out_s"] != FIB or logs["in_s"] != 10:
        raise Exception("Got invalid source or destination counting systems, it is probably a misuse of log functions")
    src = logs['in']
    dst = logs["out"]
    fib_sum = map(lambda x: f'{x[0]}*{x[1]}', zip(dst, logs['fibs']))

    return f'Из {src}{txt_sub(10)} в Фибоначчи\n' \
           f'{src} = {" + ".join(fib_sum)} = {dst}\n' \
           f'Итог: {dst}фиб'


def process_logs_direct(logs):
    steps = reduce(lambda acc, i: acc + f', ({i[0]} -> {i[1]})', logs['steps'], "").removeprefix(', ')

    src_n = f'{logs["in"]}{txt_sub(logs["in_s"])}'
    res_n = f'{logs["out"]}{txt_sub(logs["out_s"])}'
    return f'{src_n} в СС{logs["out_s"]}\n' \
           f'{src_n} = {steps} = {res_n}\n' \
           f'Результат: {res_n}'


LOG_MODE_TO_10 = 0
LOG_MODE_FROM_10 = 1
LOG_MODE_DIRECT_POWER = 2
LOG_MODE_FROM_FIB = 3
LOG_MODE_TO_FIB = 4
LOG_MODE_PASSTHROUGH = 5
MODE_TO_FUNC = {
    LOG_MODE_TO_10:        process_logs_to10,
    LOG_MODE_FROM_10:      process_logs_from10,
    LOG_MODE_DIRECT_POWER: process_logs_direct,
    LOG_MODE_FROM_FIB:     process_logs_from_fib_to_10,
    LOG_MODE_TO_FIB:       process_logs_from_10_to_fib,
    LOG_MODE_PASSTHROUGH:  lambda _: ""
}


def number_pretty_log(log):
    return {
        **log,
        'in':  log['in'].removesuffix('.0'),
        'out': log['out'].removesuffix('.0')
    }


def process_all_logs(logs):
    first, last = logs[0], logs[-1]
    res = f"Перевод числа {first['in']} из СС{first['in_s']} в СС{last['out_s']}\n\n"
    logs = list(enumerate(filter(lambda x: x['mode'] != LOG_MODE_PASSTHROUGH, logs)))
    for i, log in logs:
        log = number_pretty_log(log)
        if len(logs) > 1:
            res += f"Шаг {1 + i}: "
        res += MODE_TO_FUNC[log['mode']](log)
        res += '\n---\n'

    return res
