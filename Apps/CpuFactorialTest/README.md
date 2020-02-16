# CPU Factorial Test
This app tests the CPU energy consumption by computing large factorials.

## How does it work?
This app engages a loop which calculates the factorial of 78585656 on an interval determined by the frequency provided. 

## Frequencies
For this app we used the following frequencies:
* Low frequency: 3 seconds
* Medium frequency: 2 seconds
* High frequency: 1 second

## Changing the frequency
You can easily change the frequency by opening the project in Android Studio, go to the MainActivity.kt class look for the sentence 
``` handler.postDelayed(runnable, low_frequency) ```.
Here you can change the frequency by simply substituting ```low_frequency``` with ```medium_frequency``` or ```high_frequency```.
