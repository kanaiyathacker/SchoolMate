package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.FacultyInfoService;
import com.vaiotech.services.RestService;
import com.vaiotech.services.WeeklyTimeTableService;

import java.util.List;
import java.util.Map;

public class StudentFacultyInfoActivity extends Activity {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private FacultyInfoService facultyInfoService;
    private Context context;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_faculty_info);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);
        TextView  textViewClassName = (TextView)findViewById(R.id.textViewClassName);
        textViewClassName.setText("Class: "+studentInfo.getClassName());

        TextView  textViewSection = (TextView)findViewById(R.id.textViewSection);
        textViewSection.setText("Sec: "+studentInfo.getSection());

        TextView  textViewStudentNameValue = (TextView)findViewById(R.id.textViewStudentNameValue);
        textViewStudentNameValue.setText(studentInfo.getfName());

        TextView  textViewStudentRollNoValue = (TextView)findViewById(R.id.textViewStudentRollNoValue);
        textViewStudentRollNoValue.setText("Roll No: "+studentInfo.getRollNo());
        facultyInfoService = new FacultyInfoService(studentInfo.getSchoolId() , studentInfo.getClassName() , studentInfo.getSection());
        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(facultyInfoService , new RestServiceListener());
    }


    public final class RestServiceListener implements RequestListener<List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(StudentFacultyInfoActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(List list) {
            System.out.println("List..." + list);
            int count = 1;
            for(Object currVal : list) {
                Map map = (Map)currVal;
                TextView tr = null;
                TextView trd = null;
                switch (count) {
                    case 1 :
                          tr = (TextView) findViewById(R.id.textViewTeacherRole1);
                          trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails1);
                        break;
                    case 2 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole2);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails2);
                        break;
                    case 3 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole3);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails3);
                        break;
                    case 4 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole4);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails4);
                        break;
                    case 5 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole5);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails6);
                        break;
                    case 6 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole6);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails6);
                        break;
                    case 7 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole7);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails7);
                        break;
                    case 8 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole8);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails8);
                        break;
                    case 9 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole9);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails9);
                        break;
                    case 10 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole10);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails10);
                        break;
                    case 11 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole11);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails11);
                        break;
                    case 12 :
                        tr = (TextView) findViewById(R.id.textViewTeacherRole12);
                        trd = (TextView) findViewById(R.id.textViewTeacherRoleDetails12);
                        break;
                    default:
                        break;
                }
                if(tr != null && trd != null) {
                    tr.setText(map.get("teacherRole").toString());
                    trd.setText(map.get("teacherName").toString() + "\n" + map.get("teacherEmail").toString());
                }
                count++;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_faculty_info, menu);
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
