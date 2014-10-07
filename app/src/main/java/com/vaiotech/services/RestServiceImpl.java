package com.vaiotech.services;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import org.json.JSONObject;

import retrofit.http.Path;
import roboguice.util.temp.Ln;

/**
 * Created by kanaiyalalt on 02/10/2014.
 */
public class RestServiceImpl extends RetrofitSpiceRequest<String , RestServiceInterface>{

    private String schoolID;
    private String modelID;

    public RestServiceImpl(String schoolID , String modelID) {
        super(String.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.modelID = modelID;
    }

    @Override
    public String loadDataFromNetwork() throws java.lang.Exception{
        System.out.println("loadDataFromNetwork......................." + getService().getDescription());
        Gson gson = new Gson();
//        Exception exp = gson.fromJson(""+getService().getDescription() , Exception.class);
//        System.out.println("Exceptino ...." + exp);
        com.google.gson.internal.LinkedTreeMap map = (LinkedTreeMap)getService().getDescription();
        System.out.println("getService().getDescription(" + map.get("status").getClass());
        System.out.println("getService().getDescription(" + map.get("message"));
        System.out.println("getService().getDescription(" + map.get("status.message"));
         return "Kanaiya,....";
    }

    public class Exception {
        private Status status;
        public Status getStatus(){
            return this.status;
        }
        public void setStatus(Status status){
            this.status = status;
        }
    }

    public class Status {
        private String message;
        private Number value;

        public String getMessage(){
            return this.message;
        }
        public void setMessage(String message){
            this.message = message;
        }
        public Number getValue(){
            return this.value;
        }
        public void setValue(Number value){
            this.value = value;
        }
    }
}
