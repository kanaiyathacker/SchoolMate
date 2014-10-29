package com.vaiotech.schoolmate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.vaiotech.Utility.BarGraph;
import com.vaiotech.bean.Student;
import com.vaiotech.myschool.R;
import com.vaiotech.services.RestService;
import com.vaiotech.services.ResultSubjectService;

import java.util.ArrayList;
import java.util.List;

public class SubjectResultActivity extends Activity  implements View.OnClickListener , SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    private SpiceManager spiceManager = new SpiceManager(RestService.class);
    private ResultSubjectService resultSubjectService;
    private Context context;
    public static final String PREFS_NAME = "MyPrefsFile";
    private TextView textViewYSVal;
    private TextView textViewHSVal;
    private TextView textViewCAVal;
    private TextView textViewLSVal;

    private BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private String[] mMonths = new String[] {
            "Your Score", "Highest Score" , "Average Score", "Lowest Score"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_result);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String studentInfoJson = sharedPreferences.getString("STUDENT_INFO" , null);
        Student studentInfo = new Gson().fromJson(studentInfoJson , Student.class);
        TextView textViewClassName = (TextView)findViewById(R.id.textViewClassName);
        textViewClassName.setText("Class: "+studentInfo.getClassName());

        TextView  textViewSection = (TextView)findViewById(R.id.textViewSection);
        textViewSection.setText("Sec: "+studentInfo.getSection());

        TextView  textViewStudentNameValue = (TextView)findViewById(R.id.textViewStudentNameValue);
        textViewStudentNameValue.setText(studentInfo.getfName());

        TextView  textViewTermHeader = (TextView)findViewById(R.id.textViewTermHeader);
        textViewTermHeader.setText(studentInfo.getfName());



         textViewYSVal = (TextView)findViewById(R.id.textViewYSVal);
         textViewHSVal = (TextView)findViewById(R.id.textViewHSVal);
         textViewCAVal = (TextView)findViewById(R.id.textViewCAVal);
         textViewLSVal = (TextView)findViewById(R.id.textViewLSVal);


        TextView  textViewStudentRollNoValue = (TextView)findViewById(R.id.textViewStudentRollNoValue);
        textViewStudentRollNoValue.setText("Roll No: "+studentInfo.getRollNo());
        String subject = getIntent().getStringExtra("SUB");
        String type = getIntent().getStringExtra("TYPE");
        if("T1".equalsIgnoreCase(type)) textViewTermHeader.setText("Semester 1" + subject);
        if("T2".equalsIgnoreCase(type)) textViewTermHeader.setText("Semester 2" + subject);
        if("T3".equalsIgnoreCase(type)) textViewTermHeader.setText("Semester 3" + subject);
        if("CLASS".equalsIgnoreCase(type)) textViewTermHeader.setText("Class");
        resultSubjectService = new ResultSubjectService(studentInfo.getId() , studentInfo.getSchoolId() , studentInfo.getClassName() , studentInfo.getSection() ,type, subject);
        context = this;
//
//        Button onClick = (Button)findViewById(R.id.button);
//        onClick.setOnClickListener(this);
//
//        getIntent().getStringExtra("SUB");
//
//        tvX = (TextView) findViewById(R.id.tvXMax);
//        tvY = (TextView) findViewById(R.id.tvYMax);
//
//        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
//        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
////
//        mChart = (BarChart) findViewById(R.id.chart);
//        mChart.setOnChartValueSelectedListener(this);
//
//        // enable the drawing of values
//        mChart.setDrawYValues(true);
//
//        mChart.setDrawValueAboveBar(true);
//
//        mChart.setDescription("");
//
//        // if more than 60 entries are displayed in the chart, no values will be
//        // drawn
//        mChart.setMaxVisibleValueCount(60);
//
//        // disable 3D
//        mChart.set3DEnabled(true);
//
//        // scaling can now only be done on x- and y-axis separately
//        mChart.setPinchZoom(true);
//
//        // draw shadows for each bar that show the maximum value
//        // mChart.setDrawBarShadow(true);
//
//        mChart.setUnit(" €");
//
//        // mChart.setDrawXLabels(false);
//
//        mChart.setDrawGridBackground(false);
//        mChart.setDrawHorizontalGrid(true);
//        mChart.setDrawVerticalGrid(false);
//        // mChart.setDrawYLabels(false);
//
//        // sets the text size of the values inside the chart
//        mChart.setValueTextSize(10f);
//
//        mChart.setDrawBorder(false);
//        // mChart.setBorderPositions(new BorderPosition[] {BorderPosition.LEFT,
//        // BorderPosition.RIGHT});
//
//        XLabels xl = mChart.getXLabels();
//        xl.setPosition(XLabels.XLabelPosition.BOTTOM);
//        xl.setCenterXLabelText(true);
//
//        YLabels yl = mChart.getYLabels();
//        yl.setLabelCount(8);
//        yl.setPosition(YLabels.YLabelPosition.BOTH_SIDED);
//
//
//        setData(4, 50);
//
//        // setting data
//        mSeekBarY.setProgress(50);
//        mSeekBarX.setProgress(12);
//
//        mSeekBarY.setOnSeekBarChangeListener(this);
//        mSeekBarX.setOnSeekBarChangeListener(this);
//
//        Legend l = mChart.getLegend();
//        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
//        l.setFormSize(8f);
//        l.setXEntrySpace(4f);
    }


    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        spiceManager.execute(resultSubjectService, new RestServiceListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }


    private class RestServiceListener implements com.octo.android.robospice.request.listener.RequestListener<Object> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(Object result) {
            LinkedTreeMap map = (LinkedTreeMap)result;
            System.out.println(map.get("yScore"));
            System.out.println(map.get("hScore"));
            System.out.println(map.get("avg"));
            System.out.println(map.get("lScore"));

            textViewYSVal.setText(""+map.get("yScore"));
            textViewHSVal.setText(""+map.get("hScore"));
            textViewCAVal.setText(""+map.get("avg"));
            textViewLSVal.setText(""+map.get("lScore"));
        }
    }

    @Override
    public void onClick(View v) {
                BarGraph bar = new BarGraph();
                Intent barIntent = bar.getIntent(this);
                startActivity(barIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.subject_result, menu);
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
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvX.setText("" + (mSeekBarX.getProgress() + 1));
        tvY.setText("" + (mSeekBarY.getProgress()));

        setData(mSeekBarX.getProgress(), mSeekBarY.getProgress());
        mChart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    private void setData(int count, float range) {
        int[] firstData={90,100,30,30};
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(mMonths[i]);
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
            yVals1.add(new BarEntry(firstData[i], i));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "DataSet");
        set1.setBarSpacePercent(35f);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);

        mChart.setData(data);
    }

    @SuppressLint("NewApi")
    @Override
    public void onValueSelected(Entry e, int dataSetIndex) {

        if (e == null)
            return;

        RectF bounds = mChart.getBarBounds((BarEntry) e);
        PointF position = mChart.getPosition(e);
    }

    public void onNothingSelected() {
    };
}
