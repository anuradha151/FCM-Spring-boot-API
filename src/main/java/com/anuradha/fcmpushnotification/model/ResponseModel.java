package com.anuradha.fcmpushnotification.model;

public class ResponseModel {

    private boolean success;
    private String message;
    private int status;

    public ResponseModel(int status, String message, boolean success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
