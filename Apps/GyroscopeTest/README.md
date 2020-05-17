# Gyroscope Test
This app tests the gyroscope energy consumption by creating a listener that listens for changes reported by this sensor.

## How does it work?
The app creates a sensor listener for the gyroscope which listens for changes in the sensor.

## Benchmark settings
Use phone settings:
* Disable WiFi
* Set screen brightness to its minimum
* Enable location services (this is needed to use the gyroscope)

Use config.json settings:
* `enable_systrace_parsing` set to True
