#!/usr/bin/env python

import sys
import os
import os.path
from os import path
import subprocess
import fileinput

# Import the config file
import config

# Function that finds the given file
def find(name, path):
   for root, dirs, files in os.walk(path):
      if name in files:
         return os.path.join(root, name)
   return None

# Function that opens the file, reads every line and passes it to the config script
def open_file(given_file):
   # Repeat this step for every variable
   variables = config.get_variables()
   for i in range(0, len(variables)):
      for line in fileinput.input(given_file, inplace=True):
         sys.stdout.write(config.main(line, i))
      print("Java/kotlin file updated")
      build_apk(variables[i])

# Function that builds the apk
def build_apk(variable):
   # Create the apk
   os.system("chmod +x " + android_folder_name + "/gradlew")
   apk_process = subprocess.Popen(["./gradlew", "assembleDebug"], cwd=android_folder_name)
   apk_process.wait()
   print("APK built")
   # Rename the apk
   os.rename(r"" + android_folder_name + "/app/build/outputs/apk/debug/app-debug.apk", r"" + current_path + "Outputs/" + android_folder_name.split('/')[-1] + "_" + variable + ".apk")
   print("APK renamed")

# Check if the arguments are given
if (len(sys.argv) != 3):
   sys.exit("Missing arguments, please provide folder path and java/kotlin file name")

# Grab the folder name and java/kotlin script from sys
current_path = os.path.realpath(__file__).replace("apk_generator.py", "")
android_folder_name = str(sys.argv[1])
android_file_name = str(sys.argv[2])

# Check if the folder and java/kotlin file exist and are directory and file respectively 
if (path.exists(android_folder_name) == False):
   sys.exit("Folder with name " + android_folder_name + " could not be found")
elif (path.isdir(android_folder_name) == False):
   sys.exit(android_folder_name + " is not a directory")

android_file_path = find(android_file_name, android_folder_name + "/app/src/main/java")
if android_file_path is None:
   sys.exit("Java/kotlin " + android_file_name + " could not be found")
elif (path.isfile(android_file_path) == False):
   sys.exit("Java/kotlin " + android_file_name + " is not a file")

# Alert the user of the given arguments
print("Provided Android project folder path: " + android_folder_name)
print("Provided java/kotlin file: " + android_file_name)

# Open the given java/kotlin file
open_file(android_file_path)
