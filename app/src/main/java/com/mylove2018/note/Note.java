package com.mylove2018.note;

import java.util.List;

/**
 * Created by 11833 on 2018/1/6.
 */

public class Note {
    public List<EditData> getNotes() {
        return notes;
    }

    public void setNotes(List<EditData> notes) {
        this.notes = notes;
    }

    private List<EditData> notes;
}
