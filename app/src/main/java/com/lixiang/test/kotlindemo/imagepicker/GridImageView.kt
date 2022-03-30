package com.lixiang.test.kotlindemo.imagepicker

import android.content.Context
import android.util.AttributeSet

class GridImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int =0) :
    androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        setMeasuredDimension(widthMeasureSpec, heightSpec)
    }


}