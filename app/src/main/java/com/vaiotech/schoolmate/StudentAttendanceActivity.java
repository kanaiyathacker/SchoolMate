package com.vaiotech.schoolmate;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alamkanak.weekview.WeekView;
import com.google.gson.Gson;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.roomorama.caldroid.CaldroidFragment;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.AttendanceService;
import com.vaiotech.services.RestService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StudentAttendanceActivity extends FragmentActivity {

    private WeekView mWeekView;
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private AttendanceService attendanceService;
    public static final String PREFS_NAME = "MyPrefsFile";
    private CaldroidFragment caldroidFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);


        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);
        attendanceService = new AttendanceService(studentInfo.getId() , studentInfo.getSchoolId() , studentInfo.getSection() , studentInfo.getClassName());
        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        final FrameLayout fl = (FrameLayout)findViewById(R.id.fraContainer);
        final LinearLayout ll = (LinearLayout)findViewById(R.id.linearMV);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fraContainer ,caldroidFragment);
        t.commit();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.MV == checkedId) {
                    fl.setVisibility(View.VISIBLE);
                    ll.setVisibility(View.INVISIBLE);
                } else if (R.id.YV == checkedId) {
                    fl.setVisibility(View.INVISIBLE);
                    ll.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void buildYearlyView(List result) {
        final LinearLayout ll = (LinearLayout)findViewById(R.id.linearMV);
        for(Object currVal :result) {
            Map map = (Map) currVal;
            System.out.println(map);
            String date = (String)map.get("date");
            int month = Integer.parseInt(date.split("/")[0]);
            System.out.println(month);
            TextView tv = null;
            String val = null;
            switch (month) {
                case 1:
                    tv = (TextView)findViewById(R.id.tViewMonth1Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 2:
                    tv = (TextView)findViewById(R.id.tViewMonth2Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 3:
                    tv = (TextView)findViewById(R.id.tViewMonth3Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 4:
                    tv = (TextView)findViewById(R.id.tViewMonth4Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 5:
                    tv = (TextView)findViewById(R.id.tViewMonth5Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 6:
                    tv = (TextView)findViewById(R.id.tViewMonth6Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 7:
                    tv = (TextView)findViewById(R.id.tViewMonth7Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 8:
                    tv = (TextView)findViewById(R.id.tViewMonth8Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 9:
                    tv = (TextView)findViewById(R.id.tViewMonth9Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 10:
                    tv = (TextView)findViewById(R.id.tViewMonth10Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 11:
                    tv = (TextView)findViewById(R.id.tViewMonth11Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                case 12:
                    tv = (TextView)findViewById(R.id.tViewMonth12Text);
                    val = (String)tv.getText();
                    if(val != null && val.length() > 1) {
                        int i = Integer.parseInt(val);
                        tv.setText(++i);
                    } else {
                        tv.setText("1");
                    }
                    break;
                default: break;

            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(attendanceService , new RestServiceListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }

    public final class RestServiceListener implements RequestListener<List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
        }

        @Override
        public void onRequestSuccess(List result) {
            System.out.println("List..." + result);
            HashMap<java.util.Date, Integer> mapVal = new HashMap<java.util.Date, Integer>();
            for(Object currVal :result) {
                Map map = (Map) currVal;
                System.out.println(map);
                String date = (String)map.get("date");
                mapVal.put(new Date(date), R.color.event_color_02);
            }
            caldroidFragment.setBackgroundResourceForDates(mapVal);
            caldroidFragment.refreshView();
            buildYearlyView(result);
        }
    }

    public Date convertToDate(String date) {
        Date retVal = null;
        SimpleDateFormat fmt = new SimpleDateFormat();
        try {
            retVal = fmt.parse(date);
        } catch (Exception e) {

        }
        return retVal;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_attendance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }


}
