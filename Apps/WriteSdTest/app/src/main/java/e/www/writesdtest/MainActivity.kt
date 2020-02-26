package e.www.writesdtest

import android.os.Bundle
import android.os.Environment
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    // Constants for the frequencies
    val low_frequency: Long = 3000 // 3 seconds
    val medium_frequency: Long = 2000 // 2 seconds
    val high_frequency: Long = 1000 // 1 second

    // Runnable variable for repeating the action
    private var runnable: Runnable by Delegates.notNull()

    // Data directory variable
    private lateinit var dir: File

    // Variable to keep track of the number of iterations
    private var iterations = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the directory for the data
        val sdcard: File = Environment.getExternalStorageDirectory()
        dir = File(sdcard.absolutePath.toString() + "/test-data/")
        dir.mkdir()

        // Handler for infinite loop
        val handler = Handler()

        // Runnable which stores the data
        runnable = Runnable {
            // Store the data
            storeData()

            // Increase the iterations variable
            iterations += 1

            handler.postDelayed(runnable, low_frequency)
        }

        // Start infinite loop
        handler.post(runnable)
    }

    // Functions that stores 1KB of data on the sd card
    private fun storeData() {
        val file = File(dir, "Data" + iterations.toString() + ".txt")
        val os = FileOutputStream(file)
        val data = resources.getString(R.string.data)
        os.write(data.toByteArray())
        os.close()
    }

    override fun onStop() {
        super.onStop()

        // Clear the data when stopping the app
        dir.deleteRecursively()
    }
}
