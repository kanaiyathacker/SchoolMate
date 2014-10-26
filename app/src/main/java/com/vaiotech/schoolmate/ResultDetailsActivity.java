package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.octo.android.robospice.SpiceManager;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.RestService;
import com.vaiotech.services.ResultsService;

public class ResultDetailsActivity extends Activity {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private ResultsService resultsService;
    private Context context;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);
        TextView textViewClassName = (TextView)findViewById(R.id.textViewClassName);
        textViewClassName.setText("Class: "+studentInfo.getClassName());

        TextView  textViewSection = (TextView)findViewById(R.id.textViewSection);
        textViewSection.setText("Sec: "+studentInfo.getSection());

        TextView  textViewStudentNameValue = (TextView)findViewById(R.id.textViewStudentNameValue);
        textViewStudentNameValue.setText(studentInfo.getfName());

        TextView  textViewStudentRollNoValue = (TextView)findViewById(R.id.textViewStudentRollNoValue);
        textViewStudentRollNoValue.setText("Roll No: "+studentInfo.getRollNo());

        resultsService = new ResultsService(studentInfo.getId() , studentInfo.getSchoolId() , studentInfo.getClassName() , studentInfo.getSection());
        context = this;


        String type = getIntent().getStringExtra("TYPE");
        System.out.println("Type... "  + type);
        TextView  textViewTermHeader = (TextView)findViewById(R.id.textViewTermHeader);
        textViewTermHeader.setText(type);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result_details, menu);
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
}
