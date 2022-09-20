#!/usr/bin/bash

cd lab0

# #0

ln -s nincada6 Copy_22

# #1

# STDERR: cp: toxicroak7/shelmetyanmega: Permission denied
# Original command causes permission denied due to lacking destination dir write permission. Here is a fix
chmod u+w toxicroak7
cp yanmega9 toxicroak7/shelmetyanmega 
# chmod u-w toxicroak7 # This permission is required in #3 and #5

# #2

# The command from the following block is invalid as it recursively copies itself into it. So commenting it, however I will write a permission fix
# Original command causes permission denied due to lacking destination dir write permission and read on source
# STDERR: cp: servine0/typhlosion: Permission denied
# chmod u+w servine0
# chmod u+r servine0/typhlosion
# cp -r servine0 servine0/typhlosion 
# chmod u-w servine0
# chmod u-r servine0/typhlosion

# #3

# STDERR: ln: toxicroak7/sewaddleyanmega: Permission denied
# Original command causes permission denied due to lacking dir write permission
# chmod u+w toxicroak7 # Already done
ln -s $(pwd)/yanmega9 ./toxicroak7/sewaddleyanmega
# chmod u-w toxicroak7 # This permission is required in #4

# #4

# STDERR: cp: koffing3: Permission denied
# Original command causes permission denied due to lacking read permission on source
chmod u+r koffing3
cp koffing3 servine0/typhlosion/
chmod u-r koffing3

# #5

# 
# STDERR: ln: toxicroak7/butterfreekoffing: Permission denied
# And here also for write in toxicroak7
# chmod u+w toxicroak7 # Already done
ln koffing3 toxicroak7/butterfreekoffing
chmod u-w toxicroak7

# #6

cat toxicroak7/shelmet > koffing3_87
cat toxicroak7/sewaddle >> koffing3_87

cd ..
