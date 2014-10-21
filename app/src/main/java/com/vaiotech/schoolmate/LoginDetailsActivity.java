package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.vaiotech.myschool.R;


public class LoginDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_details);
        TextView textViewStudentName = (TextView)findViewById(R.id.textViewStudentName);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Calibri.ttf");
        textViewStudentName.setTypeface(font);

        TextView textViewStudentNameValue = (TextView)findViewById(R.id.textViewStudentNameValue);
        textViewStudentNameValue.setTypeface(font);

        TextView textViewStudentRollNo = (TextView)findViewById(R.id.textViewStudentRollNo);
        textViewStudentRollNo.setTypeface(font);

        TextView textViewStudentRollNoValue = (TextView)findViewById(R.id.textViewStudentRollNoValue);
        textViewStudentRollNoValue.setTypeface(font);

        TextView textViewStudentClass = (TextView)findViewById(R.id.textViewStudentClass);
        textViewStudentClass.setTypeface(font);

        TextView textViewStudentClassValue = (TextView)findViewById(R.id.textViewStudentClassValue);
        textViewStudentClassValue.setTypeface(font);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        System.out.println("id/....." + id);
        if (id == R.id.action_settings) {
            return true;
        }
        Intent intent = null;
        switch(item.getItemId()) {
            case R.id.admission:
                intent = new Intent(this ,AdmisionActivity.class);
                startActivity(intent);
                break;

            case R.id.schoolTiming:
                intent = new Intent(this ,SchoolTimingActivity.class);
                startActivity(intent);
                break;
            case R.id.busTiming:
                intent = new Intent(this ,BusTimingActivity.class);
                startActivity(intent);
                break;
            case R.id.newsAndEvents:
                intent = new Intent(this ,NewsAndEventsActivity.class);
                startActivity(intent);
                break;
            case R.id.career:
                intent = new Intent(this ,CareerActivity.class);
                startActivity(intent);
                break;

            case R.id.faculty:
                intent = new Intent(this ,FacultyActivity.class);
                startActivity(intent);
                break;
            case R.id.contactUs:
                intent = new Intent(this ,ContactUsActivity.class);
                startActivity(intent);
                break;
            case R.id.aboutUs:
                intent = new Intent(this ,AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.weeklyTimeTable:
                intent = new Intent(this ,TimeTableActivity.class);
                startActivity(intent);
                break;
            case R.id.dailyTimeTable:
                intent = new Intent(this ,DailyTimeTableActivity.class);
                startActivity(intent);
                break;
            case R.id.studentAttandance:
                intent = new Intent(this ,StudentAttendanceActivity.class);
                startActivity(intent);
                break;

            case R.id.studentFacultyInfo:
                intent = new Intent(this ,StudentFacultyInfoActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
