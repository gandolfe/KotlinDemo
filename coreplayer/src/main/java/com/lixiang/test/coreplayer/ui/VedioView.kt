package com.lixiang.test.coreplayer.ui

import IMediaPlayer
import android.content.Context
import android.graphics.SurfaceTexture
import android.util.AttributeSet
import android.widget.FrameLayout
import com.lixiang.test.coreplayer.beans.PlayerState

class VedioView : FrameLayout, IPlayerView {

    constructor(context: Context) :this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override var mediaPlayer: IMediaPlayer<*>? = null
    override val playerState: PlayerState
        get() = mediaPlayer?.playerStateLD?.value ?: PlayerState.IDLE

    override fun prepare() {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    override fun seekTo(time: Long) {
        TODO("Not yet implemented")
    }

    override fun attach() {
        TODO("Not yet implemented")
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
        TODO("Not yet implemented")
    }

}