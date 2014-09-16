package com.vta.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vta.dash.R;
import com.vta.data.ScheduleTime;

import java.util.List;

/**
 * Created by bournelipardo on 9/5/14.
 */
public class StationAdapter extends ArrayAdapter<ScheduleTime> {

    private Context mContext;
    private List<ScheduleTime> mObjects;
    private List<ScheduleTime> mObjects2;

    public StationAdapter(Context context, int resource, List<ScheduleTime> objects, List<ScheduleTime> objects2) {
        super(context, resource, objects);

        this.mContext = context;
        this.mObjects = objects;
        this.mObjects2 = objects2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        ScheduleTime station = mObjects.get(position);
        ScheduleTime station2 = mObjects2.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_station, null);
            holder = new ViewHolder();
            holder.scheduleFromLabel = (TextView) convertView.findViewById(R.id.time1_label);
            holder.scheduleToLabel = (TextView) convertView.findViewById(R.id.time2_label);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.scheduleFromLabel.setText(station.getTime());
        holder.scheduleToLabel.setText(station2.getTime());

        return convertView;
    }

    private static class ViewHolder {
        TextView scheduleFromLabel;
        TextView scheduleToLabel;
    }

}
