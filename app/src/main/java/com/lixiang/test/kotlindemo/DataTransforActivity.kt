package com.lixiang.test.kotlindemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lixiang.test.kotlindemo.data.Goods
import kotlinx.android.synthetic.main.activity_datatransfor_layout.*

class DataTransforActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_datatransfor_layout)

        val goods = intent.extras?.getParcelable<Goods>("goods")
        if (goods != null) {
            Log.d("yys","goods:${goods.name} and number:${goods.number}")
        }

        resultBtn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("resultdata","this is a result message")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}