import xml

src= """
<table border="1">
<tbody><tr>
  <td align="center"><b>Наименование</b></td>
  <td align="center"><b>Мнемоника</b></td>
  <td align="center"><b>Код</b></td>
  <td align="center"><b>Описание</b></td>
</tr>
<tr><td colspan="4" align="center"><b>Безадресные команды</b></td></tr>
<tr>
  <td>Нет операции</td>
  <td>NOP</td>
  <td>0000</td>
  <td></td>
</tr>
<tr>
  <td>Останов</td>
  <td>HLT</td>
  <td>0100</td>
  <td></td>
</tr>
<tr>
  <td>Разрешение прерываний</td>
  <td>EI</td>
  <td>0200</td>
  <td></td>
</tr>
<tr>
  <td>Запрещение прерываний</td>
  <td>DI</td>
  <td>0300</td>
  <td></td>
</tr>
<tr>
  <td>Очистка флага переноса</td>
  <td>CLC</td>
  <td>0400</td>
  <td>0 → С</td>
</tr>
<tr>
  <td>Инверсия флага переноса</td>
  <td>CMC</td>
  <td>0500</td>
  <td>(!С) → С</td>
</tr>
<tr>
  <td>Циклический сдвиг влево</td>
  <td>ROL</td>
  <td>0600</td>
  <td>Содержимое А и С циклически сдвигается влево</td>
</tr>
<tr><td><u>Сдвиг влево</u></td><td><u>SHL</u></td>
  <td>0680</td>
  <td>Содержимое А сдвигается влево c заполнением старшего бита нулём</td>
</tr>
<tr>
  <td>Циклический сдвиг вправо</td>
  <td>ROR</td>
  <td>0700</td>
  <td>Содержимое А и С циклически сдвигается вправо</td>
</tr>
<tr><td><u>Сдвиг вправо</u></td><td><u>SHR</u></td>
  <td>0780</td>
  <td>Содержимое А сдвигается вправо c заполнением младшего бита нулём</td>
</tr>
<tr>
  <td>Очистка аккумулятора</td>
  <td>CLA</td>
  <td>0800</td>
  <td>0 → А</td>
</tr>
<tr>
  <td>Инверсия аккумулятора</td>
  <td>CMA</td>
  <td>0900</td>
  <td>(!А) → А</td>
</tr>
<tr>
  <td>Инкремент аккумулятора</td>
  <td>INC</td>
  <td>0A00</td>
  <td>(А) + 1 → А</td>
</tr>
<tr>
  <td>Декремент аккумулятора</td>
  <td>DEC</td>
  <td>0B00</td>
  <td>(А) - 1 → А</td>
</tr>
<tr><td><u>Обмен аккумулятора с вершиной стека</u></td><td><u>SWAP</u></td>
  <td>0C00</td>
  <td>(A) ↔ ((7FF))</td>
</tr>
<tr><td><u>Помещение аккумулятора в стек</u></td><td><u>PUSH</u></td>
  <td>0D00</td>
  <td>(7FF) - 1 → 7FF, A → (7FF)</td>
</tr>
<tr><td><u>Помещение РС в стек</u></td><td><u>PUSHF</u></td>
  <td>0D80</td>
  <td>(7FF) - 1 → 7FF, РС → (7FF)</td>
</tr>
<tr><td><u>Получение аккумулятора из стека</u></td><td><u>POP</u></td>
  <td>0E00</td>
  <td>((7FF)) → A, (7FF) + 1 → 7FF</td>
</tr>
<tr><td><u>Получение РС из стека</u></td><td><u>POPF</u></td>
  <td>0E80</td>
  <td>((7FF)) → C, N, Z, EI, (7FF) + 1 → 7FF</td>
</tr>
<tr><td><u>Возврат из подпрограммы</u></td><td><u>RET</u></td>
  <td>0F00</td>
  <td>((7FF)) → СК, (7FF) + 1 → 7FF</td>
</tr>
<tr><td><u>Возврат из прерывания</u></td><td><u>IRET</u></td>
  <td>0F80</td>
  <td>((7FF)) → СК, (7FF) + 1 → 7FF, ((7FF)) → C, N, Z, EI, (7FF) + 1 → 7FF</td>
</tr>
<tr><td colspan="4" align="center"><b>Команды ввода-вывода</b></td></tr>
<tr>
  <td>Очистка флага ВУ</td>
  <td>CLF ВУ</td>
  <td>10XX</td>
  <td></td>
</tr>
<tr>
  <td>Опрос флага ВУ</td>
  <td>TSF ВУ</td>
  <td>11XX</td>
  <td>Если флаг ВУ = 1, то (СК) + 1 → СК</td>
</tr>
<tr>
  <td>Ввод</td>
  <td>IN ВУ</td>
  <td>12XX</td>
  <td>(ВУ) → А</td>
</tr>
<tr>
  <td>Вывод</td>
  <td>OUT ВУ</td>
  <td>13XX</td>
  <td>(А) → ВУ</td>
</tr>
<tr><td colspan="4" align="center"><b>Адресные команды</b></td></tr>
<tr><td><u>Обращение к подпрограмме</u></td><td><u>CALL М</u></td>
  <td>2XXX</td>
  <td>(7FF) - 1 → 7FF, (СК) → (7FF); М → СК</td>
</tr>
<tr>
  <td>Безусловный переход</td>
  <td>JMP М</td>
  <td>3XXX</td>
  <td>М → СК</td>
</tr>
<tr>
  <td>Переход, если перенос</td>
  <td>BCS М</td>
  <td>4XXX</td>
  <td>Если (С) = 1, то М → СК</td>
</tr>
<tr>
  <td>Переход, если плюс</td>
  <td>BPL М</td>
  <td>5XXX</td>
  <td>Если (N) = 0, то М → СК</td>
</tr>
<tr>
  <td>Переход, если минус</td>
  <td>BMI М</td>
  <td>6XXX</td>
  <td>Если (N) = 0, то М → СК</td>
</tr>
<tr>
  <td>Переход, если нуль</td>
  <td>BEQ М</td>
  <td>7XXX</td>
  <td>Если (Z) = 0, то М → СК</td>
</tr>
<tr>
  <td>Пересылка</td>
  <td>MOV М</td>
  <td>8XXX</td>
  <td>(A) → М</td>
</tr>
<tr><td><u>Сравнение</u></td><td><u>CMP М</u></td>
  <td>9XXX</td>
  <td>(A) - (М) → C, N, Z</td>
</tr>
<tr><td><u>Декремент и пропуск</u></td><td><u>LOOP М</u></td>
  <td>AXXX</td>
  <td>(М) - 1 → М; Если (М) ≥ 0, то (СК) + 1 → СК</td>
</tr>
<tr>
  <td>Приращение и пропуск</td>
  <td>ISZ М</td>
  <td>BXXX</td>
  <td>(М) + 1 → М; Если (М) ≥ 0, то (СК) + 1 → СК</td>
</tr>
<tr>
  <td>Вычитание</td>
  <td>SUB М</td>
  <td>CXXX</td>
  <td>(A) - (М) → A</td>
</tr>
<tr>
  <td>Сложение с переносом</td>
  <td>ADC М</td>
  <td>DXXX</td>
  <td>(A) + (С) + (М) → A</td>
</tr>
<tr>
  <td>Сложение</td>
  <td>ADD М</td>
  <td>EXXX</td>
  <td>(A) + (М) → A</td>
</tr>
<tr>
  <td>Логическое умножение</td>
  <td>AND М</td>
  <td>FXXX</td>
  <td>(A) &amp; (М) → A</td>
</tr>
</tbody></table>"""

import xml.etree.ElementTree as ET
import re

tree = ET.fromstring(src)
cmds = {(i[1] if i[1].text else i[1][0]).text: re.compile(i[2].text.replace('X', '.')) for i in tree[0][1:] if len(i) > 2}

txt = """
038: 2039
039: E042
03A: 0200
03B: 3038
03C: 2039
03D: E043
03E: A044
03F: 6043
040: E042
041: 0100
042: 2039
043: 3038
044: 6043
"""

prog = [l.split(': ') for l in txt.strip().split('\n')]

def get_cmd(txt):
  for cmd, rx in cmds.items():
    if rx.match(txt):
      return cmd
  return None

# print(prog)
for pos, val in prog:
  print(f"0x{pos}: {val} [{get_cmd(val)}]")