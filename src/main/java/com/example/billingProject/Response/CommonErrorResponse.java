package com.example.billingProject.Response;

import java.util.HashMap;

public class CommonErrorResponse {
    private HashMap<String, String> headers = new HashMap<>();
    private HashMap<String, String> errorlist = new HashMap<>();

    public CommonErrorResponse(String status, String statusCode, String message) {
        this.headers.put("status", status);
        this.headers.put("statusCode", statusCode);
        this.headers.put("message", message);
    }

    public HashMap<String,String> getHeaders(){
        return headers;
    }

    public void setHeaders(HashMap<String,String> headers) {
        this.headers = headers;
    }

    public HashMap<String, String> getErrorlist() {
        return errorlist;
    }

    public void setErrorlist(HashMap<String, String> errorlist) {
        this.errorlist = errorlist;
    }
}
