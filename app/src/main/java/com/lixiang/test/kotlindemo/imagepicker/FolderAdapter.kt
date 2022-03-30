package com.lixiang.test.kotlindemo.imagepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lixiang.test.kotlindemo.R
import com.lixiang.test.kotlindemo.data.FolderItem
import com.lixiang.test.kotlindemo.data.OnFolderSelectListener

class FolderAdapter(val folders: ArrayList<FolderItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mFolderSelectListener: OnFolderSelectListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_folder_layout, parent, false)
        return  FolderHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is FolderHolder){
            GlideImageLoader.glideImageLoader.loadImage(folders.get(position).items[0], holder.folderImgView)
            holder.folderNameTv.text = folders.get(position).name
            holder.folderimgCount.text = "(${folders.get(position).items.size})"
            holder.itemView.setOnClickListener {
                mFolderSelectListener?.selectFolder(folders.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return folders.size
    }


    class FolderHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val folderImgView = itemView.findViewById<ImageView>(R.id.folder_iv)
        val folderNameTv = itemView.findViewById<TextView>(R.id.folder_name)
        val folderimgCount = itemView.findViewById<TextView>(R.id.folder_itemcount)
    }

}