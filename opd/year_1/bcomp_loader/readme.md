# BComp dynamic programm loader

## Binary format 
```
sections_cnt@2b
start_addr@2b
[section_desc@6b] * sections_cnt
; section_desc: (offset@2b, (is_code@1, 0000, len@11)@2b, org@2b)@6b
[cmd@2b] * len
```

## Usage
> BComp assembly is used with addons, preprocessing is required

```shell
cd src
../preprocess.py main.basm 1> build.basm
bcomp -c -C build.basm
```