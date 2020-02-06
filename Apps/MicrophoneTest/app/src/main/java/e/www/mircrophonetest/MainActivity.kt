package e.www.mircrophonetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// Constants for the frequencies
val low_frequency = 1000
val medium_frequency = 2000
val high_frequency = 3000

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
