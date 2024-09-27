package com.example.radioactiva


import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.Player

class MainActivity : AppCompatActivity() {

    private lateinit var player: ExoPlayer
    private lateinit var playPauseButton: ImageButton
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playPauseButton = findViewById(R.id.playPauseButton)

        // Inicializa el reproductor de Media3
        player = ExoPlayer.Builder(this).build()
        val mediaItem = MediaItem.Builder()
            .setUri("ADD YOUR LINK TO RADIO ONLINE")
            .build()
        player.setMediaItem(mediaItem)
        player.prepare()

        // Configura el botón de reproducción/pausa
        playPauseButton.setOnClickListener {
            if (isPlaying) {
                player.pause()
                playPauseButton.setImageResource(androidx.media3.ui.R.drawable.exo_icon_circular_play)
            } else {
                player.play()
                playPauseButton.setImageResource(android.R.drawable.ic_media_pause)
            }
            isPlaying = !isPlaying
        }

        // Listener para el cambio de estado de reproducción
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    playPauseButton.setImageResource(androidx.media3.ui.R.drawable.exo_icon_circular_play)
                    isPlaying = false
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
