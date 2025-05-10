package com.example.inovaTest.enums;

public enum EvaluationStatusEnum {
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELED("CANCELED");
    private String status;
    EvaluationStatusEnum(String status){
        this.status = status;
    }   
    public String getStatus(){
        return status;
    }


    
}
