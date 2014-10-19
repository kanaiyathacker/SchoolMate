package com.vaiotech.services;

import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanaiyalalt on 13/10/2014.
 */
public class WeeklyTimeTableService extends RetrofitSpiceRequest<List , RestServiceInterface> {

    String schoolID;
    String className;
    String section;

    public WeeklyTimeTableService(String schoolID , String className , String section) {
        super(List.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.className = className;
        this.section = section;
    }

    @Override
    public List loadDataFromNetwork() throws Exception {
        System.out.println(schoolID);
        System.out.println(className);
        System.out.println(section);
        List<String> retVal = new ArrayList();
        List<LinkedTreeMap> list = (List<LinkedTreeMap>) getService().getWeeklyTimeTable(schoolID,className,section);
        System.out.println("..... " + list);
        return list;

    }
}
