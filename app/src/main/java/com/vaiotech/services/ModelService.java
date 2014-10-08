package com.vaiotech.services;

import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by kanaiyathacker on 08/10/2014.
 */
public class ModelService extends RetrofitSpiceRequest<String , RestServiceInterface> {

    private String schoolID;
    private String modelID;

    public ModelService(String schoolID , String modelID) {
        super(String.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.modelID = modelID;
    }

    @Override
    public String loadDataFromNetwork() throws java.lang.Exception{
        String retVal = null;
        LinkedTreeMap map = (LinkedTreeMap) getService().getModelDesc(schoolID , modelID);
        if(map != null && !map.isEmpty()) {
            System.out.println("map..." + map);
            retVal = map.get("description").toString();
        }
        return retVal;
    }
}
