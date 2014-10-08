package com.example.vaiotech.myschool;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MenuHomeActivity extends Activity {

    private String selectedSchool;
    private String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.activity_home_activity);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
                intent.putExtra("SELECTED_SCHOOL" , selectedSchool);
                intent.putExtra("SELECTED_CITY" , selectedCity);
                startActivity(intent);
                break;
            case R.id.calendar:
                 intent = new Intent(this ,CalendarActivity.class);
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
            case R.id.fees:
                 intent = new Intent(this ,FeesActivity.class);
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
        }
        return super.onOptionsItemSelected(item);
    }
}
