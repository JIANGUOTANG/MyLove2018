package com.mylove2018.memorial_day

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.entity.LocalMedia

import com.mylove2018.R
import com.mylove2018.calendard.CalendarSelector
import com.mylove2018.util.DateUtil
import java.util.*


/**
 * Created by 11833 on 2018/1/3.
 */

class DateActivity : AppCompatActivity(), CalendarSelector.ICalendarSelectorCallBack, DateView {
    override fun loadDate(list: List<NewDateInfo>) {
        //加载数据库中的数据
        this.list!!.addAll(list)
        dateAdapter!!.notifyDataSetChanged()
    }

    override fun addSucceed(state: Int) {

    }

    internal lateinit var selectList: List<LocalMedia>//媒体信息（图片视频）

    private var recyclerView: RecyclerView? = null
    private var dateAdapter: DateAdapter? = null
    private var fabAdd: FloatingActionButton? = null
    private var path: String? = null//上传文件的路径
    private var presenter: DatePresenter? = null
    private var tvTogetherDays: TextView? = null//在一起多久

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)
        initView()
        initDate()
        presenter = DatePresenter(this, this)
        presenter!!.loadData()
    }

    var list: MutableList<NewDateInfo>? = mutableListOf()
    private fun initDate() {
        //设置默认的日期
        list!!.add(NewDateInfo("我们相识", "2009-09-01", "http://b101.photo.store.qq.com/psb?/V12y57La1eZG3d/f8FQEjKnZyMEE0c2eiUUoB7RDaN7z2v9gOrSBMgRCZw!/b/dGUAAAAAAAAA&bo=4AGAAuABgAIFByQ!&rf=viewer_4"))
        list!!.add(NewDateInfo("我们相爱", "2014-07-13", "http://m.qpic.cn/psb?/V12y57La2uYci0/NrjHtHjSXd0sffXYl4IyYYddmaxrUBo5k4xXUyj5rag!/b/dPMAAAAAAAAA&bo=AAWrBpAJwAwRCcM!&rf=viewer_4"))
        list!!.add(NewDateInfo("我们第一次牵手", "2014-07-28", ""))
        list!!.add(NewDateInfo("我们第一次亲吻", "2014-08-02", "http://b131.photo.store.qq.com/psb?/V12y57La1eZG3d/UUFx6p7kGOVEgeoh7N7ZzyAiiKNReDXp3whfc2gAIkY!/b/dOieFk72MwAA&bo=gAJVAwAAAAABB*Q!&rf=viewer_4"))
        dateAdapter = DateAdapter(list!!, this)
        recyclerView!!.adapter = dateAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(this)

    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        tvTogetherDays = findViewById(R.id.tvTogetherDays)
        tvTogetherDays!!.text = DateUtil().getGapCount("2014-07-13").toString()
        fabAdd = findViewById(R.id.fabAdd)
        fabAdd!!.setOnClickListener { showDialog() }
    }

    /**
     * 日历选择返回
     */
    override fun transmitPeriod(result: HashMap<String, String>?, isLunar: Boolean) {

        date = result!!["year"]!!.replace("年", "-") + result!!["month"]!!.replace("月", "-") + result!!["day"]!!.replace("日", "")
        if (isLunar) {
            //农历的时候保存对应的公历
            dateInfo!!.year = result!!["year2"]//对应的公历年
            dateInfo!!.day = result!!["day2"]
            dateInfo!!.month = result!!["month2"]
            date = result!!["year2"] + result!!["month2"] + result!!["day2"] //农历不用横线显示
        }
        dateInfo!!.isLunar = isLunar
        tvDate!!.text = date
        dateInfo!!.date = date
    }

    private var dateInfo: NewDateInfo? = NewDateInfo()
    private var tvDate: TextView? = null
    private var date: String? = null//日期
    private var imagBg: AppCompatImageView? = null
    private fun showDialog() {
        val inflater = layoutInflater
        var title: String? = null//纪念日内容
        var date: String? = null//日期
        var ba = null//背景
        val dialog = inflater.inflate(R.layout.dialog_add_date, null)
        var edtTitle: EditText? = dialog.findViewById(R.id.edtDate)//输入框，输入纪念日名字
        tvDate = dialog.findViewById(R.id.tvDate)//日期文本框
        imagBg = dialog.findViewById(R.id.imgBg)//图片背景
        var itemBg: View? = dialog.findViewById(R.id.linerLayoutBg)
        var linearLayoutDate: View? = dialog.findViewById(R.id.linearLayoutDate)
        itemBg!!.setOnClickListener {
            //点击选择背景
            presenter!!.loadFile()
        }
        linearLayoutDate!!.setOnClickListener {
            //选择时间
            var mCalendarSelector: CalendarSelector? = null
            var calendar: Calendar = Calendar.getInstance()
            mCalendarSelector = CalendarSelector(this, calendar[Calendar.YEAR] - 1901, this)
            mCalendarSelector.show(tvDate)//显示日历选择框
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("添加纪念日")
        builder.setPositiveButton("完成", DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            title = edtTitle!!.text!!.toString().trim()
            dateInfo!!.imageUrl = path//设置路径
            dateInfo!!.title = title//设置标题
            list!!.add(dateInfo!!)
            presenter!!.insertDate(dateInfo)
            dateAdapter!!.notifyDataSetChanged()//通知数据增加
            Toast.makeText(applicationContext, "show", Toast.LENGTH_LONG).show()
        })
        builder.setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->

        })
        builder.setView(dialog)
        builder.setIcon(R.drawable.ic_love)
        builder.show()
    }

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
                        Toast.makeText(this@DateActivity, "jian", Toast.LENGTH_LONG).show()
                        path = selectList[0].compressPath
                        val item = selectList[0]
                        //预览图
                        Glide.with(this@DateActivity).load(path).into(this!!.imagBg!!)
                    }
                }
            }
        }
    }
}
