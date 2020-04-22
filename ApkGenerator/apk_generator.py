#!/usr/bin/env python

# Import the necessary packages
import sys
import os
import os.path
from os import path
import subprocess
import fileinput
import shutil

# Import the config file
import config

# Function that checks whether a given argument is true or false
def str_to_bool(argument):
   if isinstance(argument, bool):
      return argument
   if argument.lower() in ('yes', 'true', 't', 'y'):
      return True
   elif argument.lower() in ('no', 'false', 'f'):
      return False
   else:
      raise argparse.ArgumentTypeError('Boolean value expected.')

# Function that finds the given file
def find_file(name, path):
   for root, dirs, files in os.walk(path):
      if name in dirs:
         return os.path.join(root, name)
   return None

# Function that builds the apk
def build_apk(android_folder_name, run, current_path):
   # Create the apk
   os.system("chmod +x " + android_folder_name + "/gradlew")
   apk_process = subprocess.Popen(["./gradlew", "assembleDebug"], cwd=android_folder_name)
   apk_process.wait()
   print("APK built")
   # Rename the apk
   os.rename(r"" + android_folder_name + "/app/build/outputs/apk/debug/app-debug.apk", r"" + current_path + "Outputs/" + android_folder_name.split('/')[-1] + "_" + str(run) + ".apk")
   print("APK renamed and moved")

# Function that starts the process
def start_process(android_folder_name, number_of_runs, current_path, keep_temp):
   # Clear the temp folder from previous runs
   shutil.rmtree(current_path + "Temp", ignore_errors=True)
   # Get the folder containing the java/kotlin files
   app_name = android_folder_name.split("/")[-1].lower()
   # Repeat for the number of runs
   for run in range(0, number_of_runs):
      # Copy the folder to the Temp folder
      shutil.copytree(android_folder_name, current_path + "Temp/Run" + str(run), symlinks=False, ignore=None)
      run_folder = current_path + "Temp/Run" + str(run)
      java_folder = find_file(app_name, run_folder + "/app/src/main/java")
      # Loop over the files in the java folder
      for filename in os.listdir(java_folder):
         print("Updating " + filename)
         # Update the file using the update_file function of the config
         file_updated = config.update_file(filename, os.path.join(java_folder, filename), run)
         # Update the lines of the file using the update_loc function of the config
         if not file_updated:
            for line in fileinput.input(os.path.join(java_folder, filename), inplace=True):
               sys.stdout.write(config.update_loc(filename, line, run))
         print(filename + " updated")
         # Build the APK
         build_apk(android_folder_name, run, current_path)
   # Check to clear the Temp folder
   if not keep_temp:
      print("Removing Temp")
      shutil.rmtree(current_path + "Temp", ignore_errors=True)
   # Notify the user of completion
   sys.exit("All done")

# Function that checks whether the provided project folder exists
def check_folder(android_folder_name):
   if (path.exists(android_folder_name) == False):
      sys.exit("Folder with name " + android_folder_name + " could not be found")
   elif (path.isdir(android_folder_name) == False):
      sys.exit(android_folder_name + " is not a directory")
   return True

# Function that checks whether all arguments are provided
def check_arguments():
   if (len(sys.argv) != 4):
      sys.exit("Missing arguments, please provide folder path, the number of runs and boolean for keeping or deleting temp folders")
   else:
      # Grab the folder name and number of runs from sys
      current_path = os.path.realpath(__file__).replace("apk_generator.py", "")
      android_folder_name = str(sys.argv[1])
      number_of_runs = int(sys.argv[2])
      keep_temp = str_to_bool(sys.argv[3])
      # Check whether the project folder exists
      if check_folder(android_folder_name):
         # Alert the user of the given arguments
         print("Provided Android project folder path: " + android_folder_name)
         print("Provided number of runs: " + str(number_of_runs))
         print("Keep temp folders: " + str(keep_temp))
         # Start going through the files
         start_process(android_folder_name, number_of_runs, current_path, keep_temp)

# Check the arguments
check_arguments()
