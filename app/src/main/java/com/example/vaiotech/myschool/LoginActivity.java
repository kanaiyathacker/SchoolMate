package com.example.vaiotech.myschool;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginActivity extends Activity {

    private String selectedSchool;
    private String selectedCity;
    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        System.out.println("SELECTED_SCHOOL... "+settings.getString("SELECTED_SCHOOL" , null));
        System.out.println("SELECTED_CITY... "+settings.getString("SELECTED_CITY" , null));
        TextView schoolName = (TextView)findViewById(R.id.textViewSchoolName);
        this.selectedSchool = settings.getString("SELECTED_SCHOOL" , null);
        this.selectedCity = settings.getString("SELECTED_CITY" , null);
        schoolName.setText(this.selectedSchool);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    public void anonymous(View view) {
        Intent intent = new Intent(this ,MenuHomeActivity.class);
        intent.putExtra("LOIN_USER","ANONYMOUS");
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this ,LoginDetailsActivity.class);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent = new Intent(this ,RegisterActivity.class);
        startActivity(intent);
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
