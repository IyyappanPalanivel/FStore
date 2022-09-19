package com.tt.fstore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tt.fstore.MainActivity;
import com.tt.fstore.api.APIClient;
import com.tt.fstore.databinding.ActivityLoginBinding;
import com.tt.fstore.model.LoginResponse;
import com.tt.fstore.utils.Constant;
import com.tt.fstore.utils.LocalSession;
import com.tt.fstore.utils.MyFunctions;

import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createAccount.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class))
        );

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()){
                    login();
                }
            }
        });
    }

    private void login() {

        MyFunctions.showLoader(LoginActivity.this);

        HashMap<String,Object> map = new HashMap<>();
        map.put(Constant.PRIMARY_EMAIL,MyFunctions.getText(binding.emailEt));
        map.put(Constant.PASSWORD_HASH,MyFunctions.getText(binding.passwordEt));
        map.put(Constant.AUTH_TOKEN, MyFunctions.md5(Constant.SALT+MyFunctions.getText(binding.emailEt)+MyFunctions.getText(binding.passwordEt)));

        Call<LoginResponse> call = APIClient.getClient().login(map);
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
        if (MyFunctions.isEmpty(binding.emailEt)) return false;
        if (MyFunctions.isEmpty(binding.passwordEt)) return false;
        if (!MyFunctions.isValidEmail(binding.emailEt)) return false;
        if (!MyFunctions.isValidPassword(binding.passwordEt)) return false;
        return true;
    }
}