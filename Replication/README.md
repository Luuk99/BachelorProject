# Replication
To replicate the experiment, use the apk's and Android Runner config file found in this subdirectory.

## Benchmark settings
Use phone settings:
* Disable WiFi
* Set screen brightness to its minimum
* Disable location services

Use config.json settings:
* enable_systrace_parsing set to True

## Exceptions
* Accelerometer: enable location services
* GPS: enable location services
* Gravity: enable location services
* Gyroscope: enable location services
* Networking: enable WiFi
* Magnetic field: enable location services
* Speaker: set phone's media volume to 50%
