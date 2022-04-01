package com.lixiang.test.kotlindemo.imagepicker

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.lixiang.test.kotlindemo.R
import com.lixiang.test.kotlindemo.data.*
import kotlinx.android.synthetic.main.activity_img_picker.*

class ImgPickerActivity : AppCompatActivity() {

    private val mScanner by lazy { Scanner(applicationContext) }

    private val mFolderItems = ArrayList<FolderItem>();

    private val mImgItems = ArrayList<Item>();

    private val mSelectedItems = ArrayList<Item>()

    private val mImgAdapter by lazy { ImgAdapter(mImgItems, mSelectedItems) }

    private var mRecyclerView: RecyclerView? = null

    private var mFolderRecyclerView: RecyclerView? = null

    private val mFolderAdapter by lazy { FolderAdapter(mFolderItems) }

    private var mLargeRecyclerView: RecyclerView? = null

    private val mLargeAdapter by lazy { LargeAdapter(mImgItems, mSelectedItems) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img_picker)
        supportActionBar?.hide()
        actionBar?.hide()

        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            initView()
            loadPictureData()
        }else{
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) {
                    Log.d("yys", "存储权限获取成功！")
                    initView()
                    loadPictureData()
                } else {
                    Log.w("yys", "存储权限获取失败！")
                    Toast.makeText(this,R.string.permission_alert,Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    fun initView(){
        mRecyclerView = findViewById(R.id.recycleView)
        mRecyclerView?.let {
            it.layoutManager = GridLayoutManager(this, 5)
            it.adapter = mImgAdapter
            mImgAdapter.selectChangedListener = mSelectChangedListener
            mImgAdapter.imgItemClickListener = mImgItemClickListener
        }

        mFolderRecyclerView = findViewById(R.id.folderRecycleView)
        mFolderRecyclerView?.let {
            it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            it.adapter = mFolderAdapter
            mFolderAdapter.mFolderSelectListener = mFolderSelectListener
            var decoration = object : RecyclerView.ItemDecoration(){
                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    super.onDraw(c, parent, state)
                    var paint = Paint()
                    paint.isAntiAlias = true
                    paint.color = resources.getColor(R.color.colorWhite_per_30, null)
                    paint.style = Paint.Style.STROKE
                    paint.strokeWidth = 2f
                    paint.pathEffect = DashPathEffect(floatArrayOf(8f,8f), 0f)
                    var childCount = parent.childCount
                    for (i in 0 until childCount){
                        var param = parent.get(i).layoutParams as RecyclerView.LayoutParams
                        var bottom = parent.get(i).bottom + param.bottomMargin
                        c.drawLine(0f, bottom.toFloat(), parent.width.toFloat() - 500, bottom.toFloat(), paint)
                    }

                }

                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.set(0, 6, 0, 6)
                }
            }
            mFolderRecyclerView?.addItemDecoration(decoration)
        }

        mLargeRecyclerView = findViewById(R.id.largeRecycleView)
        mLargeRecyclerView?.let {
            PagerSnapHelper().attachToRecyclerView(it)
            it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = mLargeAdapter
            it.setHasFixedSize(true)
            mLargeAdapter.selectListener = mSelectChangedListener
        }

        folderMoreLay.setOnClickListener {
            mLargeRecyclerView?.visibility = View.GONE
            if(mFolderRecyclerView?.isShown == true){
                showFolderRecyclerView(false)
            }else{
                showFolderRecyclerView(true)
            }
        }

        reback.setOnClickListener {
            if(mFolderRecyclerView?.isShown == true){
                showFolderRecyclerView(false)
                return@setOnClickListener
            }
            if(mLargeRecyclerView?.isShown == true){
                mLargeRecyclerView?.visibility = View.GONE
                mRecyclerView?.visibility = View.VISIBLE
                return@setOnClickListener
            }
            finish()
        }

        selectCheck.setOnClickListener {
            var resultitems = mSelectedItems.map { it.path }
            setResult(RESULT_OK, Intent().putExtra("selectdata", resultitems.toTypedArray()))
            finish()
        }

    }

    fun loadPictureData(){
        mScanner.setOnScanResult { list ->
            if(!isFinishing && !isDestroyed){
                mFolderItems.clear()
                mSelectedItems.clear()
                mImgItems.clear()
                refreshSelectCheck()
                mFolderItems.addAll(list)
                mImgItems.addAll(mFolderItems[0].items)
                mImgAdapter.notifyDataSetChanged()
            }
            1
        }
        mScanner.startScan()
    }

   private var mSelectChangedListener = object : OnSelectChangedListener{
        override fun selectChanged(item: Item) {
            if(mSelectedItems.contains(item)){
                mSelectedItems.remove(item)
            }else{
                mSelectedItems.add(item)
            }
            refreshSelectCheck()
            mImgAdapter.notifyItemChanged(mImgAdapter.datas.indexOf(item))
            mLargeAdapter.notifyItemChanged(mLargeAdapter.datas.indexOf(item))
        }

    }

   private var mFolderSelectListener = object : OnFolderSelectListener{
        override fun selectFolder(folderItem: FolderItem) {
            folderNameTxv.text = folderItem.name
            mImgItems.clear()
            mImgItems.addAll(folderItem.items)
            mSelectedItems.clear()
            refreshSelectCheck()
            showFolderRecyclerView(false)
            mImgAdapter.notifyDataSetChanged()
        }
    }

    private var mImgItemClickListener = object : OnImgItemClickListener{
        override fun imgItemClick(item: Item) {
            mLargeRecyclerView?.visibility = View.VISIBLE
            mRecyclerView?.visibility = View.GONE
            mLargeAdapter.notifyDataSetChanged()
            mLargeRecyclerView?.scrollToPosition(mImgItems.indexOf(item))
        }

    }

    fun showFolderRecyclerView(isShow : Boolean){
        if(isShow){
            folderMoreImg.rotation = 180f
            mFolderRecyclerView?.visibility = View.VISIBLE
            mRecyclerView?.visibility = View.GONE
        }else{
            folderMoreImg.rotation = 0f
            mFolderRecyclerView?.visibility = View.GONE
            mRecyclerView?.visibility = View.VISIBLE
        }
    }

    fun refreshSelectCheck(){
        if(!mSelectedItems.isEmpty()){
            selectCheck.visibility = View.VISIBLE
            selectCheck.text = "${resources.getText(R.string.select)} ${mSelectedItems.size}/9"
        }else{
            selectCheck.visibility = View.GONE
        }
    }

}