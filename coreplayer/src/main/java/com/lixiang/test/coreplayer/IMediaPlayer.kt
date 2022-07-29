import android.content.Context
import android.net.Uri
import android.view.Surface
import androidx.lifecycle.LiveData
import com.lixiang.test.coreplayer.beans.PlayerState

interface IMediaPlayer<T> {
    /**
     * 播放实例
     */
    var impl : T

    /**
     * 准备好后是否播放
     */
    var playWhenReady : Boolean

    /**
     * 是否正在播放
     */
    val isPlaying : Boolean

    /**
     * 当前播放位置
     */
    var currentPosition : Long

    /**
     * 当前播放状态
     */
    var playState : PlayerState

    val playerStateLD : LiveData<PlayerState>

    fun setDataSource(context: Context?, uri:Uri?)

    /**
     * 开始播放
     */
    fun start()

    /**
     * 同步准备
     */
    fun prepare()

    /**
     * 异步准备
     */
    fun prepareAsync()

    /**
     * 暂停
     */
    fun pause()

    /**
     * 停止
     */
    fun stop()

    /**
     * 跳转到指定位置
     */
    fun seekTo(time : Long)

    /**
     * 重置资源
     */
    fun reset()

    /**
     * 释放
     */
    fun release()

    /**
     * 设置播放容器
     */
    fun setSurface(surface : Surface?)
}