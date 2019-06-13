package com.example.admin.collegelineup;

import android.app.usage.UsageEvents;
import android.service.autofill.FillEventHistory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalenderActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView myDate,event;
    private Toolbar toolbarCalender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        setUIViews();
        initToolbar();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1)  + "/" + i2 + "/" +i;
                if(date.equals("11/30/2018")){
                    event.setText("TOC");

                }
                else if(date.equals("10/22/2018")) {
                    event.setText("SDL Viva");}
                    else if(date.equals("10/26/2018")){
                        event.setText("CN Practical");}
                        else if(date.equals("12/4/2018")){
                    event.setText("DBMS");
                }else if(date.equals("12/7/2018")){
                    event.setText("SEPM");
                }else if(date.equals("12/10/2018")){
                    event.setText("ISEE");
                }else if(date.equals("12/12/2018")){
                    event.setText("CN");
                }else if(date.equals("12/17/2018")){
                    event.setText("Commencement of Teaching");
                }else if(date.equals("4/9/2019")){
                    event.setText("Conclusion of Teaching");
                }else if(date.equals("4/11/2019")){
                    event.setText("Practical Examination Starts ");
                }else if(date.equals("5/2/2019")){
                    event.setText("Theory Exam Starts");
                }else if(date.equals("4/25/2019")){
                    event.setText("End of Practical Examination");
                }else if(date.equals("5/27/2019")){
                    event.setText("End of Theory Examination");
                }else if(date.equals("10/17/2018")){
                    event.setText("Conclusion of Teaching");
                }else if(date.equals("10/20/2018")){
                    event.setText("Practical Examination Starts");
                }else if(date.equals("11/3/2018")){
                    event.setText("End of Practical Examination");
                }else if(date.equals("11/14/2018")){
                    event.setText("Theory Examination Starts");
                }else if(date.equals("10/18/2018")){
                    event.setText("Dasara");
                }else if(date.equals("11/7/2018")){
                    event.setText("Diwali");
                }else if(date.equals("11/8/2018")){
                    event.setText("Diwali");
                }else if(date.equals("1/26/2019")){
                    event.setText("Republic Day");
                }else if(date.equals("2/19/2019")){
                    event.setText("ShivJayanti");
                }else if(date.equals("3/4/2019")){
                    event.setText("Mahashivratri");
                }else if(date.equals("3/21/2019")){
                    event.setText("Holi");
                }else if(date.equals("4/6/2019")){
                    event.setText("Gudipadwa");
                }else if(date.equals("5/1/2019")){
                    event.setText("Maharastra Din");
                }

                else{
                    event.setText(" No events Planned");
                }
                myDate.setText(date);
            }
        });
    }

    private void setUIViews()
    {
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        myDate = (TextView)findViewById(R.id.myDate);
        event = (TextView)findViewById(R.id.event);
        toolbarCalender=(Toolbar)findViewById(R.id.toolbarCalender);
    }

    private void initToolbar() {
        setSupportActionBar(toolbarCalender);
        getSupportActionBar().setTitle("Calendar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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



