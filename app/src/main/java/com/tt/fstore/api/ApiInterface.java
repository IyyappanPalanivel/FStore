package com.tt.fstore.api;

import com.tt.fstore.model.CustomerResponse;
import com.tt.fstore.model.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("users/customer-manual-login")
    Call<LoginResponse> login(@Body HashMap<String,Object> hashMap);

    @POST("users/customer-register")
    Call<LoginResponse> register(@Body HashMap<String,Object> hashMap);

    @POST("users/all-customers")
    Call<CustomerResponse> getAllCustomers(@Header("Authorization") String token,@Body HashMap<String,Object> hashMap);

}
