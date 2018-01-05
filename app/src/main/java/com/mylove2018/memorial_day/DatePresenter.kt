package com.mylove2018.memorial_day

import android.app.Activity
import com.mylove2018.MyApplication
import com.mylove2018.greendao.NewDateInfoDao

/**
 * Created by ying on 17-11-9.
 */

class DatePresenter
/**
 * @param activity
 */
(private val activity: Activity,var dateView: DateView) {
    private val dateModle: DateModel = DateModel(activity)
    /**
     * 查询文件
     */
    fun loadFile() {
        dateModle.loadFile()
    }
    fun loadData(){
        val loadDate = dateModle.LoadDate()
        dateView.loadDate(loadDate)
    }
    /**
     * 插入
     */
    fun insertDate(dataInfo: NewDateInfo?) {
        dateModle.insertDate(dataInfo)
    }

    /**
     * 删除
     */
    fun deleteDate(dataInfo: NewDateInfo?) {
        dateModle.deleteDate(dataInfo)
    }
    fun updateDate(dataInfo: NewDateInfo?) {
        dateModle.updateDate(dataInfo)
    }

}
