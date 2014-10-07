package com.example.vaiotech.myschool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.vaiotech.services.CityService;
import com.vaiotech.services.RestService;
import com.vaiotech.services.RestServiceImpl;
import com.vaiotech.services.SchoolService;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends Activity {
    Spinner spinner;
    Spinner schoolSpinner;
    Context context;
    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private RestServiceImpl restServiceImpl;
    private CityService cityService;
    private SchoolService schoolService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restServiceImpl = new RestServiceImpl();
        context = this;
        setContentView(R.layout.activity_home);
        System.out.println(getAssets().getLocales());
        spinner = (Spinner)findViewById(R.id.stateSpinner);
        spinner = (Spinner) findViewById(R.id.stateSpinner);
        schoolSpinner = (Spinner) findViewById(R.id.schoolSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, getStates());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str = (String)adapterView.getItemAtPosition(i);
                System.out.println(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        schoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str = (String) adapterView.getItemAtPosition(i);
                System.out.println(str);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, getSchools(str));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                schoolSpinner.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void next(View view) {
        Intent intent = new Intent(this ,LoginActivity.class);
        startActivity(intent);
    }

    private List getStates() {
        List retVal = new ArrayList();
        retVal.add("Maharashtra");
        retVal.add("Gujarat");
        return retVal;
    }

    private List getSchools(String state) {
        List retVal = new ArrayList();
        if(state.equals("Maharashtra")) {
            retVal.add("A");
            retVal.add("B");
        } else {
            retVal.add("C");
            retVal.add("D");
        }

        return retVal;
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(restServiceImpl , new RestServiceListener());
    }

    public final class RestServiceListener implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(HomeActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final String result) {
            Toast.makeText(HomeActivity.this, "success", Toast.LENGTH_SHORT).show();
            updateContributors(result);
        }
    }

    public void updateContributors(final String result) {
        System.out.println("result... " + result);
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
