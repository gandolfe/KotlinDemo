package com.lixiang.test.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_datatpe_test.*

class DataTypeTestActivity: AppCompatActivity() {

    private var count : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datatpe_test)
        loadimg.setOnClickListener { loadimg.text = "点击+$count" ; loadimg.textSize = 30.0f ; count++; Toast.makeText(
            this,
            "一点点小的提示",
            Toast.LENGTH_SHORT
        ).show()}

        handleArray()

        handleList()

        handleMultipleBranches();

        handleGenericFun();

        handleClassTest();

    }

    private fun handleClassTest() {
        classBtn.setOnClickListener { startActivity(Intent(this,ClassTestActivity::class.java)) }
    }

    fun handleArray(){
        var mStringArray : Array<String> = arrayOf("One", "Two", "Three", "Four")
        arrayBtn.setOnClickListener { arrayBtn.text = mStringArray.get(count % 4) ; count++ }
    }
    fun handleList(){
        val goodsList:List<String> = listOf("xiaomi11", "iphone13", "OPPO 11", "FoundX");
        listBtn.setOnClickListener {
            var desc = ""
            for(i in goodsList.indices){
                val item = goodsList[i]
                desc = "${desc}名称: $item \n"
            }
            contenttv.text = desc;
        }

    }

    fun handleMultipleBranches(){
        whenBtn.setOnClickListener {
            whenBtn.text = when(count){
                0,1,2 -> "恭喜发财"
                in 3..6 -> "新年快乐！"
                !in 0..6 -> "大吉大利！"
                else -> "大吉大利！"
            }
            count++
        }
    }

    fun handleGenericFun(){
        funcBtn.setOnClickListener {
            contenttv.text = appendString<String>("四大发明：", "活字印刷术", "造纸术", "指南针", "火药技术")
        }
    }

    fun <T> appendString(tag:String, vararg otherInfo:T?):String{
        var strs:String = "$tag"
        for (item in otherInfo){
            strs = "$strs${item.toString()}"
        }
        return strs
    }

}