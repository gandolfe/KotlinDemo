package com.lixiang.test.kotlindemo.imagepicker

import android.content.Context
import com.lixiang.test.kotlindemo.data.Item

interface Resource {

    fun scan(context:Context) : ArrayList<Item>

}