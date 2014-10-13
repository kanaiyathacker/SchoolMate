package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;

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
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Calibri.ttf");
        schoolName.setTypeface(font);

        TextView textViewTKMATS = (TextView)findViewById(R.id.textViewTKMATS);
        textViewTKMATS.setTypeface(font);

        TextView textViewExiUser = (TextView)findViewById(R.id.textViewExiUser);
        textViewExiUser.setTypeface(font);

        TextView textViewLoginID = (TextView)findViewById(R.id.textViewLoginID);
        textViewLoginID.setTypeface(font);

        TextView editTextLoginID = (TextView)findViewById(R.id.editTextLoginID);
        editTextLoginID.setTypeface(font);

        TextView textViewPassword = (TextView)findViewById(R.id.textViewPassword);
        textViewPassword.setTypeface(font);

        TextView editTextPassword = (TextView)findViewById(R.id.editTextPassword);
        editTextPassword.setTypeface(font);

        TextView loginButton = (TextView)findViewById(R.id.loginButton);
        loginButton.setTypeface(font);

        TextView textViewNewUser = (TextView)findViewById(R.id.textViewNewUser);
        textViewNewUser.setTypeface(font);

        TextView registration = (TextView)findViewById(R.id.registration);
        registration.setTypeface(font);





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
        Student student = new Student();
        student.setfName("Kanaiya");
        student.setmName("Aswin");
        student.setlName("Thacker");
        student.setClassName("10");
        student.setSection("A");
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("STUDENT_INFO" , new Gson().toJson(student)).commit();
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
