package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.vaiotech.myschool.R;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.vaiotech.services.*;

import com.octo.android.robospice.SpiceManager;


public class AboutUsActivity extends Activity {
    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private ModelService modelService;
    private String selectedSchool;
    private String selectedCity;
    private TextView textView;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        textView = (TextView)findViewById(R.id.textViewAboutUS);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Calibri.ttf");
        textView.setTypeface(font);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        this.selectedSchool = settings.getString("SELECTED_SCHOOL", null);
        this.selectedCity = settings.getString("SELECTED_CITY", null);
        modelService = new ModelService(this.selectedSchool.split("-")[0].trim(),"1");
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(modelService , new RestServiceListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
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

    public final class RestServiceListener implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(AboutUsActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final String result) {
            Toast.makeText(AboutUsActivity.this, "success", Toast.LENGTH_SHORT).show();
            textView.setText(result);
        }
    }
}
