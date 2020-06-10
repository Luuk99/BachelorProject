#!/usr/bin/env python

# Function that updates the given file
def update_file(file_name, file, run):
   # Add your code here
   return False

# Function that updates the given line of code
def update_loc(file_name, line, run):
   lines = ["// test 1", "// test 2", "// test 3"]
   if (line.strip() == "setContentView(R.layout.activity_main)"):
      return line.replace("setContentView(R.layout.activity_main)", lines[run])
   # Add your code here
   return line

