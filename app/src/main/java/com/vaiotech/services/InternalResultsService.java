package com.vaiotech.services;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

/**
 * Created by kanaiyalalt on 29/10/2014.
 */
public class InternalResultsService  extends RetrofitSpiceRequest<List , RestServiceInterface> {

    String schoolID;
    String studentID;
    String section;
    String className;
    String term;

    public InternalResultsService(String studentID ,  String schoolID
            ,  String className , String section , String term ) {
        super(List.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.studentID = studentID;
        this.section = section;
        this.className = className;
        this.term = term;
    }

    @Override
    public List loadDataFromNetwork() throws Exception {
        System.out.println(studentID + schoolID + className + section + term );
        return (List) getService().getStudentTermResult(studentID, schoolID, className, section,term);
    }
}
