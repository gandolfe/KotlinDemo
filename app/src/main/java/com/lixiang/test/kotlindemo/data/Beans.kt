package com.lixiang.test.kotlindemo.data

import android.net.Uri
import java.io.File
import java.io.Serializable

data class Item(
    val path: String,
    val uri: Uri,
    val name: String?,
    val mimetype: String,
    val time: Long
) : Serializable {
    fun getFloderName(): String {
        val paths = path?.split(File.separator)
        if (paths.size >= 2) {
            return paths[paths.size - 2]
        }
        return ""
    }
}

data class FolderItem(val name: String, val items: ArrayList<Item> = ArrayList<Item>()) : Serializable

interface OnSelectChangedListener{
    fun selectChanged(item: Item)
}

interface OnFolderSelectListener{
    fun selectFolder(folderItem: FolderItem)
}

interface OnImgItemClickListener{
    fun imgItemClick(item: Item)
}