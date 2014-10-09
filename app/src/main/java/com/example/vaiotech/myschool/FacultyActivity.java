package com.example.vaiotech.myschool;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.vaiotech.services.ModelService;
import com.vaiotech.services.RestService;


public class FacultyActivity extends Activity {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private ModelService modelService;
    private TextView textView;
    private String selectedSchool;
    private String selectedCity;
    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        textView = (TextView)findViewById(R.id.textViewFaculty);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Calibri.ttf");
        textView.setTypeface(font);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        this.selectedSchool = settings.getString("SELECTED_SCHOOL", null);
        this.selectedCity = settings.getString("SELECTED_CITY", null);
        modelService = new ModelService(this.selectedSchool.split("-")[0].trim() , "005");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.faculty, menu);
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

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(modelService, new RestServiceListener());
    }

    private class RestServiceListener implements com.octo.android.robospice.request.listener.RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(String s) {
            textView.setText(s);
        }
    }
}
