package com.vaiotech.services;

import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanaiyalalt on 08/10/2014.
 */
public class AdmissionService extends RetrofitSpiceRequest<String , RestServiceInterface> {

    private String schoolID;
    private String modelID;

    public AdmissionService(String schoolID , String modelID) {
        super(String.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.modelID = modelID;
    }

    @Override
    public String loadDataFromNetwork() throws java.lang.Exception{
        List<String> retVal = new ArrayList();
        List<LinkedTreeMap> list = (List<LinkedTreeMap>) getService().getModelDesc(schoolID , modelID);
        if(list != null && !list.isEmpty()) {
            for(LinkedTreeMap currVal : list) {
                retVal.add(currVal.get("cityID") + " - " + currVal.get("cityName"));
            }
        }
        return "hello";
    }
}
