package com.mylove2018.util

import java.util.*
import java.text.SimpleDateFormat


/**
 * Created by 11833 on 2018/1/3.
 */
class DateUtil {
   /**
    * 获取两个日期之间的间隔天数
    *
    * @return
    */

   private fun StrToDate(str: String): Date {

      return SimpleDateFormat("yyyy-MM-dd").parse(str)
   }

   fun getGapCount(time: String): Long {
      var startDate: Date?=StrToDate(time)
      val endDate = Date(System.currentTimeMillis())
      //获取当前时间
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
      return (toCalendar.time.time - fromCalendar.time.time) / (1000 * 60 * 60 * 24)
   }


}