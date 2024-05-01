package com.example.app_fast_food.common.exceptions;



public class RestException extends RuntimeException{

    public RestException(String message){
        super(message);
    }

    public static class AlreadyExistsException extends RestException{
        private static final String MESSAGE = "%s with %s : %s already exists";

        public AlreadyExistsException(String className , String attribute ,  String id){
            super(String.format(MESSAGE, className , attribute , id));
        }
    }
}
