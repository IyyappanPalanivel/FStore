package com.tt.fstore.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("parameters")
    private Parameters parameters;

    public void setSuccess(boolean success){
        this.success = success;
    }

    public boolean isSuccess(){
        return success;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setParameters(Parameters parameters){
        this.parameters = parameters;
    }

    public Parameters getParameters(){
        return parameters;
    }

}
