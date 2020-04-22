# APK Generator
APK Generator is a Python framework that facilitates the execution of experiments involving multiple variations of native Android apps. This framework will allow users to automatically create different versions of their native apps with simple Python code. Note that this framework only runs on Linux distributions using Python3. 

# How it works
The APK Generator consists of 2 parts: `àpk_generator.py` and `config.py`. The first, handles all the hard work where it opens all the files in your Android project's Java folder and hands them to the `config.py`. The `config.py` implements 2 functions: `update_file`, which updates an entire Java/Kotlin file, and `update_loc` which updates individual lines of codes passed down from the `apk_generator.py`.

## How to use
You should only make changes to the `config.py` and leave the `apk_generator.py` as-is, unless you want to make changes to the workings of this framework. Alter the `update_file` and `update_loc` functions in the `config.py` to suit your needs. The above section provides you with information on how these functions work. You can also have a look at the examples in the below section. After you have altered `config.py`, follow these steps on how to use it:
* Run `python 3 %PATH TO%/ApkGenerator/apk_generator.py %PATH TO ANDROID FOLDER% %NUMBER OF RUNS% %KEEP TEMP?%`.
* Find the generated APK's in the Outputs folder.
* OPTIONAL: When you have specified to keep the temporary files, you can find the different versions of the Android project folder in the Temp folder.

## Examples
