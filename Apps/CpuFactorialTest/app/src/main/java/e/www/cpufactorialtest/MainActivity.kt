package e.www.cpufactorialtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.security.Timestamp

// Constants for the frequencies
val low_frequency = 1000
val medium_frequency = 2000
val high_frequency = 3000

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Log the starting time of the test
        Log.e("Starting Time", System.currentTimeMillis().toString())

        // Start the factorial calculation
        factorial(low_frequency)

        // Log the end time of the test
        Log.e("End Time", System.currentTimeMillis().toString())
    }
}

private fun factorial(num: Int) = (1..num).reduce(Int::times)