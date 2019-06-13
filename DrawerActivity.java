package com.example.admin.collegelineup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DrawerActivity extends AppCompatActivity {
    private Toolbar toolbarMain;
    private ListView lvMain;
     FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        setupUIViews();
        initToolbar();
        firebaseAuth = FirebaseAuth.getInstance();
        setupListView();
    }

    private void setupUIViews()
    {
        toolbarMain=(Toolbar)findViewById(R.id.toolbarmain);
        lvMain=(ListView)findViewById(R.id.lvMain);

    }

    private void initToolbar()
    {
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("CollegeLineUp");
    }

    private void setupListView()
    {
        String[] title=getResources().getStringArray(R.array.Main);
        String[] description=getResources().getStringArray(R.array.Description);
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,title,description);
        lvMain.setAdapter(simpleAdapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position)
                {
                    case 0: {
                        Intent intent = new Intent(DrawerActivity.this, WeekActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:
                    {
                        Intent intent = new Intent(DrawerActivity.this, CalenderActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:
                    {
                        Intent intent=new Intent(DrawerActivity.this,FacultyActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3:
                    {
                        Intent intent=new Intent(DrawerActivity.this,UploadActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 4:
                    {
                        firebaseAuth.signOut();
                        finish();
                        Intent intent=new Intent(DrawerActivity.this,SplashScreenActivity.class);
                        startActivity(intent);
                        break;
                    }
                    default:break;
                }
            }
        });
    }

    public class SimpleAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title,description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SimpleAdapter(Context context,String[] title,String[] description )
        {
            mContext= context;
            titleArray=title;
            descriptionArray=description;
            layoutInflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
            {
                convertView=layoutInflater.inflate(R.layout.main_activity_singleitem,null);
            }
            title=(TextView)convertView.findViewById(R.id.tvMain);
            description=(TextView)convertView.findViewById(R.id.tvDescription);
            imageView=(ImageView)convertView.findViewById(R.id.ivMain);

            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);
            if(titleArray[position].equalsIgnoreCase("Timetable"))
            {
                imageView.setImageResource(R.drawable.book);
            }
            else if(titleArray[position].equalsIgnoreCase("Calendar"))
            {
                imageView.setImageResource(R.drawable.calendar);
            }
            else if(titleArray[position].equalsIgnoreCase("Faculty"))
            {
                imageView.setImageResource(R.drawable.faculty);
            }
            else if(titleArray[position].equalsIgnoreCase("NoticeBoard"))
            {
                imageView.setImageResource(R.drawable.noticeboard);
            }
            else
            {
                imageView.setImageResource(R.drawable.logout);
            }
            return convertView;
        }
    }

}


