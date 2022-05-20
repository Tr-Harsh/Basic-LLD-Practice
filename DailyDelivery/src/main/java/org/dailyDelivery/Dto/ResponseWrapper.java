package org.dailyDelivery.Dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseWrapper {
    private String code;
    private String message;

    public ResponseWrapper(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseWrapper() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
