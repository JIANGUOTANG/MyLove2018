package com.mylove2018.note

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.Drawable

import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.blankj.utilcode.util.SpanUtils
import com.bumptech.glide.Glide

import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.entity.LocalMedia
import com.mylove2018.MyBaseActivity
import com.mylove2018.R
import com.mylove2018.memorial_day.DateModel


import kotlinx.android.synthetic.main.activity_note.*

import com.bumptech.glide.request.target.SimpleTarget

import com.bumptech.glide.request.transition.Transition



/**
 * Created by 11833 on 2018/1/6.
 */
class NoteActivity : MyBaseActivity(), View.OnKeyListener {
    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
        if (p1 == KeyEvent.KEYCODE_DEL) {
            //监听删除事件
            if (edtNote.selectionStart > 4 && edtNote.text.substring(edtNote.selectionStart - 5, edtNote.selectionStart - 1) == "<img>") {
                //因为图片在editText在这里是"Img"的形式实现的
                edtNote.text.delete(edtNote.selectionStart - 5, edtNote.selectionStart - 1)
                //图片删除事件
                return true
            }
            return false
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        initView()
    }

    private fun initView() {
        iv_AddImg!!.setOnClickListener {
            //点击添加按钮
            DateModel(this).loadFile()//打开图片选择
        }
        tvFinish.setOnClickListener {
            //点击完成
            var str = edtNote.text.toString()
            Log.d("jian", str)
            Toast.makeText(this@NoteActivity, str, Toast.LENGTH_SHORT).show()
        }
        edtNote.setOnKeyListener(this)

    }

    private lateinit var selectList: List<LocalMedia>//媒体信息（图片视频）
    private var path: String? = null//上传文件的路径
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data)
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    //文件上传
                    if (selectList.isNotEmpty()) {
                        path = selectList[0].compressPath
                        val item = selectList[0]
                        //预览图
                        var index = edtNote.selectionStart

//                        Glide.with(this)
//                                .load(path)
//
//                                .into(object : SimpleTarget<Drawable>() {
//                                    override fun onResourceReady(resource: Drawable?, transition: Transition<in Drawable>?) {
//                                        edtNote!!.text.insert(index, SpanUtils().appendImage(resource!!, 10).appendLine().create())
//
//                                    }
//
//                                });
                        Glide.with(this)
                                .asBitmap()
                                .load(path)
                                .into(object : SimpleTarget<Bitmap>() {
                                    override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                                        val width = resource!!.width
                                        val height = resource!!.height
                                        // 设置想要的大小
                                        val newWidth = width *3/ 4
                                        val newHeight = height *3/ 4
                                        // 计算缩放比例
                                        val scaleWidth = newWidth.toFloat() / width
                                        val scaleHeight = newHeight.toFloat() / height
                                        // 取得想要缩放的matrix参数
                                        val matrix = Matrix()
                                        matrix.postScale(scaleWidth, scaleHeight)
                                        // 得到新的图片
                                        var bitmap: Bitmap = Bitmap.createBitmap(resource, 0, 0, width, height, matrix, true)
                                        edtNote!!.text.insert(index, SpanUtils().appendImage(bitmap!!, SpanUtils.ALIGN_CENTER).create())
                                    }
                                }) //方法中设置asBitmap可以设置回调类型
                    }
                }
            }
        }
    }


}