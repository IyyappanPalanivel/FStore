package com.tt.fstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tt.fstore.activity.WelcomeActivity;
import com.tt.fstore.utils.Constant;
import com.tt.fstore.utils.MyFunctions;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MyFunctions.getSharedPrefs(getApplicationContext(), Constant.isLoggedIn,false)) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else {
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                }
                finish();
            }
        },2000);

    }
}