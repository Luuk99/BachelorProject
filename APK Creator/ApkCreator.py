import PyInquirer
import os
import fileinput
import sys

# Constants for the lines to look for per app
line_camera = ""
line_cpufactorial = "handler.postDelayed(runnable,"
line_gps = "startLocationUpdates("

# Constants for the options for the app
frequency_options = ["low_frequency", "medium_frequency", "high_frequency"]
priority_options = ["low_priority", "medium_priority", "high_priority"]

# Function which returns the search line for the given app
def searchline_for_app(app):
    if (app == "CameraTest"):
        return line_camera
    elif (app == "CpuFactorialTest"):
        return line_cpufactorial
    elif (app == "GpsTest"):
        return line_gps

# Function which handles the selected variable
def handle_variable_result(app, search_line, frequency):
    if (app == "GpsTest"):
        # Ask the user to also select the priority variable
        priority_question = [
            {
                'type': 'list',
                'name': 'priority',
                'message': 'Which priority do you want to use?',
                'choices': priority_options
            }
        ]
        answers = PyInquirer.prompt(priority_question)
        priority = answers['priority']

        # Edit the file
        for line in fileinput.input(os.getcwd() + '/../Apps/' + app + '/app/src/main/java/e/www/' + app.lower() + '/MainActivity.kt', inplace=True):
            if (line.strip() == search_line):
                print("startLocationUpdates(" + frequency + ", " + priority + ")")
            else:
                sys.stdout.write(line)
        print("Variables updated successfully!")
    else:
        print(frequency)

# Function which checks whether the user wants to edit the variable or not
def handle_edit_result(app, line, edit):
    if edit:
        # Ask user to select the new frequency
        frequency_question = [
            {
                'type': 'list',
                'name': 'frequency',
                'message': 'Which frequency do you want to use?',
                'choices': frequency_options
            }
        ]
        answers = PyInquirer.prompt(frequency_question)
        handle_variable_result(app, line, answers['frequency'])
    # TODO: Allow user to create APK straight away

# Asks user whether he wants to edit the current variable
def ask_to_edit(app, line):
    edit_question = [
        {
            'type': 'confirm',
            'message': 'Do you want to change the line \"' + line + '\" ?',
            'name': 'edit',
            'default': True,
        }
    ]
    answers = PyInquirer.prompt(edit_question)
    handle_edit_result(app, line, answers['edit'])

# Function which handles the selected app
def handle_app_result(app):
    # Open main activity of the app
    file = open(os.getcwd() + '/../Apps/' + app + '/app/src/main/java/e/www/' + app.lower() + '/MainActivity.kt', "r")
    with file as f:
        search_line = searchline_for_app(app)
        lines = f.readlines()
        for line in lines:
            if (line.strip().startswith(search_line)):
                file.close()
                ask_to_edit(app, line.strip())

# Ask user to select the app they want to create the APK for
app_questions = [
    {
        'type': 'list',
        'name': 'app',
        'message': 'Which app do you want to build?',
        'choices': [
            'CameraTest', 'CpuFactorialTest', 'GpsTest'
        ]
    }
]
answers = PyInquirer.prompt(app_questions)
handle_app_result(answers['app'])