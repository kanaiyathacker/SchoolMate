package com.vaiotech.services;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

/**
 * Created by kanaiyathacker on 26/10/2014.
 */
public class ResultsService extends RetrofitSpiceRequest<List , RestServiceInterface> {

    String schoolID;
    String studentID;
    String section;
    String className;

    public ResultsService(String studentID ,  String schoolID
            ,  String className , String section) {
        super(List.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.studentID = studentID;
        this.section = section;
        this.className = className;
    }

    @Override
    public List loadDataFromNetwork() throws Exception {
        System.out.println(studentID + schoolID + className + section);
        return (List) getService().getStudentResults(studentID, schoolID, className, section);
    }
}
