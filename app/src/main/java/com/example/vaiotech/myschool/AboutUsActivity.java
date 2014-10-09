package com.example.vaiotech.myschool;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.vaiotech.services.*;

import com.octo.android.robospice.SpiceManager;


public class AboutUsActivity extends Activity {
    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private RestServiceImpl restServiceImpl;
    private String selectedSchool;
    private String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        System.out.println("MenuHomeActivity SELECTED_SCHOOL... "+this.getIntent().getStringExtra("SELECTED_SCHOOL"));
        System.out.println("MenuHomeActivity SELECTED_CITY... "+this.getIntent().getStringExtra("SELECTED_CITY"));
        this.selectedSchool = this.getIntent().getStringExtra("SELECTED_SCHOOL");
        this.selectedCity = this.getIntent().getStringExtra("SELECTED_CITY");
        restServiceImpl = new RestServiceImpl(this.selectedSchool.split("-")[0].trim(),"001");
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(restServiceImpl , new RestServiceListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about_us, menu);
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

    public void updateContributors(final String result) {
        System.out.println("result... " + result);
    }

    public final class RestServiceListener implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(AboutUsActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final String result) {
            Toast.makeText(AboutUsActivity.this, "success", Toast.LENGTH_SHORT).show();
            updateContributors(result);
        }
    }
}
