package com.tt.fstore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tt.fstore.MainActivity;
import com.tt.fstore.api.APIClient;
import com.tt.fstore.databinding.ActivityRegisterBinding;
import com.tt.fstore.model.LoginResponse;
import com.tt.fstore.utils.Constant;
import com.tt.fstore.utils.LocalSession;
import com.tt.fstore.utils.MyFunctions;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()){
                    register();
                }
            }
        });

    }

    private void register() {
        MyFunctions.showLoader(RegisterActivity.this);

        HashMap<String, Object> map = new HashMap<>();
        map.put(Constant.PRIMARY_EMAIL, MyFunctions.getText(binding.emailEt));
        map.put(Constant.PRIMARY_PHONE, MyFunctions.getText(binding.phoneEt));
        map.put(Constant.FIRST_NAME, MyFunctions.getText(binding.firstnameEt));
        map.put(Constant.LAST_NAME, MyFunctions.getText(binding.lastnameEt));
        map.put(Constant.PASSWORD_HASH, MyFunctions.getText(binding.passwordEt));
        map.put(Constant.LOGIN_TYPE, "EMAIL");
        map.put(Constant.GENDER, binding.gender.getSelectedItem().toString().toUpperCase());
        map.put(Constant.AUTH_TOKEN, MyFunctions.md5(Constant.SALT + MyFunctions.getText(binding.emailEt) + MyFunctions.getText(binding.phoneEt)));

        Call<LoginResponse> call = APIClient.getClient().register(map);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                MyFunctions.cancelLoader();
                if (response.isSuccessful()){

                    LoginResponse body = response.body();
                    Toast.makeText(getApplicationContext(), body.getMessage(), Toast.LENGTH_SHORT).show();
                    if (body.isSuccess()){
                        MyFunctions.setSharedPrefs(getApplicationContext(),Constant.isLoggedIn,true);
                        LocalSession.login(body.getParameters());
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }else {
                    Toast.makeText(getApplicationContext(), Constant.SERVER_ERROR, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                MyFunctions.cancelLoader();
                Log.d(Constant.API_LOG,t.getMessage());
                Toast.makeText(getApplicationContext(), Constant.SERVER_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValid() {
        if (MyFunctions.isEmpty(binding.firstnameEt)) return false;
        if (MyFunctions.isEmpty(binding.lastnameEt)) return false;
        if (MyFunctions.isEmpty(binding.emailEt)) return false;
        if (MyFunctions.isEmpty(binding.phoneEt)) return false;
        if (MyFunctions.isEmpty(binding.passwordEt)) return false;
        if (!MyFunctions.isValidEmail(binding.emailEt)) return false;
        if (!MyFunctions.isValidPhone(binding.phoneEt)) return false;
        if (!MyFunctions.isValidPassword(binding.passwordEt)) return false;
        if (!MyFunctions.isSamePassword(binding.passwordEt,binding.confirmPasswordEt)) return false;
        return true;
    }

}