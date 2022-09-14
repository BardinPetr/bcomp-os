from functools import reduce

from tools import txt_sub, txt_sup


def process_logs_to10(logs, log_conf):
    if logs is None or len(logs) == 0: return ''

    src_num, src_sys, mid_num, mid_sys, _, _ = log_conf
    nums, powers, res_powers = logs

    sum_str = reduce(lambda acc, x: acc + [f'{x[0]} * {src_sys}{txt_sup(x[1])}'], zip(nums, powers), [])
    sum_str = ' + '.join(sum_str)
    calc_str = reduce(lambda acc, x: acc + [f'{x[0]} * {x[1]}'], zip(nums, res_powers), [])
    calc_str = ' + '.join(calc_str)

    return f'{src_num}{txt_sub(src_sys)} в СС{mid_sys}\n' \
           f'{src_num}{txt_sub(src_sys)} = {sum_str} =\n= {calc_str} = {mid_num}{txt_sub(mid_sys)}\n' \
           f'Итог: {mid_num}{txt_sub(mid_sys)}'


def process_logs_from10(logs, log_conf):
    if logs is None or len(logs) == 0: return ''

    _, _, mid_num, mid_sys, dst_num, dst_sys = log_conf
    dec_log, frac_log = logs

    dec_str = [f"{cur} = {dst_sys} * {int_part} + {rem_alph}"
               for cur, rem, rem_alph, int_part in dec_log]
    dec_str = '\n'.join(dec_str) + f"\nИтог: {dst_num.split('.')[0]}{txt_sub(dst_sys)}"

    res = f'{mid_num}{txt_sub(mid_sys)} в СС{dst_sys}\n' \
          f'Перевод целой части: \n{dec_str}\n'

    if len(frac_log) > 0:
        frac_str = [f"{round(src, 5)} * {dst_sys} = {round(src_mult, 5)} = {dec_part} + {round(frac_part, 5)}"
                    for src, src_mult, dec_part, frac_part in frac_log]
        frac_str = '\n'.join(frac_str) + f"\nИтог (с округлением): {dst_num.split('.')[1]}{txt_sub(dst_sys)}"

        res += f'Перевод дробной части: \n{frac_str}\n\n'

    res += f'\nРезультат: {dst_num}{txt_sub(dst_sys)}'
    return res
