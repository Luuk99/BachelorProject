package e.www.microphonetest

import android.media.*
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.IOException

// Media recorder variables
private lateinit var recorder: MediaRecorder
private var audioFile: String = ""

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ask permission
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 200)

        // Create audioFile
        audioFile = Environment.getExternalStorageDirectory().path + File.separator + Environment.DIRECTORY_DCIM + File.separator + "testaudio.3gp"

        // Create the recorder
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(audioFile)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e("Mediarecorder", "prepare() failed")
            }

            // Start recording
            start()
        }
    }

    override fun onStop() {
        // Stop the recorder
        if (::recorder.isInitialized) {
            recorder.apply {
                stop()
                release()
            }
        }

        super.onStop()
    }
}
