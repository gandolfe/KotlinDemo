package com.lixiang.test.kotlindemo.imagepicker

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import com.lixiang.test.kotlindemo.data.Item
import java.io.File

class ImageResource : Resource {

    override fun scan(context:Context) : ArrayList<Item> {
        val mContentResolver = context.contentResolver ?: return emptyList<Item>() as ArrayList<Item>
        Log.i("yys", "start scan images")
        val mCursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            , arrayOf(MediaStore.Images.Media._ID
                ,MediaStore.Images.Media.DATA
                ,MediaStore.Images.Media.DISPLAY_NAME
                ,MediaStore.Images.Media.DATE_ADDED
                ,MediaStore.Images.Media.MIME_TYPE
                ,MediaStore.Images.Media.SIZE)
            ,MediaStore.Images.Media.SIZE + " > 0"
            ,null
            ,MediaStore.Images.Media.DATE_ADDED + " DESC")

        val images = ArrayList<Item>()

        if (mCursor != null){
            while (mCursor.moveToNext()){
                val item = mapToItemData(mContentResolver, mCursor);
                Log.i("yys", item?.name + " ; " + item?.path)
                if(item != null){
                    images.add(item)
                }
            }
        }
        mCursor?.close()
        return images
    }

    fun mapToItemData(mContentResolver: ContentResolver, mCursor: Cursor) : Item? {
        val id = mCursor.getLong(mCursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
        val path = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
        val name = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
        val time = mCursor.getLong(mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED))
        val mimetype = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE))
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(id.toString()).build();
        if(File(path).exists() || mContentResolver.openFileDescriptor(uri, "r") != null){
            return Item(path, uri, name, mimetype, time)
        }
        return null
    }

}