package com.lixiang.test.kotlindemo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.lixiang.test.kotlindemo.data.Animal
import com.lixiang.test.kotlindemo.data.Bird
import com.lixiang.test.kotlindemo.data.Duck
import com.lixiang.test.kotlindemo.data.Goods
import kotlinx.android.synthetic.main.activity_class_test.*

class ClassTestActivity : AppCompatActivity() {

    lateinit var lunch01: ActivityResultLauncher<Intent>

    lateinit var lunch02: ActivityResultLauncher<String>

    lateinit var lunch03: ActivityResultLauncher<Array<String>>

    lateinit var lunch04: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_test)

        initClass()

        initActivityResult()

    }

    fun initClass() {
        constructorBtn.setOnClickListener {
            Log.d("yys", "constructorBtn")
            var animal: Animal = Bird("小鸟图图", 1)
            animal.callOut()

            //类型判断
            when (animal) {
                is Bird -> {
                    animal.flyUp()
                    animal.flyAway()
                }
                is Duck -> {
                    animal.run()
                }
                is Animal -> {
                    animal.eat()
                }
            }

            //类型强转
            var mybird = animal as Bird
            mybird.flyAway()

            //这里编译不会报错，但是运行会报错
//            var duck = animal as Duck
//            duck.callOut()
        }

    }

    fun initActivityResult(){

        //自定义ActivityResult
        lunch01 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                Log.d("yys", "数据成功返回： ${it.data?.extras?.get("resultdata")}")
            }else{
                Log.d("yys", "数据失败返回")
            }

        }
        dataTransforBtn.setOnClickListener {
            var goods = Goods("笔记本电脑", 10)
            var intent = Intent()
            intent.setClass(this, DataTransforActivity::class.java)
            intent.putExtra("goods", goods);

            lunch01.launch(intent)

            startActivityForResult(intent,0)

        }

        //申请读取联系人的单个权限
        lunch02 = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                Log.d("yys", "获取到通讯录读取权限-成功")
            } else {
                Log.d("yys", "获取到通讯录读取权限-失败")
            }
        }
        contactPermissionBtn.setOnClickListener {
            lunch02.launch(Manifest.permission.READ_CONTACTS)
        }


        //多个权限同时申请
        lunch03 = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            for (item in it) {
                Log.d("yys", "获取 \"${item.key}\" 权限${if (item.value) "成功" else "失败"}")
            }
        }
        multPermissionBtn.setOnClickListener {
            lunch03.launch(
                arrayOf(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }

        //通过文件管理器选择文本文件
        lunch04 = registerForActivityResult(ActivityResultContracts.GetContent()) {
            Log.d("yys", it.toString())
        }
        contentBtn.setOnClickListener {
            lunch04.launch("text/plain")
        }

    }
}