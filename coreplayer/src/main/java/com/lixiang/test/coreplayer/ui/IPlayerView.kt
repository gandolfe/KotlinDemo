package com.lixiang.test.coreplayer.ui

import IMediaPlayer
import android.view.TextureView
import androidx.lifecycle.LifecycleObserver
import com.lixiang.test.coreplayer.beans.PlayerState

/**
 * 视频播放容器抽象接口
 */

interface IPlayerView : TextureView.SurfaceTextureListener, LifecycleObserver {

    /**
     * 播放器内核
     */
    var mediaPlayer: IMediaPlayer<*>?

    /**
     * 播放状态
     */
    val playerState: PlayerState

    fun prepare()

    fun start()

    fun pause()

    fun stop()

    fun release()

    fun seekTo(time: Long)

    /**
     * 绑定视图
     */
    fun attach()
}