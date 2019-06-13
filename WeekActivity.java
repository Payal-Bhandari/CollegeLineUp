package com.example.admin.collegelineup;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.admin.collegelineup.Utils.LetterImageView;

public class WeekActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbarWeek;
    private ListView lvWeek;
    public static SharedPreferences sharedPreferences;
    public static String selected_day;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        setUIViews();
        initToolbar();
        setupListView();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUIViews()
    {
        toolbarWeek=(Toolbar)findViewById(R.id.toolbarWeek);
        lvWeek=(ListView)findViewById(R.id.lvWeek);
        sharedPreferences=getSharedPreferences("MY DAY",MODE_PRIVATE);

    }

    private void initToolbar()
    {
        setSupportActionBar(toolbarWeek);
        getSupportActionBar().setTitle("TimeTable");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setupListView()
   {
        String[] week=getResources().getStringArray(R.array.Week);
        WeekAdapter adapter= new WeekAdapter(this,R.layout.week_activity_singleitem,week);
        lvWeek.setAdapter(adapter);

        lvWeek.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position)
                {
                    case 0: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(selected_day, "Monday").apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(selected_day, "Tuesday").apply();
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(selected_day, "Wednesday").apply();
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(selected_day, "Thursday").apply();
                        break;
                    }
                    case 4:{
                        startActivity(new Intent(WeekActivity.this,DayDetail.class));
                        sharedPreferences.edit().putString(selected_day,"Friday").apply();break;
                    }
                    case 5:{
                        startActivity(new Intent(WeekActivity.this,DayDetail.class));
                        sharedPreferences.edit().putString(selected_day,"Saturday").apply();
                        break;
                    }
                    default:break;

                }
            }
        });
   }

   public class WeekAdapter extends ArrayAdapter
   {
       private int resource;
       private LayoutInflater layoutInflater;
       private String[] week=new String[]{};

       public WeekAdapter(Context context, int resource,String[] objects) {
           super(context, resource,objects);
           this.resource=resource;
           this.week=objects;
           layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       }

       @NonNull
       @Override
       public View getView(int position,View convertView,ViewGroup parent) {
           ViewHolder viewHolder;
           if(convertView == null)
           {
               viewHolder=new ViewHolder();
               convertView=layoutInflater.inflate(resource,null);
               viewHolder.ivWeek=(LetterImageView)convertView.findViewById(R.id.ivWeek);
               viewHolder.tvWeek=(TextView)convertView.findViewById(R.id.tvWeek);
               convertView.setTag(viewHolder);
           }
           else
           {
               viewHolder=(ViewHolder)convertView.getTag();
           }

           viewHolder.ivWeek.setOval(true);
           viewHolder.ivWeek.setLetter(week[position].charAt(0));
           viewHolder.tvWeek.setText(week[position]);
           return convertView;

       }
       class ViewHolder
       {
           private LetterImageView ivWeek;
           private TextView tvWeek;
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
