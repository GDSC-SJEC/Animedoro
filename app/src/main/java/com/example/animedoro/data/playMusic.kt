import android.media.MediaPlayer

class playMusic(musicName : R.raw) {

    private val mediaPlayer = MediaPlayer.create(this, musicName)

    fun startMusic(musicName : R.raw) {
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