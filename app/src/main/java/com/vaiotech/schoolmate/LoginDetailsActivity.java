package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.RestService;
import com.vaiotech.services.WeeklyTimeTableService;

import java.util.List;


public class LoginDetailsActivity extends Activity {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private WeeklyTimeTableService weeklyTimeTableService;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences sharedPreferences;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_details);
        sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);
        context = this;
        weeklyTimeTableService = new WeeklyTimeTableService(studentInfo.getSchoolId() , studentInfo.getClassName() , studentInfo.getSection());

        TextView textViewStudentName = (TextView)findViewById(R.id.textViewStudentName);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Calibri.ttf");
        textViewStudentName.setTypeface(font);

        TextView textViewStudentNameValue = (TextView)findViewById(R.id.textViewStudentNameValue);
        textViewStudentNameValue.setTypeface(font);
        textViewStudentNameValue.setText(studentInfo.getfName() + " " + studentInfo.getmName() + " " + studentInfo.getlName());
        TextView textViewStudentRollNo = (TextView)findViewById(R.id.textViewStudentRollNo);
        textViewStudentRollNo.setTypeface(font);

        TextView textViewStudentRollNoValue = (TextView)findViewById(R.id.textViewStudentRollNoValue);
        textViewStudentRollNoValue.setTypeface(font);
        textViewStudentRollNoValue.setText(studentInfo.getRollNo());

        TextView textViewStudentClass = (TextView)findViewById(R.id.textViewStudentClass);
        textViewStudentClass.setTypeface(font);

        TextView textViewStudentClassValue = (TextView)findViewById(R.id.textViewStudentClassValue);
        textViewStudentClassValue.setTypeface(font);
        textViewStudentClassValue.setText(studentInfo.getClassName());

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public final class RestServiceListener implements RequestListener<List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(LoginDetailsActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final List result) {
            System.out.println("timetableList... " + result);
            Gson gson = new Gson();
            sharedPreferences.edit().putString("TIME_TABLE" , gson.toJson(result)).commit();
            Intent intent = new Intent(context ,DailyTimeTableActivity.class);
            startActivity(intent);
        }
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
            case R.id.dailyTimeTable:
                intent = new Intent(this ,DailyTimeTableActivity.class);
                spiceManager.start(this);
                spiceManager.execute(weeklyTimeTableService , new RestServiceListener());

                break;
            case R.id.studentAttandance:
                intent = new Intent(this ,StudentAttendanceActivity.class);
                startActivity(intent);
                break;

            case R.id.studentFacultyInfo:
                intent = new Intent(this ,StudentFacultyInfoActivity.class);
                startActivity(intent);
                break;

            case R.id.results:
                intent = new Intent(this ,ResultsActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
