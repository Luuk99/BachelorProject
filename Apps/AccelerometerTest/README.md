# Accelerometer Test
This app tests the accelerometer energy consumption by creating a listener that listens for changes reported by the accelerometer.

## How does it work?
This app creates a sensor listener for the accelerometer which listens for changes in the sensor.

## Benchmark settings
Use phone settings:
* Disable WiFi
* Set screen brightness to its minimum
* Enable location services (this is needed to use the accelerometer)

Use config.json settings:
* `enable_systrace_parsing` set to True
