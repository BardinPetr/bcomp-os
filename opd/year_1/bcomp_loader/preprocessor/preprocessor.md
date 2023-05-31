## BComp Assembler Preprocessor

## Usage

```shell
cd src # enter code directory root
python3 ../preprocessor/main.py main.basm build/main.build.basm -t build/abi.build.basm
bcomp -c -r -C build/main.build.basm
```

- first argument - path to the root code file, all include directive should be relative to the directory of that file
- second argument - output file, in which will all included files be built
- -t argument - if present, table of global functions with all addresses substituted would be written to that file, it could be later inserted as header file to applications to provide them with OS ABI. 

### Constructions available
#### String encoding 
To encode strings to memory, the following format could be used:
`LABEL: WORD "text here"`
By default, preprocessor uses KOI8-R encoding. Data layout is 1 byte per char, 2 chars per word, with 0 at the end, in format `(char[1] char[0]) ... (char[i+1]@1b char[i]@1b) (0x00, 0x00/char[2n-1])`.
For convinience, pointer to the starting address is created automatically at the end of string memory with original label and postfix `_PTR`. 

#### File includes
The preprocessor provides instruction to include other asm files by directly inserting included program text in place of `INCLUDE` instruction. 
Syntax: `INCLUDE file_path_here`

Other preprocessor modules are run after joining all included files. 
*No relocation is done, therefore use of `ORG` directive of assembler and `INCLUDE` of preprocesseor are allowed only at root file.*

#### Function support 
Preprocessor provides number of helpers for writting functions, however it is important to follow the template for module to be run. 

The preprocessor enforces TODO calling convention. 

##### Local varible names
Preprocessor provides labeling for stack memory cells. All variables must be declared in `; stack` segment of function header as `; &: label_name_here`. In function body *(only between *function label* and *`; end func FUNCNAME`)* all occurences of `&label_name` got replaced with `&stack_number`.  

As BComp hasn't got RBP, addressing is from SP. The first declared variable gets &0 and lines after get incremented numbers. **Warning**: When it is inevitable to use `PUSH` in body, with each incstruction SP moves so generated stack pointer shifts are moved also and will not point to original variable. Use `&label_name+pushes_before_count` to shift this label.

 
##### Prologue and epilogue generation
To reduce code size, macros `APUSH` and `APOP` provided for prologue and epilogue respectively.
Only local variables declared in format described in previous paragraph are used.

The following template is used:
```asm
Prologue:
SPADD #%(1-CNT)%

Epilogue:
SPADD #%CNT%
```
Where CNT is the local stack variables count. All values are 1-byte signed integers.

*(For info: `SPADD x` is `SP + x -> SP` instruction of bcomp)*

##### Function template
**All three NAMESPACE__FUNCNAME names should be same as function label and be present, other way function is ignored by preprocessor**

```asm
; func NAMESPACE__FUNCNAME: (args, ...) description here
; 
; params: AC: arg1        <- just documentation about parameters (no preprocessing)
;         STACK: arg2
;         ...
; return: AC: ...         <- also only docs
; stack:                  <- used for local variables preprocessor
; &: var_name1            <- would be &0
; &: var_name2
; &: var_name3
; &: ret_addr             <- having variable that has `ret` in name is signing that all following declarations ignored in prologue
; &: func_stack_arg1 
; &: ...
; 
; begin globals
FUNCNAME__VARNAME: WORD ....
; end globals

; begin func
NAMESPACE__FUNCNAME:

; begin prologue
  APUSH
; end prologue

; code here

; begin epilogue
  APOP  
  RET
; end epilogue
; end func NAMESPACE__FUNCNAME
```


