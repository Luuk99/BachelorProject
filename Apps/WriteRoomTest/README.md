# Write Room Database Test
This app tests the energy consumption of writing data to a Room database by writing 1MB periodically.

## How does it work?
The app writes 1MB of data to the Room database at the specified interval.

## Frequencies
For this app we used the following frequencies:
* Low frequency: 3 seconds
* Medium frequency: 2 seconds
* High frequency: 1 second

## Changing the frequency
You can easily change the frequency by opening the project in Android Studio, go to the MainActivity.kt class and look for the sentence 
```handler.postDelayed(runnable, low_frequency) ```.
Here you can change the frequency by simply substituting ```low_frequency``` with ```medium_frequency``` or ```high_frequency```.

## Benchmark settings
Use phone settings:
* Disable WiFi
* Set screen brightness to its minimum
* Disable location services
