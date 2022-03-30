package com.lixiang.test.kotlindemo.imagepicker

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.lixiang.test.kotlindemo.data.Item

interface IImageLoader {
    fun loadImage(item: Item, imageView: ImageView)
}

class GlideImageLoader private constructor(): IImageLoader{ //私有化构造方法，并使用伴生对象实现单例模式

    companion object{
        val glideImageLoader: GlideImageLoader by lazy {
            GlideImageLoader()
        }
    }

    override fun loadImage(item: Item, imageView: ImageView) {
        Glide.with(imageView).load(item.uri).into(imageView)
    }

}