package e.www.cpufactorialtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.math.BigInteger
import kotlin.properties.Delegates

// Constants for the frequencies
const val low_frequency: Long = 3000 // 3 seconds
const val medium_frequency: Long = 2000 // 2 seconds
const val high_frequency: Long = 1000 // 1 second

// Runnable variable for repeating the action
private var runnable: Runnable by Delegates.notNull()

// Handler for infinite loop
private lateinit var handler: Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handler for infinite loop
        handler = Handler()

        // Runnable which computes the factorial
        runnable = Runnable {
            // Compute the factorial
            factorial()

handler.postDelayed(runnable, high_frequency)
        }

        // Start infinite loop
        handler.post(runnable)
    }

    override fun onPause() {
        // Stop the loop
        handler.removeCallbacks(runnable)

        super.onPause()
    }
}

private fun factorial() {
    val num = 7858
    var factorial = BigInteger.ONE
    for (i in 1..num) {
        // factorial = factorial * i;
        factorial = factorial.multiply(BigInteger.valueOf(num.toLong()))
    }
}
