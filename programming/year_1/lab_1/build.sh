#!/bin/bash

rm -rf build/classes
rm -f build/*.jar

SRC=$(find ./src/ -name "*.java")

javac -d build/classes $SRC
jar -cvfm build/main.jar build/MANIFEST.MF -C build/classes .
