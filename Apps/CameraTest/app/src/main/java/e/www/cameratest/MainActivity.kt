package e.www.cameratest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore

// Constants for the frequencies
val low_frequency = 1000
val medium_frequency = 2000
val high_frequency = 3000

// Image capture request argument
var REQUEST_IMAGE_CAPTURE = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // For loop which creates the photos
        while (REQUEST_IMAGE_CAPTURE <= low_frequency) {
            dispatchTakePictureIntent()

            REQUEST_IMAGE_CAPTURE += 1
        }
    }

    // Takes a picture
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }
}
