# Write SD Test
This app tests the energy consumption of writing data to the SD card by writing 1KB periodically.

## How does it work?
The app writes 1KB of data to the SD card at the specified interval.

## Frequencies
For this app we used the following frequencies:
* Low frequency: 3 seconds
* Medium frequency: 2 seconds
* High frequency: 1 second

## Changing the frequency
You can easily change the frequency by opening the project in Android Studio, go to the MainActivity.kt class and look for the sentence 
```handler.postDelayed(runnable, low_frequency) ```.
Here you can change the frequency by simply substituting ```low_frequency``` with ```medium_frequency``` or ```high_frequency```.