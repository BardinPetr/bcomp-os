#!/usr/bin/bash

# Required to use ** wildcards
shopt -s globstar

# 1

echo "Task #4/1"
wc -m lab0/**/t* 2>&1 1>/tmp/wc_result
echo "Task #4/1, file /tmp/wc_result:"
cat /tmp/wc_result

# 2

echo "Task #4/2"
ls -Rrm lab0/toxicroak7/

# 3

echo "Task #4/3"
grep -Eiv --no-filename "d$" lab0/nincada6/voltorb lab0/servine0/zebstrika 2>/dev/null

# 4

echo "Task #4/4"
ls -dplu lab0/**/*e 2>/tmp/ls_4_errors | grep -v "/$" | sort -k7M -k6n -k8 | tail -n 4

# 5

echo "Task #4/5"
ls -dpl lab0/**/s* 2>/dev/null | grep -v "/$" | sort -rk2

# 6

echo "Task #4/6"
cat -n $(ls -dp lab0/**/*7 | grep -v "/$") | sort

shopt -u globstar
