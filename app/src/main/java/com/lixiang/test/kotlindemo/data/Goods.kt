package com.lixiang.test.kotlindemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Goods(var name:String, var number:Int) : Parcelable{

}
