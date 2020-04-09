package e.www.speakertest

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    // Media player to play the audio file
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the media player
        mediaPlayer = MediaPlayer.create(this, R.raw.audiofile)
        mediaPlayer?.setOnPreparedListener {
            mediaPlayer?.start()
        }
    }

    override fun onPause() {
        // Stop the audio
        mediaPlayer?.stop()

        super.onPause()
    }
}
