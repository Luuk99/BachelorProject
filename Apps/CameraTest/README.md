# Camera Test
This app tests the camera energy consumption by taking pictures.

## How does it work?
This app engages a loop which takes a picture on an interval determined by the frequency provided. 

## Frequencies
For this app we used the following frequencies:
* Low frequency: 15 seconds
* Medium frequency: 10 seconds
* High frequency: 5 seconds

## Changing the frequency
You can easily change the frequency by opening the project in Android Studio, go to the MainActivity.kt class look for the sentence 
``` handler.postDelayed(runnable, low_frequency) ```.
Here you can change the frequency by simply substituting ```low_frequency``` with ```medium_frequency``` or ```high_frequency```.