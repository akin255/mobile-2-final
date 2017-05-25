package com.example.akin.travel;


import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Chat {
    private String msg;
    private String name;
    private long time;


    public Chat() {
    }


    public Chat(String msg, String name) {
        this.msg = msg;
        this.name = name;
        this.time=time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
