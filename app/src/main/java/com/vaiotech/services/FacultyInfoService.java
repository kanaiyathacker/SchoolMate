package com.vaiotech.services;

import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by kanaiyalalt on 20/10/2014.
 */
public class FacultyInfoService extends RetrofitSpiceRequest<Map , RestServiceInterface> {

    private String schoolID;
    private String className;
    private String section;


    public FacultyInfoService(String schoolID , String className , String section) {
        super(Map.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.className = className;
        this.section = section;
    }

    @Override
    public Map loadDataFromNetwork() throws java.lang.Exception{
        String retVal = null;
        Map map = (Map) getService().getFacultyInfo(schoolID , className, section);
        return map;
    }
}



