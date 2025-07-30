package com.example.billingProject.Exception;

import com.example.billingProject.Constants.ConstantMessages;
import com.example.billingProject.Response.CommonErrorResponse;
import com.example.billingProject.Constants.StatusCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalException{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex){
        HashMap<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(),error.getDefaultMessage())
        );
        CommonErrorResponse response = new CommonErrorResponse(ConstantMessages.ERROR,StatusCode.STATUSBAD , "Enter Valid Params");
        response.setErrorlist(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleUserNotFoundException(Exception ex) {
        ex.printStackTrace();
        CommonErrorResponse response = new CommonErrorResponse(ConstantMessages.ERROR, StatusCode.STATUSBAD, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<Object> handleNoSuchElementException(Exception ex) {
//        ex.printStackTrace();
//        CommonErrorResponse response = new CommonErrorResponse(ConstantMessages.ERROR, StatusCode.STATUSBAD, ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

