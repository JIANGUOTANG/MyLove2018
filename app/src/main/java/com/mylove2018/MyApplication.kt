package com.mylove2018

import android.app.Application
import com.blankj.utilcode.util.Utils


import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.FontAwesomeModule
import com.mylove2018.greendao.DaoMaster
import org.greenrobot.greendao.database.Database
import com.mylove2018.greendao.DaoSession



/**
 * Created by 11833 on 2018/1/3.
 */

class MyApplication : Application() {
    private var daoSession: DaoSession? = null
    override fun onCreate() {
        super.onCreate()
        Iconify.with(FontAwesomeModule())
        val helper = DaoMaster.DevOpenHelper(this, "notes-db")
        val db = helper.writableDb
        daoSession = DaoMaster(db).newSession()
        Utils.init(this)

    }

    fun getDaoSession(): DaoSession? {
        return daoSession
    }
}