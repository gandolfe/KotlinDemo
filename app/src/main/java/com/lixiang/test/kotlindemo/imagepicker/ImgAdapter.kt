package com.lixiang.test.kotlindemo.imagepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.lixiang.test.kotlindemo.R
import com.lixiang.test.kotlindemo.data.Item
import com.lixiang.test.kotlindemo.data.OnImgItemClickListener
import com.lixiang.test.kotlindemo.data.OnSelectChangedListener

class ImgAdapter(val datas:ArrayList<Item>, val selectedList: ArrayList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var selectChangedListener: OnSelectChangedListener? = null

    var imgItemClickListener: OnImgItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  ImageHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_gridimg_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ImageHolder){
            GlideImageLoader.glideImageLoader.loadImage(datas[position], holder.iv)
            val item = datas.get(position)
            val select = selectedList.contains(item)
            holder.selectTv.mChecked = select
            if(select){
                holder.selectTv.text = (selectedList.indexOf(item) + 1).toString()
            }else{
                holder.selectTv.text = null
            }

            holder.selectLy.setOnClickListener {
                selectChangedListener?.selectChanged(item)
            }

            holder.itemView.setOnClickListener {
                imgItemClickListener?.imgItemClick(item)
            }
        }

    }

    override fun getItemCount(): Int {
       return datas.size
    }

    class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val iv = itemView.findViewById<ImageView>(R.id.imgview)
        val selectLy = itemView.findViewById<RelativeLayout>(R.id.select_ly)
        val selectTv = itemView.findViewById<SelectTextView>(R.id.select_tv)
    }

}