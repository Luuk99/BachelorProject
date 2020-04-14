import PyInquirer
import os
import fileinput
import sys

# Constant for the interval app names that are
app_names = ["e.www.cameratest", "e.www.cpufactorialtest", "e.www.gpstest", "e.www.httpsrequesttest", "e.www.writelocaltest", "e.www.writeroomtest" "e.www.writesdtest"]
small_app_names = []

# Constants for the lines to look for per app
line_standard = "handler.postDelayed(runnable,"
line_gps = "startLocationUpdates("

# Constants for the options for the app
frequency_options = ["low_frequency", "medium_frequency", "high_frequency"]
priority_options = ["low_priority", "medium_priority", "high_priority"]

# Returns the search line for the given app
def searchline_for_app(app_name):
   if (app_name == "e.www.gpstest"):
      return line_gps
   else:
      return line_standard

# Function that creates the apk for a given frequency
def build_apk(app_name, frequency):
   # Get the search line
   search_line = searchline_for_app(app_name)
   # Check if app uses standard frequencies
   if (app_name == "e.www.gpstest"):
      # Open the MainActivity.kt file
      for line in fileinput.input(os.getcwd() + '/../Apps/' + app_name + '_low_frequency/app/src/main/java/e/www/' + app.lower() + '/MainActivity.kt', inplace=True):
         if (line.strip() == search_line):
            print("startLocationUpdates(" + frequency + ", " + priority + ")")
         else:
            sys.stdout.write(line)
   else:
      # Open the MainActivity.kt file
      for line in fileinput.input(os.getcwd() + '/../Apps/' + app_name + '_low_frequency/app/src/main/java/e/www/' + app.lower() + '/MainActivity.kt', inplace=True):
         if (line.strip() == search_line):
            print("handler.postDelayed(runnable," + frequency + ")")
         else:
            sys.stdout.write(line)
   # TODO: Build the APK

# Function that creates all apk's for the given app
def build_app_versions(app_name):
   # Decode the low_frequency app
   os.system('java -jar /usr/local/bin/apktool.jar d /../Apps/{0}_low_frequency.apk'.format(app_name))
   # Create the medium_frequency version
   build_apk(app_name, "medium_frequency")
   # Create the high_frequency version
   build_apk(app_name, "high_frequency")
   
# Function which handles confirmation of the building
def handle_confirm_result(confirm):
   if confirm:
      # Start building the apps
      for app_name in app_names:
         build_app_versions(app_name)
      # Alert user of completion
      print("APK building completed")
   else:
      print("APK building aborted")

# Ask user to confirm building apk's
confirm_question = [
    {
        'type': 'confirm',
        'name': 'build',
        'message': 'Are you sure you want to build all apk\'s in this directory?',
        'default': True,
    }
]
answers = PyInquirer.prompt(confirm_question)
handle_confirm_result(answers['build'])
