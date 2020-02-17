package e.www.mircrophonetest

import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException


// Audio recorder variables
private lateinit var audioRecorder: MediaRecorder

// File path for storing recording
private lateinit var outputFile: String

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set the path for saving the recording
        outputFile = Environment.getExternalStorageDirectory().absolutePath + "/recording.3gp"

        // Create the media recorder
        audioRecorder = MediaRecorder()
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        audioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
        audioRecorder.setOutputFile(outputFile)

        // Start the recorder
        try {
            audioRecorder.prepare()
            audioRecorder.start()
            Log.e("AudioRecorder", "started")
        } catch (ise: IllegalStateException) {
            Log.e("AudioRecorder", ise.toString())
        }
        catch (ioe: IOException) {
            Log.e("AudioRecorder", ioe.toString())
        }
    }

    override fun onStop() {
        // Stop the recorder
        audioRecorder.stop()
        audioRecorder.release()

        super.onStop()
    }
}
