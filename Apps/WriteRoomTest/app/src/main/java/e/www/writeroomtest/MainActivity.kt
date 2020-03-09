package e.www.writeroomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.room.Room
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    // Constants for the frequencies
    val low_frequency: Long = 3000 // 3 seconds
    val medium_frequency: Long = 2000 // 2 seconds
    val high_frequency: Long = 1000 // 1 second

    // Runnable variable for repeating the action
    private var runnable: Runnable by Delegates.notNull()

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

        // Handler for infinite loop
        val handler = Handler()

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
        super.onDestroy()

        // Close the database
        database.close()
    }

    override fun onPause() {
        super.onPause()

        // Close the database
        database.close()
    }

    override fun onStop() {
        super.onStop()

        // Close the database
        database.close()
    }

    override fun onResume() {
        super.onResume()

        // Re-initialize the database
        database = Room.databaseBuilder(
                this,
                ThisDatabase::class.java, "thisDatabase"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }
}
