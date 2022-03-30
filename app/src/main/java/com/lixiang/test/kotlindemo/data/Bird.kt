package com.lixiang.test.kotlindemo.data

import android.util.Log
import com.lixiang.test.kotlindemo.behavior.FlyBehavior

class Bird(name:String, age:Int, sex:Int = 0) : Animal(age,name,sex),FlyBehavior {

    init {
        Log.d("yys","这是一只鸟");
    }

    override fun eat() {
        Log.d("yys","小鸟吃虫子");
    }

    override fun callOut() {
        Log.d("yys","小鸟\"唧唧\"叫");
    }

    override fun flyUp() {
        Log.d("yys","小鸟飞起来了，可以飞到树梢上")
    }

    override fun flyDown() {
        Log.d("yys","小鸟降落了，可降落在树干上")
    }
}