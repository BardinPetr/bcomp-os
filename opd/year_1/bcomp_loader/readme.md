# bcomp programm loader

```
sections_cnt@2b
start_addr@2b
[section_desc@6b] * sections_cnt
; section_desc: ((is_code@1b, 0000, offset@11)@2b, len@2b, org@2b)
[cmd@2b] * len
```


## pseudocode
```python
codeBlock == 1

codeBlock == 0
(ptr)++ = cmd


program_start: int@2b = first_free_addr()

input_str: list[int@1b] = read_line()
data: list[int@2b] = base64decode(input_str)

remap: map (int@2b, int@2b) -> int@2b

mem_prog_iter: int@2b = program_start
cur_input_pos: int@2b = 0
commands_count: int@2b = 0



for(int@2b i = 0; i < len(data); i++):

  
  mem_prog_iter++
  cur_input_pos++
  commands_count++
  
  remap[mem_prog_iter] = cur_input_pos
  mem(mem_prog_iter) = word
  

while :


```