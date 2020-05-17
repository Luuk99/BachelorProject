# Gravity Sensor Test
This app tests the gravity sensor energy consumption by creating a listener that listens for changes reported by this sensor.

## How does it work?
The app creates a sensor listener for the gravity sensor which listens for changes in the sensor.

## Benchmark settings
Use phone settings:
* Disable WiFi
* Set screen brightness to its minimum
* Enable location services (this is needed to use the gravity sensor)

Use config.json settings:
* `enable_systrace_parsing` set to True
