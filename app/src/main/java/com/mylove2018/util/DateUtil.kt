package com.mylove2018.util

import java.util.*


/**
 * Created by 11833 on 2018/1/3.
 */
class DateUtil {
    /**
     * 获取两个日期之间的间隔天数
     * @return
     */
     fun getGapCount(startDate: Date, endDate: Date): Long {
        val fromCalendar = Calendar.getInstance()
        fromCalendar.time = startDate
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0)
        fromCalendar.set(Calendar.MINUTE, 0)
        fromCalendar.set(Calendar.SECOND, 0)
        fromCalendar.set(Calendar.MILLISECOND, 0)
        val toCalendar = Calendar.getInstance()
        toCalendar.time = endDate
        toCalendar.set(Calendar.HOUR_OF_DAY, 0)
        toCalendar.set(Calendar.MINUTE, 0)
        toCalendar.set(Calendar.SECOND, 0)
        toCalendar.set(Calendar.MILLISECOND, 0)
        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24)
    }

}