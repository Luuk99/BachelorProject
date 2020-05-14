# HTTPS Request Test
This app tests the networking energy consumption by sending HTTPS GET requests.

## How does it work?
This app engages a loop which sends a HTTPS GET request to Google on an interval determined by the frequency provided. 

## Frequencies
For this app we used the following frequencies:
* Low frequency: 3 seconds
* Medium frequency: 2 seconds
* High frequency: 1 second

## Changing the frequency
You can easily change the frequency by opening the project in Android Studio, go to the MainActivity.kt class look for the sentence 
``` handler.postDelayed(runnable, low_frequency) ```.
Here you can change the frequency by simply substituting ```low_frequency``` with ```medium_frequency``` or ```high_frequency```.

## Benchmark settings
Use phone settings:
* Enable WiFi (this is needed for this test)
* Set screen brightness to its minimum
* Disable location services
