package e.www.accelerometertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    // Accelerometer listener variable
    private lateinit var accelerometerListener: AccelerometerListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the accelerometer listener
        accelerometerListener = AccelerometerListener(this)
    }

    override fun onStart() {
        // Start the accelerometer listener
        accelerometerListener.start()
        super.onStart()
    }

    override fun onStop() {
        // Stop the accelerometer listener
        accelerometerListener.stop()
        super.onStop()
    }
}
