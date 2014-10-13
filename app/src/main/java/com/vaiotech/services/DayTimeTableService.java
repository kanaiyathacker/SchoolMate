package com.vaiotech.services;

import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanaiyalalt on 13/10/2014.
 */

public class DayTimeTableService extends RetrofitSpiceRequest<List , RestServiceInterface> {
    String schoolID;
    String className;
    String section;
    String day;

    public DayTimeTableService(String schoolID , String className , String section , String day) {
        super(List.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.className = className;
        this.section = section;
        this.day = day;
    }

    @Override
    public List loadDataFromNetwork() throws Exception {
        List<String> retVal = new ArrayList();
        List<LinkedTreeMap> list = (List<LinkedTreeMap>) getService().getDayTimeTable(schoolID,className,section , day);
        return retVal;
    }
}
