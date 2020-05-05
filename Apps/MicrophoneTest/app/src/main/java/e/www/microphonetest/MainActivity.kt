package e.www.microphonetest

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import java.io.File
import java.io.IOException

// Media recorder variables
private lateinit var recorder: MediaRecorder
private var audioFile: String = ""

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check permissions
        checkPermissions()
    }

    private fun checkPermissions() {
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) ==
                      PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                      Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
          startRecording()
      }
      else {
          checkPermissions()
      }
    }

    private fun startRecording() {
        // Create audioFile
        audioFile = getExternalFilesDir(Environment.DIRECTORY_DCIM)?.absolutePath + File.separator + "testaudio.3gp"

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
