# GPS Test
This app tests the GPS energy consumption by requesting location updates periodically.

## How does it work?
The app requests location updates from the gps at the specified frequency and priority.

## Frequencies
For this app we used the following frequencies:
* Low frequency: 3 seconds
* Medium frequency: 2 seconds
* High frequency: 1 second

## Priorities
For this app we used standard location request priority constants provided by the location services as specified in the [documentation](https://developers.google.com/android/reference/com/google/android/gms/location/LocationRequest):
* Low priority: ``` LocationRequest.PRIORITY_LOW_POWER ```, this is used to request "city" level accuracy.
* Medium priority: ``` LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY ```, this is used to request "block" level accuracy.
* High priority: ``` LocationRequest.PRIORITY_HIGH_ACCURACY ```, this is used to request the most accurate locations available.

## Changing the frequency
You can easily change the frequency by opening the project in Android Studio, go to the MainActivity.kt class and look for the sentence 
```startLocationUpdates(low_frequency, low_priority) ```.
Here you can change the frequency by simply substituting ```low_frequency``` with ```medium_frequency``` or ```high_frequency```.

## Changing the priority 
You can easily change the priority by opening the project in Android Studio, go to the MainActivity.kt class and look for the sentence
```startLocationUpdates(low_frequency, low_priority) ```.
Here you can change the frequency by simply substituting ```low_priority``` with ```medium_priority``` or ```high_priority```.
