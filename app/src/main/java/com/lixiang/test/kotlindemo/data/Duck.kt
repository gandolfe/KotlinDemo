package com.lixiang.test.kotlindemo.data

import android.util.Log
import com.lixiang.test.kotlindemo.behavior.RunBehavior

class Duck constructor(age:Int, name:String, sex:Int = 0) : Animal(age,name), RunBehavior {

    init {
        Log.d("yys","$name 是一直鸭子")
    }

    override fun eat() {
        Log.d("yys","鸭子$name 吃鱼");
    }

    override fun callOut() {
        Log.d("yys","鸭子嘎嘎嘎")
    }

    override fun run() {
        super.run()
        Log.d("yys","鸭子$name 屁股一摇一摆的")
    }
}