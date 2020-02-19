package e.www.magneticfieldtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    // Magnetic field listener variable
    private lateinit var magneticFieldListener: MagneticFieldListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the magnetic field listener
        magneticFieldListener = MagneticFieldListener(this)
    }

    override fun onStart() {
        // Start the magnetic field listener
        magneticFieldListener.start()
        super.onStart()
    }

    override fun onStop() {
        // Stop the magnetic field listener
        magneticFieldListener.stop()
        super.onStop()
    }
}
