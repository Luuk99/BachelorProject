package e.www.httpsrequesttest

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL
import kotlin.properties.Delegates

// Constants for the frequencies
const val low_frequency: Long = 3000 // 3 seconds
const val medium_frequency: Long = 2000 // 2 seconds
const val high_frequency: Long = 1000 // 1 second

class MainActivity : AppCompatActivity() {

    // Runnable variable for repeating the action
    private var runnable: Runnable by Delegates.notNull()

    // Handler for infinite loop
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handler for infinite loop
        handler = Handler()

        // Runnable which sends https request
        runnable = Runnable {
            // Send https request
            sendGet()

            handler.postDelayed(runnable, medium_frequency)
        }

        // Start infinite loop
        handler.post(runnable)
    }

    private fun sendGet() {
        GlobalScope.launch(Dispatchers.IO) {
            val url = URL("https://www.google.com/")

            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"
            }
        }
    }

    override fun onPause() {
        // Stop the loop
        handler.removeCallbacks(runnable)

        super.onPause()
    }
}
