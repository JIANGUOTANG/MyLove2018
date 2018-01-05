package com.mylove2018

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by 11833 on 2018/1/3.
 */

class TestActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        val str = if (0 > 0) "已经" else "距离"
    }

    override fun onClick(view: View) {

    }
}
