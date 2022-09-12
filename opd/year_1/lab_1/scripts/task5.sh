#!/bin/sh

rm lab0/porygon7
rm lab0/nincada6/voltorb
rm -r lab0/Copy_*

# Adding write permission on parent directory and of files to delete (and to them) 
chmod u+w lab0/toxicroak7
chmod u+w lab0/toxicroak7/butterfreekoffi*
rm lab0/toxicroak7/butterfreekoffi*
chmod u-w lab0/toxicroak7

# Adding rwx permissions recursively to everythin as we have high level of nesting here to avoid modifying each file to w and directories to rwx
chmod -R u+rwx lab0/servine0
rm -r lab0/servine0

# Using rmdir requires having an empty dir, so clearing it before
rm -rf lab0/nincada6/gligar/*
rmdir lab0/nincada6/gligar
