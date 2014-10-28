package com.vaiotech.Utility;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
public class BarGraph {

    int[] firstData={90};
    int[] secondData={-1 , 85};
    int[] thirdData={-1 , -1 , 50};

    private String[] mMonth = new String[] {
            "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
            "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
    };

    private String[] xlable = new String[] {
            "Your Score", "Highest Score" , "Average Score", "Lowest Score"
    };


//    public Intent getIntent(Context context){
//        int y[] = {25,10,15,20};
//        CategorySeries series = new CategorySeries("Bar1");
//        for(int i=0; i < y.length; i++){
//            series.add("Bar"+(i+1),y[i]);
//        }
//        XYMultipleSeriesDataset dataSet = new XYMultipleSeriesDataset();  // collection of series under one object.,there could any
//        dataSet.addSeries(series.toXYSeries());                            // number of series
//        //customization of the chart
//        XYSeriesRenderer renderer = new XYSeriesRenderer();     // one renderer for one series
//        renderer.setColor(Color.RED);
//        renderer.setDisplayChartValues(true);
//        renderer.setChartValuesSpacing((float) 5.5d);
//        renderer.setLineWidth((float) 10.5d);
//
//        XYSeriesRenderer renderer1 = new XYSeriesRenderer();     // one renderer for one series
//        renderer.setColor(Color.YELLOW);
//        renderer.setDisplayChartValues(true);
//        renderer.setChartValuesSpacing((float) 5.5d);
//        renderer.setLineWidth((float) 10.5d);
//        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();   // collection multiple values for one renderer or series
//        mRenderer.addSeriesRenderer(renderer);
//        mRenderer.addSeriesRenderer(renderer1);
//        mRenderer.setChartTitle("Demo Graph");
////      mRenderer.setXTitle("xValues");
//        mRenderer.setYTitle("Rupee");
//        mRenderer.setZoomButtonsVisible(true);    mRenderer.setShowLegend(true);
//        mRenderer.setShowGridX(true);      // this will show the grid in  graph
//        mRenderer.setShowGridY(true);
////      mRenderer.setAntialiasing(true);
//        mRenderer.setBarSpacing(.5);   // adding spacing between the line or stacks
//        mRenderer.setApplyBackgroundColor(true);
//        mRenderer.setBackgroundColor(Color.BLACK);
//        mRenderer.setXAxisMin(0);
////      mRenderer.setYAxisMin(.5);
//        mRenderer.setXAxisMax(5);
//        mRenderer.setYAxisMax(50);
////
//        mRenderer.setXLabels(0);
//        mRenderer.addXTextLabel(1,"Income");
//        mRenderer.addXTextLabel(2,"Saving");
//        mRenderer.addXTextLabel(3,"Expenditure");
//        mRenderer.addXTextLabel(4,"NetIncome");
//        mRenderer.setPanEnabled(true, true);    // will fix the chart position
//
//
//
//        Intent intent = ChartFactory.getBarChartIntent(context, dataSet, mRenderer,Type.DEFAULT);
//        return intent;
//    }

    public Intent getIntent(Context context ){
        XYMultipleSeriesRenderer barChartRenderer = getBarChartRenderer();
        barChartRenderer.setBackgroundColor(Color.WHITE);
        barChartRenderer.setApplyBackgroundColor(true);
        setBarChartSettings(barChartRenderer);
        Intent intent = ChartFactory.getBarChartIntent(context, getBarDemoDataset(), barChartRenderer, Type.DEFAULT);
        return intent;
    }

    private XYMultipleSeriesDataset getBarDemoDataset() {
        int[] x = { 0,1,2,3,4,5,6,7 };
        int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800,3800};
        int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };

        // Creating an  XYSeries for Income
        XYSeries incomeSeries = new XYSeries("Income");
        // Creating an  XYSeries for Expense
        XYSeries expenseSeries = new XYSeries("Expense");
        // Adding data to Income and Expense Series
        for(int i=0;i<x.length;i++){
            incomeSeries.add(i,income[i]);
            expenseSeries.add(i,expense[i]);
        }

        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        dataset.addSeries(incomeSeries);
        // Adding Expense Series to dataset
        dataset.addSeries(expenseSeries);

        // Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
        incomeRenderer.setColor(Color.rgb(130, 130, 230));
        incomeRenderer.setFillPoints(true);
        incomeRenderer.setLineWidth(2);
        incomeRenderer.setDisplayChartValues(true);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        expenseRenderer.setColor(Color.rgb(220, 80, 80));
        expenseRenderer.setFillPoints(true);
        expenseRenderer.setLineWidth(2);
        expenseRenderer.setDisplayChartValues(true);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Income vs Expense Chart");
        multiRenderer.setXTitle("Year 2012");
        multiRenderer.setYTitle("Amount in Dollars");
        multiRenderer.setZoomButtonsVisible(true);
        for(int i=0; i< x.length;i++){
            multiRenderer.addXTextLabel(i, mMonth[i]);
        }

        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(incomeRenderer);
        multiRenderer.addSeriesRenderer(expenseRenderer);
        return dataset;
    }

    public XYMultipleSeriesRenderer getBarChartRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(20);
        renderer.setChartTitleTextSize(18);
        renderer.setLabelsTextSize(18);
        renderer.setLegendTextSize(18);
        renderer.setBackgroundColor(Color.WHITE);
        renderer.setMargins(new int[] {10, 10, 10, 0});

        for(int i=0; i< 3;i++){
            renderer.addXTextLabel(i, xlable[i]);
        }

        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(Color.BLUE);
        renderer.addSeriesRenderer(r);

        r = new SimpleSeriesRenderer();
        r.setColor(Color.GREEN);
        renderer.addSeriesRenderer(r);

        r = new SimpleSeriesRenderer();
        r.setColor(Color.RED);
        renderer.addSeriesRenderer(r);
        return renderer;
    }
    private void setBarChartSettings(XYMultipleSeriesRenderer renderer) {
        renderer.setChartTitle("Growth comparison company1 vs company2");
        renderer.setXTitle("No of Years in industry");
        renderer.setYTitle("Profit in millions");
        renderer.setXAxisMin(1);
        renderer.setXAxisMax(4);
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(100);
    }
}
