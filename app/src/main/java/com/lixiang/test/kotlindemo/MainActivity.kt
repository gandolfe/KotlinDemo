package com.lixiang.test.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.lixiang.test.kotlindemo.imagepicker.ImgPickerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lunch01: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lunch01 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                var selectDatas = it.data?.getStringArrayExtra("selectdata")
                var textvalue = selectDatas?.joinToString()
                textvalue?.let { it1 -> Log.i("yys", it1) }
                Toast.makeText(this, textvalue, Toast.LENGTH_LONG).show()
            }
        }

        datatypetest.setOnClickListener {
            startActivity(Intent(this, ClassTestActivity::class.java))
        }

        imgpicker.setOnClickListener {
            lunch01?.launch(Intent(this, ImgPickerActivity::class.java))
        }

    }
}