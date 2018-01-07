package com.mylove2018.note;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 11833 on 2018/1/6.
 */
@Entity
public class NoteJson {
    @Id(autoincrement = true)
    private long id;
    private String json;
    @Generated(hash = 919962061)
    public NoteJson(long id, String json) {
        this.id = id;
        this.json = json;
    }
    @Generated(hash = 1403440236)
    public NoteJson() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }
}
