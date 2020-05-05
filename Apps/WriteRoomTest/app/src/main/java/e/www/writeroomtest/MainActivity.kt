package e.www.writeroomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.room.Room
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

    // Variables for the database
    lateinit var database: ThisDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the database
        database = Room.databaseBuilder(
                this,
        ThisDatabase::class.java, "thisDatabase"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        // Empty the database
        database.thisDatabaseInterface().deleteAll()

        // Handler for infinite loop
        handler = Handler()

        // Runnable which stores the data
        runnable = Runnable {
            // Store the data
            storeData()

            handler.postDelayed(runnable, low_frequency)
        }

        // Start infinite loop
        handler.post(runnable)
    }

    // Function that stores 1MB of data in the room database
    private fun storeData() {
        // Create an entry for the database
        val entry = ThisDatabaseEntity(0, resources.getString(R.string.data))

        // Store the entry in the database
        database.thisDatabaseInterface().addEntry(entry)
    }

    override fun onDestroy() {
        // Stop the loop
        handler.removeCallbacks(runnable)

        // Close the database
        database.close()

        super.onDestroy()
    }

    override fun onPause() {
        // Stop the loop
        handler.removeCallbacks(runnable)

        // Close the database
        database.close()

        super.onPause()
    }

    override fun onStop() {
        // Stop the loop
        handler.removeCallbacks(runnable)

        // Close the database
        database.close()

        super.onStop()
    }

    override fun onResume() {
        // Start the loop
        handler.post(runnable)

        // Re-initialize the database
        database = Room.databaseBuilder(
                this,
                ThisDatabase::class.java, "thisDatabase"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        super.onResume()
    }
}
