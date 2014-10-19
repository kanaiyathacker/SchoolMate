package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.RestService;
import com.vaiotech.services.WeeklyTimeTableService;

import java.util.List;

public class TimeTableActivity extends Activity {
    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private WeeklyTimeTableService weeklyTimeTableService;
    private Context context;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_time_table);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);
        weeklyTimeTableService = new WeeklyTimeTableService("101" , studentInfo.getClassName() , studentInfo.getSection());
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(weeklyTimeTableService , new RestServiceListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.time_table, menu);
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
            Toast.makeText(TimeTableActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final List result) {
            System.out.println("result... " + result);
            int count = 1;
            for(Object obj : result) {
                LinkedTreeMap map = (LinkedTreeMap)obj;
                System.out.println("obj... " + map.get("subName"));
                TextView textViewPeriods = null;
                switch (count) {
                    case 1:
//                        textViewPeriods  = (TextView)((Activity) context).findViewById(R.id.textViewPeriods1);
//                        break;

//                    case 2:
//                        textViewPeriods  = (TextView)((Activity) context).findViewById(R.id.textViewPeriods2);
//                        break;
//
//                    case 3:
//                        textViewPeriods  = (TextView)((Activity) context).findViewById(R.id.textViewPeriods3);
//                        break;
//
//                    case 4:
//                        textViewPeriods  = (TextView)((Activity) context).findViewById(R.id.textViewPeriods4);
//                        break;

                    case 5:
                        break;

                    case 6:
                        break;

                }
                if(textViewPeriods != null)
                    textViewPeriods.setText(""+map.toString());
                count++;
            }
        }
    }
}
