package com.vaiotech.schoolmate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.vaiotech.bean.Item;
import com.vaiotech.bean.ItemAdapter;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.InternalResultsService;
import com.vaiotech.services.RestService;
import com.vaiotech.services.ResultsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InternalResultsActivity extends Activity {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private InternalResultsService internalResultsService;
    private Context context;
    public static final String PREFS_NAME = "MyPrefsFile";
    public ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_results);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);
        TextView textViewClassName = (TextView)findViewById(R.id.textViewClassName);
        textViewClassName.setText("Class: "+studentInfo.getClassName());

        TextView  textViewSection = (TextView)findViewById(R.id.textViewSection);
        textViewSection.setText("Sec: "+studentInfo.getSection());

        TextView  textViewStudentNameValue = (TextView)findViewById(R.id.textViewStudentNameValue);
        textViewStudentNameValue.setText(studentInfo.getfName());

        TextView  textViewStudentRollNoValue = (TextView)findViewById(R.id.textViewStudentRollNoValue);
        textViewStudentRollNoValue.setText("Roll No: "+studentInfo.getRollNo());

        String type = getIntent().getStringExtra("TYPE");
        internalResultsService = new InternalResultsService(studentInfo.getId() , studentInfo.getSchoolId() , studentInfo.getClassName() , studentInfo.getSection() , type);
        context = this;

        listView = (ListView)findViewById(R.id.listView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(internalResultsService , new RestServiceListener());
    }

    private class RestServiceListener implements com.octo.android.robospice.request.listener.RequestListener<List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(List result) {
            System.out.println(result);
            int count =1;
            List<Item> list = new ArrayList<Item>();
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
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.internal_results, menu);
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
