# APK Generator
APK Generator is a Python framework that facilitates the execution of experiments involving multiple variations of native Android apps. This framework will allow users to automatically create different versions of their native apps with simple Python code. Note that this framework only runs on Linux distributions using Python3. 

## How it works
The APK Generator consists of 2 parts: `apk_generator.py` and `config.py`. The first, handles all the hard work where it opens all the files in your Android project's Java folder and hands them to the `config.py`. The `config.py` implements 2 functions: `update_file`, which updates an entire Java/Kotlin file, and `update_loc` which updates individual lines of codes passed down from the `apk_generator.py`.

### apk_generator.py
The APK Generator can be called using:
```
python 3 %PATH TO%/ApkGenerator/apk_generator.py %PATH TO ANDROID FOLDER% %NUMBER OF RUNS% %KEEP TEMP?%
```
It accepts the following arguments:
1. **Path to Android folder**: this is the path to the Android project folder.
2. **Number of runs**: this is an integer representing how many times the APK creation process is repeated, this is determined by how many different variations you want to create.
3. **Keep temp?**: this is a boolean (True/False) representing whether to keep the the temporary copy of the Android project with its adjustments. 

### update_file
The `update_file` function of `config.py` looks the following:
```python
def update_file(file_name, file, run):
   # Add your code here
   return False
```
It receives the following objects:
1. **file_name**: this is the name of the Java/Kotlin file.
2. **file**: this is the path to the Java/Kotlin file.
3. **run**: this is the number of the run. Do keep in mind that this ranges from 0 to (number of runs - 1). This is explicitly done to facilitate the easy use of indexing over lists.

What it should return:
* The function should return a Boolean, whether or not the file has been changed. If False is returned, `update_loc` will not be called for the individual lines.

### update_loc
The `update_loc` function of `config.py` looks the following:
```python
def update_loc(file_name, line, run):
   # Add your code here
   return line
```
It receives the following objects:
1. **file_name**: this is the name of the Java/Kotlin file that the loc originates from.
2. **line**: this is a specific line in a Kotlin/Java file.
3. **run**: this is the number of the run. Do keep in mind that this ranges from 0 to (number of runs - 1). This is explicitly done to facilitate the easy use of indexing over lists.

What it should return:
* The function should return a string object representing the a of code.

## How to use
You should only make changes to the `config.py` and leave the `apk_generator.py` as-is, unless you want to make changes to the workings of this framework. Alter the `update_file` and `update_loc` functions in the `config.py` to suit your needs. The above section provides you with information on how these functions work. You can also have a look at the examples in the below section. After you have altered `config.py`, follow these steps on how to use it:
* Run `python3 %PATH TO%/ApkGenerator/apk_generator.py %PATH TO ANDROID FOLDER% %NUMBER OF RUNS% %KEEP TEMP?%`.
* Find the generated APK's in the Outputs folder.
* OPTIONAL: When you have specified to keep the temporary files, you can find the different versions of the Android project folder in the Temp folder.

## Examples
