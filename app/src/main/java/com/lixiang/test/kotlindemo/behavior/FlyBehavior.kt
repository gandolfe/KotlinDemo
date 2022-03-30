package com.lixiang.test.kotlindemo.behavior

import android.util.Log

interface FlyBehavior {

    fun flyUp()

    fun flyDown()

    fun flyAway():String{
        Log.d("yys","飞啊，飞啊，飞走了！")
        return "飞啊，飞啊，飞走了！"
    }
}