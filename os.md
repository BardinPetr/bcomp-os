# BComp OS

> Modified version BComp emulator is used; BComp assembly preprocessing is required.

> [Upgraded BComp repo](https://github.com/BardinPetr/bcomp)


## Running
```bash
cd src/
mkdir build
python3 ../preprocessor/main.py main.basm build/main.build.basm -t build/abi.build.basm
java -jar bcomp.jar -Dmode=cli -Dcode=build/main.build.basm -DtimerPeriod=10 -Dautostart
```

*Do not try to run in GUI/Dual mode, as it would be tremendously slow. Even in CLI it is better to set t.*

*`-Dautostart` is used not only to start bcomp after loading program, but also to set delays to 0*

*Also, dev 0 timer base period should be set to 10ms to have acceptable experience for multiprocessing*


## Dynamic program loading 
There are demo applications available in `src/apps`.

Binary generator is included in upgraded BComp. The whole process of creating ASCII representation of the binary (let the target file be src/test.basm).

For using OS ABI, there should be `INCLUDE build/abi.build.basm` line in the code.


```bash
cd src/
python3 ../preprocessor/main.py test.basm build/test.build.basm
java -jar bcomp.jar -Dmode=build -Dcode=build/test.build.basm > build/test.bin
```

Resulting file would be `build/test.bin` which is compatible with `load` command in the shell and could be transmitted via `Send file` dialog in the terminal (IO4). 

### Binary format 
```
sections_cnt@2b
start_addr@2b
prog_size@2b
[section_desc@6b] * sections_cnt
section_desc: (offset@2b, (is_code@1, 0000, len@11)@2b, org@2b)@6b
[cmd@2b] * len
```

#### Text
This format has also ASCII replesentation for loading via console. All 2-byte words are encoded as hex numbers separated by `\n`.

#### Limitations
- working:
  - absolute direct addressing
  - relative direct addressing
  - basm WORD $ directive
- not working:
  - relative indirect addressing