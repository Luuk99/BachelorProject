package e.www.mircrophonetest

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


// Audio recorder variables
private val RECORDER_SAMPLERATE = 8000
private val RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO
private val RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT
private lateinit var recorder: AudioRecord

// Audio recorder buffer variables
private val BufferElements2Rec = 1024
private val BytesPerElement = 2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the recorder
        recorder = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            RECORDER_SAMPLERATE, RECORDER_CHANNELS,
            RECORDER_AUDIO_ENCODING, BufferElements2Rec * BytesPerElement
        )

        // Start recording
        recorder.startRecording()
    }

    override fun onStop() {
        // Stop the recorder
        if (::recorder.isInitialized) {
            recorder.stop()
            recorder.release()
        }

        super.onStop()
    }
}
