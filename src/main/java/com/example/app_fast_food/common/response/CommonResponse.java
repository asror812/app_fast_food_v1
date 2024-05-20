package com.example.app_fast_food.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

    private boolean success;
    private T data;
    private String errorMessage;
    private List<FieldErrorDTO> fieldErrors;


    public static<T> CommonResponse<T> succeed(T data) {
        CommonResponse<T> commonResponse = new CommonResponse<>();

        commonResponse.success = true;
        commonResponse.data = data;

        return commonResponse;
    }

    public static<T> CommonResponse<T> fail(String errorMessage){
        CommonResponse<T> commonResponse = new CommonResponse<>();

        commonResponse.success = false;
        commonResponse.errorMessage = errorMessage;

        return commonResponse;
    }

    public static<T> CommonResponse<T> failFieldError(List<FieldErrorDTO> fieldErrorDTOList){

        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.success = false;
        commonResponse.fieldErrors = fieldErrorDTOList;

        return commonResponse;
    }

}
