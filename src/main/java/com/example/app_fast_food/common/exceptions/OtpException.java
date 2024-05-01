package com.example.app_fast_food.common.exceptions;

import java.time.LocalDateTime;

public class OtpException extends RuntimeException{

    public OtpException(String message){
            super(message);
    }

    public static class OtpEarlyResentException extends OtpException{
           private static final String MESSAGE = "Please try after 0:%d";

           public OtpEarlyResentException(long resentTime){
                  super(String.format(MESSAGE, resentTime));
           }
    }

    public static class OtpLimitExitedException extends OtpException{
           private static final String MESSAGE = "Otp limit is reached: %s Please try  after %s";

           public OtpLimitExitedException(int count , LocalDateTime  reTryTime){
               super(String.format(MESSAGE, count, reTryTime));
           }
    }

    public static class PhoneNumberNotVerified extends OtpException {
        static private final String MESSAGE = "Phone number: %s is not verified";

        public PhoneNumberNotVerified(String phoneNumber) {
            super(String.format(MESSAGE, phoneNumber));
        }
    }
}
