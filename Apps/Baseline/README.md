# Baseline App
This app is used to acquire a baseline energy consumption from the phone.

## How does it work?
This app does nothing and like the other apps has no UI at all, therefore it should consume no power and therefore the base power consumption of the phone can be measured.

## Benchmark settings
Use phone settings:
* Disable WiFi
* Set screen brightness to its minimum
* Disable location services

Use config.json settings:
* `enable_systrace_parsing` set to True
