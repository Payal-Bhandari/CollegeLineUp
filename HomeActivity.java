package com.example.admin.collegelineup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.collegelineup.Utils.LetterImageView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    private TextView  tv,tvattend; //menu1;
    private LetterImageView letterImageView;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private String Date;
    private Toolbar toolbarHome;
    private ImageButton ibHome;
    private Button btnattend,add,sub;
    private String upload;
    public static String[] monday;
    public static String[] tuesday;
    public static String[] wednesday;
    public static String[] thursday;
    public static String[] friday;
    public static String[] saturday;
    public static String[] sunday;

    public static String[] time1;
    public static String[] time2;
    public static String[] time3;
    public static String[] time4;
    public static String[] time5;
    public static String[] time6;
    public static String[] time7;

    private String[] pre_day;
    private String[] pre_time;
    private ListView lvHome;

    int attendance = 0;
    int attend = 0;
    // int count = 1;
     boolean flag = false;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUIViews();
        initToolbar();
        setupListView();


        btnattend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String att=String.valueOf(attendance);
                    tvattend.setText(att);
                    if(attendance<7)
                    {
                        calculate();
                    }
                    else
                    {
                        Toast.makeText(HomeActivity.this, "You've selected too many lectures please subtract them", Toast.LENGTH_SHORT).show();
                    }
                    
                } catch (Exception exp) {
                    tvattend.setText("Error");
                }
            }


        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Attendance();
                Toast.makeText(HomeActivity.this, "Got Your Attendance", Toast.LENGTH_SHORT).show();
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int minus = attendance;
                //String minus = String.valueOf(attendance);
                minus = minus - 1;
                attendance = minus;
                Toast.makeText(HomeActivity.this, "Attendance of 1 lecture is subtracted", Toast.LENGTH_SHORT).show();
                String value = String.valueOf(attendance);
                tvattend.setText(value);
            }
        });

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,DrawerActivity.class);
                startActivity(intent);
            }
        });

        //String upload=String.valueOf(attendance);
        //sendUserdate();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String statesaved=savedInstanceState.getString("saved state");
        if(statesaved==null)
        {
            Toast.makeText(HomeActivity.this,"No state saved",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(HomeActivity.this," state saved",Toast.LENGTH_SHORT).show();
        }
    }

   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }*/

    private void setUIViews()
    {
        ibHome=(ImageButton)findViewById(R.id.ibHome);
        tv = (TextView) findViewById(R.id.tv);
        lvHome=(ListView)findViewById(R.id.lvHome);
        toolbarHome=(Toolbar)findViewById(R.id.toolbarHome);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
        Date = simpleDateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        tv.setText(Date);
        tvattend = (TextView)findViewById(R.id.tvattend);
        btnattend = (Button)findViewById(R.id.btnattend);
        add = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
    }

    @SuppressLint("ResourceType")
    private void initToolbar()
    {
        setSupportActionBar(toolbarHome);
        //getSupportActionBar().setTitle(R.id.tvtoolbarHome);
        //getSupportActionBar().setIcon(R.id.ibHome);
    }

    private void setupListView ()
    {
        monday = getResources().getStringArray(R.array.Monday);
        tuesday = getResources().getStringArray(R.array.Tuesday);
        wednesday = getResources().getStringArray(R.array.Wednesday);
        thursday = getResources().getStringArray(R.array.Thursday);
        friday = getResources().getStringArray(R.array.Friday);
        saturday = getResources().getStringArray(R.array.Saturday);
        sunday=getResources().getStringArray(R.array.Sunday);
        time1 = getResources().getStringArray(R.array.time1);
        time2 = getResources().getStringArray(R.array.time2);
        time3 = getResources().getStringArray(R.array.time3);
        time4 = getResources().getStringArray(R.array.time4);
        time5 = getResources().getStringArray(R.array.time5);
        time6 = getResources().getStringArray(R.array.time6);
        time7=getResources().getStringArray(R.array.time7);

        if (Date.startsWith("Mon")) {
            pre_day = monday;
            pre_time = time1;
        } else if (Date.startsWith("Tue")) {
            pre_day = tuesday;
            pre_time = time2;
        } else if (Date.startsWith("Wed")) {
            pre_day = wednesday;
            pre_time = time3;
        } else if (Date.startsWith("Thu")) {
            pre_day = thursday;
            pre_time = time4;
        } else if (Date.startsWith("Fri")) {
            pre_day = friday;
            pre_time = time5;
        } else if (Date.startsWith("Sat")) {
            pre_day = saturday;
            pre_time = time6;
        } else {
            pre_day = monday;
            pre_time=time1;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, pre_day, pre_time);
        lvHome.setAdapter(simpleAdapter);
    }

    public class SimpleAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView subject, time;
        private String[] subjectarray;
        private String[] timearray;
        private LetterImageView letterImageView;
        private TextView mark;

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
                convertView=layoutInflater.inflate(R.layout.home_activity_singleitem,null);
            }
            subject=(TextView)convertView.findViewById(R.id.tvHome);
            time=(TextView)convertView.findViewById(R.id.tvHomeTime);
            letterImageView = (LetterImageView)convertView.findViewById(R.id.ivHome);
            mark = (TextView)convertView.findViewById(R.id.mark);

            subject.setText(subjectarray[position]);
            time.setText(timearray[position]);

            letterImageView.setOval(true);
            letterImageView.setLetter(subjectarray[position].charAt(0));
            attendance();
            return convertView;
        }

        private boolean attendance() {
            lvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    attend = Attendance();
                    flag = true;
                    Toast.makeText(HomeActivity.this, "Got Your Attendance", Toast.LENGTH_SHORT).show();
                }
            });
            return flag;
        }

    }

    private int Attendance() {
        attendance = attendance + 1;
        return attendance;
    }

    private void calculate() {
        int calculate = attendance;
        calculate = ((attendance*100)/5);
        Toast.makeText(HomeActivity.this, "Your Today's Attendance is : " +calculate + "%" , Toast.LENGTH_SHORT).show();

    }
    /*private void sendUserdate()
    {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef=firebaseDatabase.getReference(firebaseAuth.getUid());
        myRef.setValue(upload);
    }*/
}
