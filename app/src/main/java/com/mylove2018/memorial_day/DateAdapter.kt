package com.mylove2018.memorial_day

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.vipulasri.timelineview.TimelineView
import com.mylove2018.R
import com.ramotion.foldingcell.FoldingCell
/**
 * Created by 11833 on 2018/1/3.
 */
class DateAdapter() : RecyclerView.Adapter<DateAdapter.MyViewHoder>(), Parcelable {
    var list: List<DateInfo>? = null

    constructor(parcel: Parcel) : this() {

    }
    var context: Context? = null
    constructor(list: List<DateInfo>,context: Context?) : this() {
        this.list = list
        this.context = context
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHoder {
        var view: View? = LayoutInflater.from(parent!!.context).inflate(R.layout.item_timeline,parent,false)
        return MyViewHoder(view)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: MyViewHoder?, position: Int) {
        var dateInfo: DateInfo? = list!![position]
        holder!!.tvDate!!.text = dateInfo!!.date
        holder!!.tvTitle!!.text = dateInfo!!.title
        holder!!.tvDetailTitle!!.text = dateInfo!!.title
        holder!!.tvDate!!.text = dateInfo!!.date
        holder!!.titleView!!.setOnClickListener { holder!!.folding_cell!!.toggle(false) }
        holder!!.detailView!!.setOnClickListener { holder!!.folding_cell!!.toggle(false) }
        holder!!.timelineView!!.setMarkerColor(ContextCompat.getColor(this!!.context!!,R.color.colorAccent))

     }
    class MyViewHoder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = itemView!!.findViewById(R.id.tvTitle)//标题，我们在一起
        var tvDay: TextView? = itemView!!.findViewById(R.id.tvDay)//天数，纪念日的天数
        //详细页面的信息
        var tvDetailDay: TextView? = itemView!!.findViewById(R.id.tvDetailDay)//天数，纪念日的天数
        var tvDetailTitle: TextView? = itemView!!.findViewById(R.id.tvDetailTitle)//天数，纪念日的天数
        var tvDate: TextView? = itemView!!.findViewById(R.id.tvDate)//日期，几年内日期
        var detailView: View? = itemView!!.findViewById(R.id.cell_detail_view)//日期，几年内日期
        var titleView: View? = itemView!!.findViewById(R.id.cell_title_view)//日期，几年内日期
        var folding_cell: FoldingCell? = itemView!!.findViewById(R.id.folding_cell)//日期，几年内日期
        var timelineView: TimelineView? = itemView!!.findViewById(R.id.time_marker)//日期，几年内日期

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