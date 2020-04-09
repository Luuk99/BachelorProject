package e.www.displaytest

import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up videoview
        val videoView = findViewById<VideoView>(R.id.videoView)
        val mc = MediaController(this)
        mc.setAnchorView(videoView)
        mc.setMediaPlayer(videoView)
        videoView.setMediaController(mc)

        // Set the path to the video
        val videoUri: Uri = Uri.parse("android.resource://" + packageName + "/"
                + R.raw.videofile)

        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener(preparedListener)

        videoView.requestFocus()
    }

    // Listen for when the video is prepared
    var preparedListener = OnPreparedListener { m ->
        try {
            if (m.isPlaying) {
                m.stop()
                m.release()
            }
            m.setVolume(0f, 0f)
            m.isLooping = false
            m.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
