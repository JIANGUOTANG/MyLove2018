package com.mylove2018.memorial_day;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by 11833 on 2018/1/4.
 */
@Entity
public class NewDateInfo {
    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String title;
    private String date;
    private String imageUrl;
    private String year;
    private String month;
    private String day;
    private boolean isLunar;

    public NewDateInfo(String title, String date, String imageUrl) {
        this.title = title;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    @Generated(hash = 1364795317)
    public NewDateInfo(Long id, String title, String date, String imageUrl,
            String year, String month, String day, boolean isLunar) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.imageUrl = imageUrl;
        this.year = year;
        this.month = month;
        this.day = day;
        this.isLunar = isLunar;
    }

    @Generated(hash = 898685089)
    public NewDateInfo() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean getIsLunar() {
        return this.isLunar;
    }

    public void setIsLunar(boolean isLunar) {
        this.isLunar = isLunar;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
