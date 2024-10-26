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

    public static class FileSizeLimitExceedException extends RestException{
        private static final String MESSAGE = "%s file size exceed limit : Limit is %d ";
        public FileSizeLimitExceedException(String fileName , Long limitSize ){
            super(String.format(MESSAGE, fileName , limitSize ));
        }
    }

    public static class FileNotFound extends RestException{
        private static final String MESSAGE = "%s file not found in path %s";
        public FileNotFound(String fileName , String path ){
            super(String.format(MESSAGE, fileName , path ));
        }
    }

    public static class EntityNotFoundException extends RestException{
        private static final String MESSAGE = "%s entity not found with id %s";
        public EntityNotFoundException(String entityName , String id ){
            super(String.format(MESSAGE, entityName , id ));
        }
    }


    public static class InvalidOperationException extends RestException {
        public InvalidOperationException(String s) {
           super(s);
        }
    }

    public static class TooFarException extends RestException {
        public TooFarException(String s) {
            super(s);
        }
    }
}
