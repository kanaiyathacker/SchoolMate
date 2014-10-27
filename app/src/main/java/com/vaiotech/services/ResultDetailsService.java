package com.vaiotech.services;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

/**
 * Created by kanaiyathacker on 27/10/2014.
 */
public class ResultDetailsService extends RetrofitSpiceRequest<List , RestServiceInterface> {

    String schoolID;
    String studentID;
    String section;
    String className;
    String term;

    public ResultDetailsService(String studentID ,  String schoolID
            ,  String className , String section , String term) {
        super(List.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.studentID = studentID;
        this.section = section;
        this.className = className;
        this.term = term;
    }

    @Override
    public List loadDataFromNetwork() throws Exception {
        System.out.println(studentID + schoolID + className + section);
        return (List) getService().getStudentTermResult(studentID, schoolID, className, section,term);
    }
}
