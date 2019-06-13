package com.example.admin.collegelineup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.example.admin.collegelineup.Utils.LetterImageView;

public class FacultyActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbarFaculty;
    private ViewFlipper viewFlipper;
    private ListView lvFaculty;
    public static SharedPreferences sharedPreferences;
    public static String selected_faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        setUIViews();
        initToolbar();
        viewFlipper.setOnClickListener(this);
        setupListView();
    }


    private void setUIViews()
    {
        toolbarFaculty=(Toolbar)findViewById(R.id.toolbarFaculty);
        lvFaculty=(ListView)findViewById(R.id.lvFaculty);
        viewFlipper=(ViewFlipper)findViewById(R.id.ivFaculty);
        sharedPreferences=getSharedPreferences("My Faculty",MODE_PRIVATE);
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbarFaculty);
        getSupportActionBar().setTitle("Faculty");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView()
    {
        String[] faculty_names =getResources().getStringArray(R.array.Faculty);
        FacultyAdapter adapter= new FacultyAdapter(this,R.layout.faculty_activity_singleitem,faculty_names);
        lvFaculty.setAdapter(adapter);

        lvFaculty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position)
                {
                    case 0: {
                        startActivity(new Intent(FacultyActivity.this, FacultyDetails.class));
                        sharedPreferences.edit().putInt(selected_faculty,0).apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(FacultyActivity.this, FacultyDetails.class));
                        sharedPreferences.edit().putInt(selected_faculty, 1).apply();
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(FacultyActivity.this, FacultyDetails.class));
                        sharedPreferences.edit().putInt(selected_faculty, 2).apply();
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(FacultyActivity.this, FacultyDetails.class));
                        sharedPreferences.edit().putInt(selected_faculty, 3).apply();
                        break;
                    }
                    case 4:{
                        startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(selected_faculty,4).apply();break;
                    }
                    case 5:{
                        startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(selected_faculty,5).apply();
                        break;
                    }
                    default:break;

                }
            }
        });
    }

    public class FacultyAdapter extends ArrayAdapter
    {
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] faculty=new String[]{};

        public FacultyAdapter(Context context, int resource, String[] objects) {
            super(context, resource,objects);
            this.resource=resource;
            this.faculty=objects;
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position,View convertView,ViewGroup parent) {
            FacultyActivity.FacultyAdapter.ViewHolder viewHolder;
            if(convertView == null)
            {
                viewHolder=new ViewHolder();
                convertView=layoutInflater.inflate(resource,null);
                viewHolder.ivFaculty=(LetterImageView)convertView.findViewById(R.id.ivFaculty);
                viewHolder.tvFaculty=(TextView)convertView.findViewById(R.id.tvFaculty);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder=(ViewHolder)convertView.getTag();
            }

            viewHolder.ivFaculty.setOval(true);
            viewHolder.ivFaculty.setLetter(faculty[position].charAt(0));
            viewHolder.tvFaculty.setText(faculty[position]);
            return convertView;

        }
        class ViewHolder
        {
            private LetterImageView ivFaculty;
            private TextView tvFaculty;
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
    @Override
    public void onClick(View v)
    {
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(2000);
    }
}
