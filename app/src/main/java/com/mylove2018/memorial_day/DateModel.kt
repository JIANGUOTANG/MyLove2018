package com.mylove2018.memorial_day

import android.app.Activity

import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.mylove2018.MyApplication
import com.mylove2018.greendao.NewDateInfoDao
import com.mylove2018.greendao.DaoSession
import org.greenrobot.greendao.query.Query


/**
 * Created by ying on 17-11-9.
 */

class DateModel(
        /**
         * 查询文件
         */
        private val activity: Activity) {

    fun loadFile() {
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofAll())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .maxSelectNum(1)// 最大图片选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(false)// 是否可预览视频 true or false
                .enablePreviewAudio(true) // 是否可播放音频 true or false
                .compress(true)
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .isGif(true)// 是否显示gif图片 true or false
                .openClickSound(true)// 是否开启点击声音 true or false
                // 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .videoMaxSecond(160)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                .forResult(PictureConfig.CHOOSE_REQUEST)//结果回调onActivityResult code
    }


    fun LoadDate(): List<NewDateInfo> {
        val daoSession = (activity.application as MyApplication).getDaoSession()
        var newdateDao: NewDateInfoDao = daoSession!!.newDateInfoDao
        val dataInfoQuery: Query<NewDateInfo>? = newdateDao!!.queryBuilder().build()
        return dataInfoQuery!!.list()
    }

    /**
     * 插入
     */
    fun insertDate(dataInfo: NewDateInfo?) {
        val daoSession = (activity.application as MyApplication).getDaoSession()
        var newdateDao: NewDateInfoDao = daoSession!!.newDateInfoDao
        newdateDao.insertOrReplace(dataInfo)
    }

    /**
     * 删除
     */
    fun deleteDate(dataInfo: NewDateInfo?) {
        val daoSession = (activity.application as MyApplication).getDaoSession()
        var newdateDao: NewDateInfoDao = daoSession!!.newDateInfoDao
        newdateDao.deleteByKey(dataInfo!!.id)
    }
    fun updateDate(dataInfo: NewDateInfo?) {
        val daoSession = (activity.application as MyApplication).getDaoSession()
        var newdateDao: NewDateInfoDao = daoSession!!.newDateInfoDao
        newdateDao.update(dataInfo)
    }



}
