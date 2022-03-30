package com.lixiang.test.kotlindemo.imagepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.github.chrisbanes.photoview.PhotoView
import com.lixiang.test.kotlindemo.R
import com.lixiang.test.kotlindemo.data.Item
import com.lixiang.test.kotlindemo.data.OnSelectChangedListener

class LargeAdapter(val datas: ArrayList<Item>, val selectedList: ArrayList<Item>) :
    Adapter<RecyclerView.ViewHolder>() {

    var selectListener: OnSelectChangedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_largeimg_layout, parent, false)
        return LargeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = datas.getOrNull(position)?: return
        if(holder is LargeViewHolder){
            GlideImageLoader.glideImageLoader.loadImage(item, holder.photoView)
            if(selectedList.contains(item)){
                holder.selectTv.mChecked = true
                holder.selectTv.text = (selectedList.indexOf(item) + 1).toString()
            }else{
                holder.selectTv.mChecked = false
                holder.selectTv.text = null
            }

            holder.selectLy.setOnClickListener {
                selectListener?.selectChanged(item)
            }

        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class LargeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoView = itemView.findViewById<PhotoView>(R.id.photoview)
        var selectLy = itemView.findViewById<RelativeLayout>(R.id.large_select_ly)
        var selectTv = itemView.findViewById<SelectTextView>(R.id.large_select_tv)
    }
}