package com.mylove2018.memorial_day

import android.app.Activity
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.vipulasri.timelineview.TimelineView
import com.joanzapata.iconify.widget.IconTextView
import com.mylove2018.R
import com.mylove2018.util.DateUtil
import com.mylove2018.util.DialogUtil
import com.ramotion.foldingcell.FoldingCell

/**
 * Created by 11833 on 2018/1/3.
 */
class DateAdapter() : RecyclerView.Adapter<DateAdapter.MyViewHoder>(), Parcelable {
    var list: MutableList<NewDateInfo>? = null

    constructor(parcel: Parcel) : this() {

    }

    var context: Activity? = null

    constructor(list: MutableList<NewDateInfo>, context: Activity?) : this() {
        this.list = list
        this.context = context
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHoder {
        var view: View? = LayoutInflater.from(parent!!.context).inflate(R.layout.item_timeline, parent, false)
        return MyViewHoder(view)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: MyViewHoder?, position: Int) {
        var dateInfo: NewDateInfo? = list!![position]
        holder!!.tvDate!!.text = dateInfo!!.date
        holder.titleView!!.setOnClickListener { holder.folding_cell!!.toggle(false) }
        holder.detailView!!.setOnClickListener { holder!!.folding_cell!!.toggle(false) }
        holder.timelineView!!.setMarkerColor(ContextCompat.getColor(this.context!!, R.color.colorAccent))
        //设置时间距离
        var day: Long? = 0
        day = if (!dateInfo.isLunar!!) {
            Log.d("jianguotan", "month" + dateInfo.month)
            DateUtil().getGapCount(dateInfo.date!!)//距离今天的天数
        } else {
            DateUtil().getGapCount(dateInfo.year!!.replace("年", "-") + dateInfo.month!!.replace("月", "-") + dateInfo.day!!.replace("日", ""))//距离今天的天数
        }
        holder.tvDay!!.text = Math.abs(day).toString()//简易界面的日期
        holder.tvTitle!!.text = if (day > 0) dateInfo.title + "已经" else "距离" + dateInfo.title + "还有"//设置标题
        holder.tvDetailTitle!!.text = if (day > 0) dateInfo.title + "已经" else "距离" + dateInfo.title + "还有"//设置标题
        holder.tvDetailDay!!.text = Math.abs(day).toString()//点击后复杂界面的日期
        holder.iv_delete!!.setOnClickListener {
            if (position > 3) {
                DialogUtil().showDialog(context, "确定删除纪念日？", object : DialogUtil.OnSelectListener {
                    override fun confirm() {
                        list!!.remove(dateInfo)//删除某一个数据
                        DateModel(context!!).deleteDate(dateInfo)//删除数据库数据
                        notifyDataSetChanged()//通知数据变更

                    }
                    override fun cancel() {

                    }

                })
            }


        }



        if (!dateInfo.imageUrl.equals("")) {
            Glide.with(context).load(dateInfo.imageUrl).into(holder.imgView)
        }
    }

    class MyViewHoder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = itemView!!.findViewById(R.id.tvTitle)//标题，我们在一起
        var tvDay: TextView? = itemView!!.findViewById(R.id.tvDay)//天数，纪念日的天数
        //详细页面的信息
        var tvDetailDay: TextView? = itemView!!.findViewById(R.id.tvDetailDay)//天数，纪念日的天数
        var tvDetailTitle: TextView? = itemView!!.findViewById(R.id.tvDetailTitle)//天数，纪念日的天数
        var tvDate: TextView? = itemView!!.findViewById(R.id.tvDate)//日期，几年内日期
        var detailView: View? = itemView!!.findViewById(R.id.cell_detail_view)//纤细视图
        var titleView: View? = itemView!!.findViewById(R.id.cell_title_view)//标题
        var folding_cell: FoldingCell? = itemView!!.findViewById(R.id.folding_cell)//日期，几年内日期
        var timelineView: TimelineView? = itemView!!.findViewById(R.id.time_marker)//时光轴
        var imgView: ImageView? = itemView!!.findViewById(R.id.imgView)//图片显示
        var iv_delete: IconTextView? = itemView!!.findViewById(R.id.iv_delete)//删除

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DateAdapter> {
        override fun createFromParcel(parcel: Parcel): DateAdapter {
            return DateAdapter(parcel)
        }

        override fun newArray(size: Int): Array<DateAdapter?> {
            return arrayOfNulls(size)
        }
    }


}