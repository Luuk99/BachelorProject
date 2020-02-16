package e.www.cpufactorialtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlin.properties.Delegates

// Constant for the factorial
val factorial = 78585656

// Constants for the frequencies
val low_frequency: Long = 3000 // 3 seconds
val medium_frequency: Long = 2000 // 2 seconds
val high_frequency: Long = 1000 // 1 second

private var runnable: Runnable by Delegates.notNull()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handler for infinite loop
        val handler = Handler()

        // Runnable which computes the factorial
        runnable = Runnable {
            // Compute the factorial
            factorial(factorial)

            handler.postDelayed(runnable, low_frequency)
        }

        // Start infinite loop
        handler.post(runnable)
    }
}

private fun factorial(num: Int) = (1..num).reduce(Int::times)