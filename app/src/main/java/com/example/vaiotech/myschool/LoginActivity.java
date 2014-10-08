package com.example.vaiotech.myschool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginActivity extends Activity {

    private String selectedSchool;
    private String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        System.out.println(this.getIntent().getStringExtra("SELECTED_SCHOOL"));
        System.out.println(this.getIntent().getStringExtra("SELECTED_CITY"));
        TextView schoolName = (TextView)findViewById(R.id.textViewSchoolName);
        schoolName.setText(this.getIntent().getStringExtra("SELECTED_SCHOOL"));
        this.selectedSchool = this.getIntent().getStringExtra("SELECTED_SCHOOL");
        this.selectedCity = this.getIntent().getStringExtra("SELECTED_CITY");
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
        intent.putExtra("SELECTED_SCHOOL" , selectedSchool);
        intent.putExtra("SELECTED_CITY" , selectedCity);
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
