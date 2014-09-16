package com.vta.data;

/**
 * Created by bournelipardo on 9/5/14.
 */

public class ScheduleTime {
    private String mTime;

    public ScheduleTime(String time){
        this.mTime = time;
    }

    public String getTime() { return mTime; }

    public void setTime(String time) {
        this.mTime = time;
    }
}
