package com.mylove2018.memorial_day

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.mylove2018.R
import com.ramotion.foldingcell.FoldingCell

/**
 * Created by 11833 on 2018/1/3.
 */

class DateActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var dateAdapter: DateAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)
        initView()
        initDate()
    }
    private fun initDate() {
        var list: MutableList<DateInfo>? = mutableListOf()
        DateInfo("相识", "2009-09-01", "")
        list!!.add(DateInfo("在一起已经", "2014-07-13", ""))
        list!!.add(DateInfo("我们相识已经", "2009-09-01", ""))
        dateAdapter = DateAdapter(list,this)
        recyclerView!!.adapter = dateAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)

    }
}
