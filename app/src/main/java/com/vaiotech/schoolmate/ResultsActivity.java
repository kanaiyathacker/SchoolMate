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
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.vaiotech.bean.Item;
import com.vaiotech.bean.ItemAdapter;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.ExamTermService;
import com.vaiotech.services.FacultyInfoService;
import com.vaiotech.services.RestService;
import com.vaiotech.services.ResultsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultsActivity extends Activity implements AdapterView.OnItemClickListener {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private ResultsService resultsService;
    private ExamTermService examTermService;
    public ListView listView ;
    private Context context;
    List<Item> list;
    List<String> term;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Arial_Rounded_MT_Bold.ttf");

        TextView textViewClassName = (TextView)findViewById(R.id.textViewClassName);
        textViewClassName.setText("Class: "+studentInfo.getClassName());

        TextView  textViewSection = (TextView)findViewById(R.id.textViewSection);
        textViewSection.setText("Sec: "+studentInfo.getSection());

        TextView  textViewStudentNameValue = (TextView)findViewById(R.id.textViewStudentNameValue);
        textViewStudentNameValue.setText(studentInfo.getfName());

        TextView  textViewStudentRollNoValue = (TextView)findViewById(R.id.textViewStudentRollNoValue);
        textViewStudentRollNoValue.setText("Roll No: "+studentInfo.getRollNo());

        textViewClassName.setTypeface(font);
        textViewSection.setTypeface(font);
        textViewStudentNameValue.setTypeface(font);
        textViewStudentRollNoValue.setTypeface(font);

        resultsService = new ResultsService(studentInfo.getId() , studentInfo.getSchoolId() , studentInfo.getClassName() , studentInfo.getSection());
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        context = this;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = null;
        if(term.get(i).equals("CT")) {
            intent = new Intent(this ,InternalResultsActivity.class);
            intent.putExtra("TYPE" , "CT");
        } else {
            intent = new Intent(this ,ResultDetailsActivity.class);
            intent.putExtra("TYPE" , term.get(i));
        }
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(resultsService , new RestServiceListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }

    private class RestServiceListener implements com.octo.android.robospice.request.listener.RequestListener<List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(List result) {
            System.out.println(result);
//            for(Object currVal :result) {
//                Map map = (Map) currVal;
//                System.out.println("map..." + map);
//                String type = (String) map.get("typeDesc");
//                if(type.equals("AT1")) {
//                    TextView  textViewSection = (TextView)findViewById(R.id.textViewTerm1Value);
//                    textViewSection.setText("Aggregate : " + map.get("scored") + "/" + map.get("total"));
//                } else if(type.equals("AT2")) {
//                    TextView  textViewSection = (TextView)findViewById(R.id.textViewTerm2Value);
//                    textViewSection.setText("Aggregate : " + map.get("scored") + "/" + map.get("total"));
//                }else if(type.equals("AT3")) {
//                    TextView  textViewSection = (TextView)findViewById(R.id.textViewTerm3Value);
//                    textViewSection.setText("Aggregate : " + map.get("scored") + "/" + map.get("total"));
//                }

                list = new ArrayList<Item>();
                term = new ArrayList<String>();
                for(Object currVal :result) {
                    Map map = (Map) currVal;
                    Item item = new Item(""+map.get("typeDesc") , "Aggregate : " + map.get("scored") + "/" + map.get("total"));
                    list.add(item);
                    term.add(""+map.get("type"));
                }
                ItemAdapter adapter = new ItemAdapter(context , R.layout.list_item , list);
                listView.setAdapter(adapter);
                int[] colors = {0xFFFF0000, 0xFFFF0000, 0xFFFF0000}; // red for the example
                listView.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
                listView.setDividerHeight(1);
//            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.results, menu);
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

    public void onClickFirstTerm(View view) {
        RelativeLayout lo = (RelativeLayout)view;
        Intent intent = null;

//        if(lo.getId() == R.id.relativeTerm1) {
//            intent = new Intent(this ,ResultDetailsActivity.class);
//            intent.putExtra("TYPE" , "T1");
//        } else if(lo.getId() == R.id.relativeTerm2) {
//            intent = new Intent(this ,ResultDetailsActivity.class);
//            intent.putExtra("TYPE" , "T2");
//        } else if(lo.getId() == R.id.relativeTerm3) {
//            intent = new Intent(this ,ResultDetailsActivity.class);
//            intent.putExtra("TYPE" , "T3");
//        } else if(lo.getId() == R.id.relativeClassTest) {
//            intent = new Intent(this ,InternalResultsActivity.class);
//            intent.putExtra("TYPE" , "CT");
//        }
        startActivity(intent);
    }
}
