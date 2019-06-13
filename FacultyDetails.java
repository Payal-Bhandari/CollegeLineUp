package com.example.admin.collegelineup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyDetails extends AppCompatActivity {
    private CircleImageView circleImageView;
    private Toolbar toolbar;
    private TextView facultynames,phonenumber,email,subjects;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details);
        setupUIViews();
        initToolbar();
        setUPDetails();
    }

        private void setupUIViews()
        {
            toolbar=(Toolbar)findViewById(R.id.toolbarFacultyDetails);
            circleImageView=(CircleImageView)findViewById(R.id.ivFacultyDetails);
            facultynames=(TextView)findViewById(R.id.tvFacultyDetails);
            phonenumber=(TextView)findViewById(R.id.tv3);
            email=(TextView)findViewById(R.id.tv6);
            subjects=(TextView)findViewById(R.id.tv9);

        }

        private void initToolbar()
        {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Faculty Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        private void setUPDetails()
        {
           int faculty_pos = FacultyActivity.sharedPreferences.getInt(FacultyActivity.selected_faculty,0);
           String[] faculty_names=getResources().getStringArray(R.array.Faculty);
           int[] faculty_array=new int[]{R.array.ProfDDPukale,R.array.VinayaKulkarni,R.array.ShitalJadhav,R.array.KiranYesugade,R.array.AnjaliKadam,R.array.ShitalPawar};
           String[] FacultyDetails = getResources().getStringArray(faculty_array[faculty_pos]);
           phonenumber.setText(FacultyDetails[0]);
           email.setText(FacultyDetails[1]);
           subjects.setText(FacultyDetails[2]);
           facultynames.setText(faculty_names[faculty_pos]);
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

