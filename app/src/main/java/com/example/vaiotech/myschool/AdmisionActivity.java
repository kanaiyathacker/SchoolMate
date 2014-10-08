package com.example.vaiotech.myschool;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.vaiotech.services.AdmissionService;
import com.vaiotech.services.ModelService;
import com.vaiotech.services.RestService;


public class AdmisionActivity extends Activity {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private ModelService modelService;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admision);
        textView = (TextView)findViewById(R.id.textViewAdmission);
//        System.out.println(this.getIntent().getStringExtra("SELECTED_SCHOOL"));
//        System.out.println(this.getIntent().getStringExtra("SELECTED_CITY"));
        modelService = new ModelService("101" , "001");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admision, menu);
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
