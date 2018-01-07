package com.mylove2018

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.util.Log
import com.blankj.utilcode.util.SpanUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.entity.LocalMedia
import com.mylove2018.memorial_day.DateModel
import kotlinx.android.synthetic.main.activity_note.*
import com.mylove2018.note.RichTextEditor


/**
 * Created by 11833 on 2018/1/3.
 */

class TestActivity : AppCompatActivity() {
    internal lateinit var mSpanUtils: SpanUtils
    internal lateinit var animSsb: SpannableStringBuilder
    private var richEditor: RichTextEditor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

//        bt_insert!!.setOnClickListener { DateModel(this).loadFile() }
        richEditor = findViewById(R.id.richEditor)

    }

    internal lateinit var selectList: List<LocalMedia>//媒体信息（图片视频）
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
                    Log.d("jianguotang", "jian1")
                    if (selectList.isNotEmpty()) {
                        Log.d("jianguotang", "jian2")
                        path = selectList[0].compressPath
                        val item = selectList[0]
                        //预览图
                        insertBitmap(path);
//                        insertIntoEditText(getBitmapMime(path));
//                        edtNote!!.text = SpanUtils().appendImage(Uri.fromFile(File(path)), 10).append("jianguotang" +
//                                "jiangjiangjian" +
//                                "jianjianjian\n").create()
                    }
                }
            }
        }
    }

    private fun insertBitmap(imagePath: String?) {
        richEditor!!.insertImage(imagePath)
    }
}
