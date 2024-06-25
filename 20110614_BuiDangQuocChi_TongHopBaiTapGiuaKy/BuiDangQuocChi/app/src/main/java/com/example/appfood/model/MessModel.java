package com.example.appfood.model;

import java.util.List;

public class MessModel {
    private String success;
    private String message;
    private List<ProductDetailModel> result;

    public MessModel(String success, String message, List<ProductDetailModel> result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductDetailModel> getResult() {
        return result;
    }

    public void setResult(List<ProductDetailModel> result) {
        this.result = result;
    }
}
