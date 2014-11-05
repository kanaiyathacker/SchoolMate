package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.vaiotech.bean.Item;
import com.vaiotech.bean.ItemAdapter;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.RestService;
import com.vaiotech.services.ResultDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultDetailsActivity extends Activity implements AdapterView.OnItemClickListener {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private ResultDetailsService resultDetailsService;
    private Context context;
    public static final String PREFS_NAME = "MyPrefsFile";
    public ListView listView ;
    List<Item> list;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);
        TextView textViewClassName = (TextView)findViewById(R.id.textViewClassName);
        textViewClassName.setText("Class: "+studentInfo.getClassName());
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Arial_Rounded_MT_Bold.ttf");

        TextView  textViewSection = (TextView)findViewById(R.id.textViewSection);
        textViewSection.setText("Sec: "+studentInfo.getSection());
        textViewSection.setTypeface(font);

        TextView  textViewStudentNameValue = (TextView)findViewById(R.id.textViewStudentNameValue);
        textViewStudentNameValue.setText(studentInfo.getfName());
        textViewStudentNameValue.setTypeface(font);

        TextView  textViewStudentRollNoValue = (TextView)findViewById(R.id.textViewStudentRollNoValue);
        textViewStudentRollNoValue.setText("Roll No: "+studentInfo.getRollNo());
        textViewStudentRollNoValue.setTypeface(font);

        type = getIntent().getStringExtra("TYPE");

        if("AT1".equalsIgnoreCase(type)) {
            type = "T1";
        } else if("AT2".equalsIgnoreCase(type)) {
            type = "T2";
        } else if("AT3".equalsIgnoreCase(type)) {
            type = "T3";
        } else if("AT4".equalsIgnoreCase(type)) {
            type = "T4";
        }

        resultDetailsService = new ResultDetailsService(studentInfo.getId() , studentInfo.getSchoolId() , studentInfo.getClassName() , studentInfo.getSection() , type);
        context = this;


        TextView  textViewTermHeader = (TextView)findViewById(R.id.textViewTermHeader);
        if("T1".equalsIgnoreCase(type)) textViewTermHeader.setText("Semester 1");
        if("T2".equalsIgnoreCase(type)) textViewTermHeader.setText("Semester 2");
        if("T3".equalsIgnoreCase(type)) textViewTermHeader.setText("Semester 3");
        if("CT".equalsIgnoreCase(type)) textViewTermHeader.setText("Class");

        textViewTermHeader.setTypeface(font);
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        int[] colors = {0xFFFF0000, 0xFFFF0000, 0xFFFF0000}; // red for the example
        listView.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        listView.setDividerHeight(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(resultDetailsService , new RestServiceListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intend = new Intent(this , SubjectResultActivity.class);
        intend.putExtra("SUB" , list.get(i).getTitle());
        intend.putExtra("TYPE" , type);
        startActivity(intend);
    }

    private class RestServiceListener implements com.octo.android.robospice.request.listener.RequestListener<List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(List result) {
            System.out.println(result);
            int count =1;
            list = new ArrayList<Item>();
            for(Object currVal :result) {
                Map map = (Map) currVal;
                Item item = new Item(""+map.get("subject") , "Your score :" + map.get("scored") + "/" + map.get("total"));
                list.add(item);
            }
            ItemAdapter adapter = new ItemAdapter(context , R.layout.list_item , list);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result_details, menu);
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
