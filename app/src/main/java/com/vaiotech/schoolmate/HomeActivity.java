package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.vaiotech.myschool.R;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.vaiotech.services.CityService;
import com.vaiotech.services.RestService;
import com.vaiotech.services.SchoolService;

import java.util.List;


public class HomeActivity extends Activity {
    Spinner citySpinner;
    Spinner schoolSpinner;
    Context context;
    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private CityService cityService;
    private SchoolService schoolService;
    private String selectedSchool;
    private String selectedCity;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cityService = new CityService();
        schoolService = new SchoolService();
        context = this;
        System.out.println(getAssets().getLocales());
        citySpinner = (Spinner)findViewById(R.id.stateSpinner);
        schoolSpinner = (Spinner) findViewById(R.id.schoolSpinner);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String city = (String) adapterView.getItemAtPosition(i);
                selectedCity = city;
                getSchools(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        schoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String school = (String) adapterView.getItemAtPosition(i);
                selectedSchool = school;
                System.out.println("school.... "+school);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }

    public void next(View view) {
        Intent intent = new Intent(this ,LoginActivity.class);
        System.out.println(schoolSpinner.getSelectedItem().toString());
        System.out.println(citySpinner.getSelectedItem().toString());
        intent.putExtra("SELECTED_SCHOOL" , selectedSchool);
        intent.putExtra("SELECTED_CITY" , selectedCity);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("SELECTED_CITY", selectedCity);
        editor.putString("SELECTED_SCHOOL", selectedSchool);
        editor.commit();

        startActivity(intent);
    }

    private void getSchools(String city) {
        schoolService.setCityID(city.split("-")[0]);
        spiceManager.execute(schoolService, new SchoolServiceListener());
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(cityService, new RestServiceListener());
    }

    public final class SchoolServiceListener implements RequestListener<List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(HomeActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final List result) {
            Toast.makeText(HomeActivity.this, "success", Toast.LENGTH_SHORT).show();
            updateSchool(result);
        }
    }

    public void updateSchool(final List result) {
        if(result != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, result);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            schoolSpinner.setAdapter(adapter);
        }
    }


    public final class RestServiceListener implements RequestListener<List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(HomeActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final List result) {
            Toast.makeText(HomeActivity.this, "success", Toast.LENGTH_SHORT).show();
            updateContributors(result);
        }
    }

    public void updateContributors(final List result) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, result);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
