# BComp OS


## Dynamic prog loading
### Binary format 
```
sections_cnt@2b
start_addr@2b
prog_size@2b
[section_desc@6b] * sections_cnt
; section_desc: (offset@2b, (is_code@1, 0000, len@11)@2b, org@2b)@6b
[cmd@2b] * len
```

#### Limitations
- working:
  - absolute direct addressing
  - relative direct addressing
  - basm WORD $ directive 
- not working:
  - relative indirect addressing