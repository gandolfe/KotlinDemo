package com.lixiang.test.kotlindemo.imagepicker

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatTextView

class SelectTextView @JvmOverloads constructor (context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatTextView(context, attrs, defStyleAttr), Checkable {

    var mChecked = false

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableStates = super.onCreateDrawableState(extraSpace + 1) //添加一个状态
        if(mChecked){
            mergeDrawableStates(drawableStates, intArrayOf(android.R.attr.state_checked))
        }
        return drawableStates

    }

    override fun setChecked(p0: Boolean) {
        mChecked = p0
        refreshDrawableState()  //触发状态的刷新
    }

    override fun isChecked(): Boolean {
        return mChecked
    }

    override fun toggle() {
        TODO("Not yet implemented")
    }
}