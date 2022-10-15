package com.example.animedoro

import android.content.Context
import android.media.MediaPlayer

class playMusic(musicName : Int, context: Context) {

    private val mediaPlayer = MediaPlayer.create(context, musicName)

    fun startMusic() {
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    fun pauseMusic() {
        mediaPlayer.pause()
    }

    fun playMusic() {
        mediaPlayer.seekTo(mediaPlayer.currentPosition)
        mediaPlayer.start()
    }

    fun stopMusic() {
        mediaPlayer.stop()
    }
}