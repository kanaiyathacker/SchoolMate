package com.vaiotech.services;

import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

/**
 * Created by kanaiyathacker on 01/11/2014.
 */
public class AttendanceService extends RetrofitSpiceRequest<List , RestServiceInterface> {

    private String schoolID;
    private String studentId;
    private String section;
    private String classname;

    public AttendanceService(String studentId , String schoolID ,String section , String classname) {
        super(List.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.studentId = studentId;
        this.section = section;
        this.classname = classname;
    }

    @Override
    public List loadDataFromNetwork() throws java.lang.Exception{
        System.out.println(studentId + schoolID  + section + classname);
        List retVal = (List) getService().getStudentAttendance(studentId, schoolID, section, classname);
        return retVal;
    }
}
