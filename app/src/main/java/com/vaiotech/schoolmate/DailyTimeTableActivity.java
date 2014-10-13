package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.DayTimeTableService;
import com.vaiotech.services.RestService;
import com.vaiotech.services.WeeklyTimeTableService;

import java.util.List;

public class DailyTimeTableActivity extends Activity {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private DayTimeTableService dayTimeTableService;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_time_table);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);
        dayTimeTableService = new DayTimeTableService(studentInfo.getSchoolId() , studentInfo.getClassName() , studentInfo.getSection() , "Monday");
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(dayTimeTableService , new RestServiceListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.daily_time_table, menu);
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

    public final class RestServiceListener implements RequestListener<List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(DailyTimeTableActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final List result) {
            System.out.println("result... " + result);
        }
    }
}
