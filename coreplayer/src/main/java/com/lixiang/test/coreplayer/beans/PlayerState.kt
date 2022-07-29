package com.lixiang.test.coreplayer.beans

/**
 * 播放状态的定义
 * sealed 定义的是密封类
 */
sealed class PlayerState(val code: Int) {
    object IDLE : PlayerState(1 shl 1)

    object INITIALIZED : PlayerState(1 shl 2)

    object PREPARING : PlayerState(1 shl 3)

    object PREPARED : PlayerState(1 shl 4)

    object STARTED : PlayerState(1 shl 5)

    object PAUSED : PlayerState(1 shl 6)

    object STOPPED : PlayerState(1 shl 7)

    object COMPLETED : PlayerState(1 shl 8)

    object ERROR : PlayerState(1 shl 9)

    object  END : PlayerState(1 shl 10)
}