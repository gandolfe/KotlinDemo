package com.lixiang.test.kotlindemo.imagepicker

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.lixiang.test.kotlindemo.data.FolderItem
import com.lixiang.test.kotlindemo.data.Item
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

class Scanner(val context: Context) {

    private val isScan = AtomicBoolean(false)
    private val mImageList = ArrayList<Item>()
    private val mHandler = Handler(Looper.getMainLooper())
    private var onScanResult: ((List<FolderItem>) -> Int)? = null

    fun setOnScanResult(onScanResult: (List<FolderItem>) -> Int){
        this.onScanResult = onScanResult
    }

    fun startScan() {
        if (isScan.get()) return
        thread {
            synchronized(this) {
                isScan.set(true)
                val images = ImageResource().scan(context).sortedByDescending { it.time }
                val changed = !images.toTypedArray().contentEquals(mImageList.toTypedArray())
                if (changed) {
                    Log.i("yys", "data has changed")
                    mImageList.clear()
                    mImageList.addAll(images)
                    val folderList = (arrayOf(FolderItem("All", mImageList))
                            + mImageList.groupBy { it.getFloderName() }
                        .map { entry -> FolderItem(entry.key, ArrayList(entry.value)) })
                        .toList()
                    mHandler.post {
                        onScanResult?.let { it(folderList) }
                    }
                }
                isScan.set(false)
            }
        }
    }

}