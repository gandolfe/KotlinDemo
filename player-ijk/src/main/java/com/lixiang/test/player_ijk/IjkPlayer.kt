package com.lixiang.test.player_ijk

import IMediaPlayer
import android.content.Context
import android.net.Uri
import android.view.Surface
import androidx.lifecycle.MutableLiveData
import com.lixiang.test.coreplayer.beans.PlayerState
import tv.danmaku.ijk.media.player.IjkMediaPlayer

open class IjkPlayer :  IMediaPlayer<IjkMediaPlayer>,
    tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener,
    tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener,
    tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener,
    tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener,
    tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener,
    tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener,
    tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener{
    override var impl: IjkMediaPlayer = IjkMediaPlayer()
    override var playWhenReady: Boolean = true
    override val playerStateLD: MutableLiveData<PlayerState> = MutableLiveData()
    override val isPlaying: Boolean  //val关键字定义的成员字段，不能set，但是可以修改get来达到动态变化的目的。但不推荐这类使用，而应该用方法代替
        get() {
            try {
                return impl.isPlaying
            }catch (e : Exception){}
            return false
        }
    override var currentPosition: Long
        get() {
            return impl.currentPosition
        }
        set(value) {}
    override var playState: PlayerState
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun start() {
        try {
            impl.start()
            playerStateLD.value = PlayerState.STARTED
        } catch (e : IllegalStateException){
            e.printStackTrace()
        }
    }

    override fun prepare() {
        TODO("Not yet implemented")
    }

    override fun prepareAsync() {
        try {
            impl.prepareAsync()
            playerStateLD.value = PlayerState.PREPARING
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun seekTo(time: Long) {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    override fun setSurface(surface: Surface?) {
        TODO("Not yet implemented")
    }

    override fun setDataSource(context: Context?, uri: Uri?) {
        impl.setDataSource(context, uri)
        playerStateLD.value = PlayerState.INITIALIZED
    }

    override fun onPrepared(p0: tv.danmaku.ijk.media.player.IMediaPlayer?) {
        TODO("Not yet implemented")
    }

    override fun onCompletion(p0: tv.danmaku.ijk.media.player.IMediaPlayer?) {
        TODO("Not yet implemented")
    }

    override fun onBufferingUpdate(p0: tv.danmaku.ijk.media.player.IMediaPlayer?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onSeekComplete(p0: tv.danmaku.ijk.media.player.IMediaPlayer?) {
        TODO("Not yet implemented")
    }

    override fun onError(p0: tv.danmaku.ijk.media.player.IMediaPlayer?, p1: Int, p2: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun onInfo(p0: tv.danmaku.ijk.media.player.IMediaPlayer?, p1: Int, p2: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun onVideoSizeChanged(
        p0: tv.danmaku.ijk.media.player.IMediaPlayer?,
        p1: Int,
        p2: Int,
        p3: Int,
        p4: Int
    ) {
        TODO("Not yet implemented")
    }
}