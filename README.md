# BachelorProject
Bachelor project of Luuk Kaandorp for the study Information Sciences (previously IMM) at the VU Amsterdam. In collaboration with Ivano Malavolta.

## The goal
The goal of the project is to assess the whether Android Runner can accurately measure energy consumption. We do this by creating synthetic apps which test hardware components of the phone. We then measure this using Android Runner and compare this to our base test results which where measured directly from the phone's hardware.

## The apps
* [Baseline](/Apps/Baseline)
* [Accelerometer](/Apps/AccelerometerTest)
* [Ambient Light Sensor](/Apps/AmbientLightTest)
* [Camera](/Apps/CameraTest)
* [CPU Factorial](/Apps/CpuFactorialTest)
* [Display](/Apps/DisplayTest)
* [GPS](/Apps/GpsTest)
* [Gravity Sensor](/Apps/GravityTest)
* [Gyroscope](/Apps/GyroscopeTest)
* [HTTPS Network](/Apps/HttpsRequestTest)
* [Local Storage](/Apps/WriteLocalTest)
* [Magnetic Field Sensor](/Apps/MagneticFieldTest)
* [Microphone](/Apps/MicrophoneTest)
* [Room Database](/Apps/WriteRoomTest)
* [SD-Card](/Apps/WriteSdTest)
* [Speaker](/Apps/SpeakerTest)

## Reproduction
Before reproduction, the experiments can only be conducted on linux distributions (preferably Ubuntu). The experiments can be replicated by following these steps:
* Install Android Studio: this can be done from the Ubuntu Software program or from the [official website](https://developer.android.com/studio/install).
* Install Android Runner: this can be done by following the steps provided on the [official github page](https://github.com/S2-group/android-runner).
* Add your device to devices.json: TODO
* Get the power_profile.xml from your phone:
  1. Open Android Studio. Head to the Device File Explorer tab in the bottom right of your screen. Navigate to the sytem folder -> framework folder -> Grab the framework-res.apk and copy it to your PC.
  2. Install APKTool: this can be done by following the steps provided on the [official website](https://ibotpeaches.github.io/Apktool/install/).
  3. OPTIONAL: if the apktool does not work in itself, try running `java -jar /usr/local/bin/apktool.jar` instead of `apktool`.
  4. Decode the framework-res.apk file: this can be done by navigating to the directory containing this file inside a terminal. Then execute `java -jar /usr/local/bin/apktool.jar d framework-res.apk`.
  5. Find power_profile.xml: the previous step should have created a framework-res folder in your directory. Find and copy power_profile.xml to the `android-runner/examples/batterystats/Scripts` folder.
* The following steps need to be done for every experiment:
  1. Add APK to batterystats folder: this can be done by building a project from Android Studio and moving the APK file to `android-runner/examples/batterystats`. Rename the apk so that it confirms to this template `%PACKAGE NAME%.apk`. For example, the baseline app needs to be named `e.www.baseline.apk`.
  2. Add APK to config.json: this can be done by opening config.json in the `android-runner/examples/batterystats` folder with a text editor and set `"paths" : [ "%DEFAULT PATH%" ]` to the path of your APK file. Since we placed the APK in the same folder, we can simply add `"android-runner/examples/batterystats/%APK NAME%.apk"` as our path.
  3. ON THE FIRST EXPERIMENT: when you alter the config.json file for the first time, you have to alter some lines. Make sure that `"replications" : 30`. Replace the default device in `"devices" : { "%DEFAULT DEVICE%": {} }` with your own device, remember to use the same name as you have entered in the devices.json file. Set `"duration" : 180000` and `"time_between_run" : 120000`. 
