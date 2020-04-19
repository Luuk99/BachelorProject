package e.www.writelocaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.io.File
import java.io.FileOutputStream
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

    // Data directory variable
    private lateinit var dir: File
    private lateinit var fileDir: File

    // Variable to keep track of the number of iterations
    private var iterations = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the directory for the data
        dir = File(this.filesDir.absolutePath.toString())
        if (!dir.exists())
            dir.mkdirs()
        fileDir = File(this.filesDir.absolutePath.toString(), "/testData/")
        if (fileDir.exists()) {
            fileDir.delete()
            fileDir.mkdirs()
        } else {
            fileDir.mkdirs()
        }

        // Handler for infinite loop
        handler = Handler()

        // Runnable which stores the data
        runnable = Runnable {
            // Store the data
            storeData()

            // Increase the iterations variable
            iterations += 1

            handler.postDelayed(runnable, high_frequency)
        }

        // Start infinite loop
        handler.post(runnable)
    }

    // Function that stores 1MB of data in the internal storage
    private fun storeData() {
        val file = File(fileDir, "Data" + iterations.toString() + ".txt")
        val os = FileOutputStream(file)
        val data = resources.getString(R.string.data)
        os.write(data.toByteArray())
        os.close()
    }

    override fun onPause() {
        // Stop the loop
        handler.removeCallbacks(runnable)

        // Clear the data when stopping the app
        fileDir.deleteRecursively()

        super.onPause()
    }
}
