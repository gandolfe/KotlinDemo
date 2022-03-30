package com.lixiang.test.kotlindemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lixiang.test.kotlindemo.imagepicker.ImgPickerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datatypetest.setOnClickListener {
            startActivity(Intent(this, DataTransforActivity::class.java))
        }

        imgpicker.setOnClickListener {
            startActivity(Intent(this, ImgPickerActivity::class.java))
        }

    }
}