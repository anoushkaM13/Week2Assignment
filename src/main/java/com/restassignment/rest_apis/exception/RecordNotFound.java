package com.restassignment.rest_apis.exception;

public class RecordNotFound extends RuntimeException{
    public RecordNotFound(String msg){
        super(msg);
    }
};
