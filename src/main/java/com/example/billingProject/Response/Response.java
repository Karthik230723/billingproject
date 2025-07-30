package com.example.billingProject.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private Map<String, String> headers = new HashMap<>();

    private List<?> data;
    private Object record;

    public Response(String status, String statusCode, String message) {
        this.headers.put("status", status);
        this.headers.put("statusCode", statusCode);
        this.headers.put("message", message);
    }

    public Map<String, String> getHeaders() {

        return headers;
    }

    public void setHeaders(Map<String, String> headers) {

        this.headers = headers;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Object getRecord() {
        return record;
    }

    public void setRecord(Object data) {
        this.record = data;
    }
}
