package com.example.admin.collegelineup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.admin.collegelineup.Utils.LetterImageView;

public class DayDetail extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbarDayDetail;
    private ListView lvDayDetail;
    public static String[] monday;
    public static String[] tuesday;
    public static String[] wednesday;
    public static String[] thursday;
    public static String[] friday;
    public static String[] saturday;

    public static String[] time1;
    public static String[] time2;
    public static String[] time3;
    public static String[] time4;
    public static String[] time5;
    public static String[] time6;

    private String[] pre_day;
    private String[] pre_time;
    private LetterImageView letterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);
        setUIViews();
        initToolbar();
        setupListView();
    }

    private void setUIViews() {
        toolbarDayDetail = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarDayDetail);
        lvDayDetail = (ListView) findViewById(R.id.lvDayDetail);

    }

    private void initToolbar() {
        setSupportActionBar(toolbarDayDetail);
        getSupportActionBar().setTitle("TimeTable");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setupListView() {
        monday = getResources().getStringArray(R.array.Monday);
        tuesday = getResources().getStringArray(R.array.Tuesday);
        wednesday = getResources().getStringArray(R.array.Wednesday);
        thursday = getResources().getStringArray(R.array.Thursday);
        friday = getResources().getStringArray(R.array.Friday);
        saturday = getResources().getStringArray(R.array.Saturday);
        time1 = getResources().getStringArray(R.array.time1);
        time2 = getResources().getStringArray(R.array.time2);
        time3 = getResources().getStringArray(R.array.time3);
        time4 = getResources().getStringArray(R.array.time4);
        time5 = getResources().getStringArray(R.array.time5);
        time6 = getResources().getStringArray(R.array.time6);

        String sel_day = WeekActivity.sharedPreferences.getString(WeekActivity.selected_day, null);

        if (sel_day.equalsIgnoreCase("Monday")) {
            pre_day = monday;
            pre_time = time1;
        } else if (sel_day.equalsIgnoreCase("Tuesday")) {
            pre_day = tuesday;
            pre_time = time2;
        } else if (sel_day.equalsIgnoreCase("Wednesday")) {
            pre_day = wednesday;
            pre_time = time3;

        } else if (sel_day.equalsIgnoreCase("Thursday")) {
            pre_day = thursday;
            pre_time = time4;
        } else if (sel_day.equalsIgnoreCase("Friday")) {
            pre_day = friday;
            pre_time = time5;
        } else {
            pre_day = saturday;
            pre_time = time6;
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,pre_day,pre_time);
        lvDayDetail.setAdapter(simpleAdapter);
    }

    public class SimpleAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView subject, time;
        private String[] subjectarray;
        private String[] timearray;
        private LetterImageView letterImageView;

        public SimpleAdapter(Context context, String[] subjectarray, String[] timearray) {
            mContext = context;
            this.subjectarray= subjectarray;
            this.timearray = timearray;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subjectarray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectarray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
            {
                convertView=layoutInflater.inflate(R.layout.day_activity_singleitem,null);
            }
            subject=(TextView)convertView.findViewById(R.id.tvDayDetail);
            time=(TextView)convertView.findViewById(R.id.tvDayDetailTime);
            letterImageView = (LetterImageView)convertView.findViewById(R.id.ivDayDetail);

            subject.setText(subjectarray[position]);
            time.setText(timearray[position]);

            letterImageView.setOval(true);
            letterImageView.setLetter(subjectarray[position].charAt(0));
            return convertView;

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}