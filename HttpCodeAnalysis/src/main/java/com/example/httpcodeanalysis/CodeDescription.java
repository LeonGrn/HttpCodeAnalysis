package com.example.httpcodeanalysis;

public class CodeDescription {
    private String code;
    private String description;

    public CodeDescription(){}

    public CodeDescription(String code , String description){
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
