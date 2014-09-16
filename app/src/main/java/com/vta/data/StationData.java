package com.vta.data;

import java.util.ArrayList;
import java.util.List;

public class StationData {
    private List<ScheduleTime> mTime = new ArrayList<ScheduleTime>();
    private String mStationName;

    public List<ScheduleTime> getTime(){
        return mTime;
    }

    public void setStation(String station) {
        this.mStationName = station;
    }

    public void setTime(String time) {
        ScheduleTime mSchedule = new ScheduleTime(time);
        addItem(mSchedule);
    }

    public String getStation(){
        return mStationName;
    }

    public void addItem(ScheduleTime item){
        mTime.add(item);
    }
}

