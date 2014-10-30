package com.vaiotech.services;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

/**
 * Created by kanaiyathacker on 30/10/2014.
 */
public class ExamTermService  extends RetrofitSpiceRequest<List , RestServiceInterface> {

    String schoolID;
    String section;
    String className;

    public ExamTermService(String schoolID
            , String className, String section) {
        super(List.class, RestServiceInterface.class);
        this.schoolID = schoolID;
        this.section = section;
        this.className = className;
    }

    @Override
    public List loadDataFromNetwork() throws Exception {
        return (List) getService().getExamTerm(schoolID, className, section);
    }
}
