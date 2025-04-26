package com.example.inovaTest.enums;

public enum QuestionTypeEnum {
    MULTIPLE_CHOICE("multiple_choice"),
    LONG_ANSWER("long_answer");

    private String type;

    QuestionTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    
}
