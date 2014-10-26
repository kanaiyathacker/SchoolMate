package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.google.gson.Gson;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class DailyTimeTableActivity extends Activity implements WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener {

    public static final String PREFS_NAME = "MyPrefsFile";
    private WeekView mWeekView;
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private List timetableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_time_table);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);

        String timeTableInfoJson = sharedPreferences.getString("TIME_TABLE" , null);
        System.out.println("timeTableInfoJsontimeTableInfoJson.... " + timeTableInfoJson);
        List timeTableListObj = new Gson().fromJson(timeTableInfoJson , List.class);

        System.out.println("timtablelist.... " + timeTableListObj);
        timetableList = timeTableListObj;

        mWeekView = (WeekView)findViewById(R.id.weekView);
        mWeekView.setOnEventClickListener(this);
        mWeekView.setMonthChangeListener(this);
        mWeekView.setEventLongPressListener(this);

        mWeekView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                int scrollX = mWeekView.getScrollX(); //for horizontalScrollView
                int scrollY = mWeekView.getScrollY(); //for verticalScrollView
                System.out.println("timtablelist.... " + scrollX);
                System.out.println("timtablelist.... " + mWeekView.toString());
                //DO SOMETHING WITH THE SCROLL COORDINATES

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.daily_time_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_today:
                mWeekViewType = TYPE_DAY_VIEW;
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(DailyTimeTableActivity.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(DailyTimeTableActivity.this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        int count = 1;

        for(int i =0; i <=4;i++) {
            int date = i == 0 ? 0 : i * 7;
            for(Object currVal : timetableList) {
                Map map = (Map)currVal;
                String ttStart = map.get("startTime").toString();
                String ttEnd = map.get("endTime").toString();
                Calendar startTime = Calendar.getInstance();
                int day = getDay(map.get("day").toString());
                startTime.set(Calendar.DAY_OF_WEEK, day);
                startTime.set(Calendar.DATE, startTime.get(Calendar.DATE) + date );
                startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ttStart.split(":")[0]));
                startTime.set(Calendar.MINUTE, Integer.parseInt(ttStart.split(":")[1]) + 1);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                Calendar endTime = (Calendar) startTime.clone();
                endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ttEnd.split(":")[0]));
                endTime.set(Calendar.MINUTE, Integer.parseInt(ttEnd.split(":")[1]));
                WeekViewEvent event = new WeekViewEvent(1, map.get("subName").toString() + " ( " + ttStart + " - " + ttEnd + " ) " + "\n" + map.get("profName").toString(), startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_02));
                events.add(event);
                System.out.println(startTime.get(Calendar.DATE) + "/ " + startTime.get(Calendar.MONTH));
            }
        }
        return events;
    }

    private int getDay(String day) {
        if("MONDAY".equalsIgnoreCase(day)) return Calendar.MONDAY;
        if("TUESDAY".equalsIgnoreCase(day)) return Calendar.TUESDAY;
        if("WEDNESDAY".equalsIgnoreCase(day)) return Calendar.WEDNESDAY;
        if("THURSDAY".equalsIgnoreCase(day)) return Calendar.THURSDAY;
        if("FRIDAY".equalsIgnoreCase(day)) return Calendar.FRIDAY;
        if("SATURDAY".equalsIgnoreCase(day)) return Calendar.SATURDAY;
        if("SUNDAY".equalsIgnoreCase(day)) return Calendar.SUNDAY;
        return -1;
    }
}
