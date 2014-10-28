package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.RestService;
import com.vaiotech.services.ResultDetailsService;
import com.vaiotech.services.ResultsService;

import java.util.List;
import java.util.Map;

public class ResultDetailsActivity extends Activity {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private ResultDetailsService resultDetailsService;
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

        resultDetailsService = new ResultDetailsService(studentInfo.getId() , studentInfo.getSchoolId() , studentInfo.getClassName() , studentInfo.getSection() , "T1");
        context = this;
        String type = getIntent().getStringExtra("TYPE");
        System.out.println("Type... "  + type);
        TextView textViewTermHeader = (TextView)findViewById(R.id.textViewTermHeader);
        textViewTermHeader.setText(type);

        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(resultDetailsService , new RestServiceListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }

    private class RestServiceListener implements com.octo.android.robospice.request.listener.RequestListener<List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(List result) {
            System.out.println(result);
            int count =1;
            for(Object currVal :result) {
                Map map = (Map) currVal;
                System.out.println("map..." + map);
                String type = (String) map.get("type");
                TextView textViewNameSub = null;
                TextView textViewNameScore = null;
                RelativeLayout rlo = null;
                switch (count++) {
                    case 1:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubOne);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreOne);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeOne);
                        break;
                    case 2:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubTwo);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreTwo);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeTwo);
                        break;
                    case 3:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubThree);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreThree);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeThree);
                        break;
                    case 4:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubFour);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreFour);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeFour);
                        break;
                    case 5:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubFive);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreFive);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeFive);
                        break;
                    case 6:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubSix);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreSix);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeSix);
                        break;
                    case 7:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubSeven);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreSeven);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeSeven);
                        break;
                    case 8:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubEight);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreEight);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeEight);
                        break;
                    case 9:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubNine);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreNine);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeNine);
                        break;
                    case 10:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubTen);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreTen);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeTen);
                        break;
                    case 11:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubEleven);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreEleven);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeEleven);
                        break;
                    case 12:
                        textViewNameSub = (TextView)findViewById(R.id.textViewNameSubTwelve);
                        textViewNameScore = (TextView)findViewById(R.id.textViewNameScoreTweleve);
                        rlo = (RelativeLayout)findViewById(R.id.innerRelativeTwelve);
                        break;
                     default:
                        break;
                }

                textViewNameSub.setText(""+map.get("subject"));
                textViewNameScore.setText("Your score :" + map.get("scored") + "/" + map.get("total") );
                rlo.setVisibility(View.VISIBLE);
            }
        }
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

    public void onClick(View view) {
        Intent intent = new Intent(this , SubjectResultActivity.class);
        startActivity(intent);
    }
}
