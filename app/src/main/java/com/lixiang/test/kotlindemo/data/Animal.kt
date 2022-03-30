package com.lixiang.test.kotlindemo.data

import android.util.Log

abstract class Animal constructor(var age:Int, var name:String, var sex:Int = 0){

    init {
        Log.d("yys","这只动物的名字是:$name, 它的年龄是:$age, 并且它还是一只${if (sex==0) "公的" else "母的"}")
    }

    abstract fun eat()

    abstract fun callOut()
}