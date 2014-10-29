package com.vaiotech.services;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

/**
 * Created by kanaiyathacker on 28/10/2014.
 */
public class ResultSubjectService  extends RetrofitSpiceRequest<String , RestServiceInterface> {

    String schoolID;
    String studentID;
    String section;
    String className;
    String term;
    String sub;

    public ResultSubjectService(String studentID ,  String schoolID
            ,  String className , String section , String term , String sub) {
        super(String.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.studentID = studentID;
        this.section = section;
        this.className = className;
        this.term = term;
        this.sub = sub;
    }

    @Override
    public String loadDataFromNetwork() throws Exception {
        System.out.println(studentID + schoolID + className + section + term + sub);
        return (String)getService().getStudentScoreResult(studentID, schoolID, className, section,term , sub);
    }
}
