package com.vta.dash;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.vta.data.ScheduleTime;
import com.vta.data.StationData;
import com.vta.util.StationAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MyActivity extends ListActivity {

    public static final String KEY_DIRIDON="San Jose Diridon";
    public static final String KEY_FIRST="San Fernando And 1st";
    public static final String KEY_FOURTH = "4th And San Fernando";
    public static final String KEY_CONVENTION = "San Jose Convention";

    List<StationData> mDash = new ArrayList<StationData>();
    List<ScheduleTime> mResult = new ArrayList<ScheduleTime>();
    private Spinner mSpinner, mSpinner2;
    private int mValueSpin, mValueSpin2;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        setTimeAndStation();
    }

    public void setTimeAndStation(){
        //Initialize Spinners
        mSpinner = (Spinner) findViewById(R.id.spinner);

        mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stationTo = mSpinner.getSelectedItem().toString();
                Toast.makeText(MyActivity.this, stationTo, Toast.LENGTH_SHORT).show();
                mValueSpin = mSpinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        mSpinner2 = (Spinner) findViewById(R.id.spinner2);
        mSpinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stationFrom = mSpinner2.getSelectedItem().toString();
                Toast.makeText(MyActivity.this, stationFrom, Toast.LENGTH_SHORT).show();
                mValueSpin2 = mSpinner2.getSelectedItemPosition();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        mBtnSubmit = (Button) findViewById(R.id.buttonSchedule);

        try {
            InputStream inputStream;

            // Add time Diridon Station
            inputStream = getResources().openRawResource(R.raw.san_jose_diridon);
            readRawTextFile(inputStream);

            // Add time 1st  Station
            inputStream = getResources().openRawResource(R.raw.san_fernando);
            readRawTextFile(inputStream);

            // Add time 4th Station
            inputStream = getResources().openRawResource(R.raw.fourth_and_san);
            readRawTextFile(inputStream);

            // Add time Convention Station
            inputStream = getResources().openRawResource(R.raw.convention);
            readRawTextFile(inputStream);

            // Add time Diridon Station
            inputStream = getResources().openRawResource(R.raw.end_diridon);
            readRawTextFile(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String timeHighlight;
//        String timeNow = getTime().replaceAll("\\D", "");
//        int timeNowNum = Integer.parseInt(timeNow);

//        while(timeNowNum <= tempNum && i < temp.size()){
//            tempNum = Integer.parseInt(temp.get(i).getTime().replaceAll("\\D", ""));
//            i++;
//        }

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<ScheduleTime> temp = mDash.get(1).getTime();
                int i = 0;
                int tempNum = Integer.parseInt(temp.get(i).getTime().replaceAll("\\D", ""));

                StationAdapter adapter =
                        new StationAdapter(MyActivity.this,
                                R.layout.item_station,
                                mDash.get(mValueSpin).getTime(),
                                mDash.get(mValueSpin2).getTime());

                setListAdapter(adapter);

                getListView().setSelection(i);
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            }
        });

    }

    /*
    * Read a file and put it into a List of StationData
    */
    public void readRawTextFile(InputStream inputStream) throws IOException
    {
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputreader);
        String line;

        line = reader.readLine();
        StationData station = new StationData();
        station.setStation(line);

        while ((line = reader.readLine()) != null) {
            station.setTime(line);
        }

        mDash.add(station);
    }

    /*
     * Retrieve the Actual Time
     */
//    public String currentTime(){
//        Date now = new Date();
//        DateFormat shortDf = DateFormat.getTimeInstance(DateFormat.SHORT);
//        String currentTime = shortDf.format(now);
//        return currentTime;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
