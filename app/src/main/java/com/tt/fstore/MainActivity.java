package com.tt.fstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.tt.fstore.activity.LoginActivity;
import com.tt.fstore.adapter.CustomerAdapter;
import com.tt.fstore.api.APIClient;
import com.tt.fstore.databinding.ActivityMainBinding;
import com.tt.fstore.model.CustomerResponse;
import com.tt.fstore.model.CustomerResponse;
import com.tt.fstore.utils.Constant;
import com.tt.fstore.utils.LocalSession;
import com.tt.fstore.utils.MyFunctions;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    private long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureToolbar();

        getAllCustomers();

    }

    private void getAllCustomers() {
        MyFunctions.showLoader(MainActivity.this);

        String token = Constant.BEARER + Constant.ADMIN_TOKEN;

        HashMap<String,Object> map = new HashMap<>();
        map.put("auth_token", "e237f60abef8e44f43c71e1faf0692f1");

        Call<CustomerResponse> call = APIClient.getClientAdmin().getAllCustomers(token,map);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                MyFunctions.cancelLoader();
                if (response.isSuccessful()){

                    CustomerResponse body = response.body();
                    if (body.getSuccess()){
                        CustomerAdapter adapter = new CustomerAdapter(MainActivity.this,body.getParameters());
                        binding.contentMain.customerRecycler.setAdapter(adapter);
                    }else {
                        Log.d(Constant.API_LOG,body.getMessage());
                        Toast.makeText(getApplicationContext(), Constant.SERVER_ERROR, Toast.LENGTH_SHORT).show();                    }
                }else {
                    Toast.makeText(getApplicationContext(), Constant.SERVER_ERROR, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                MyFunctions.cancelLoader();
                Log.d(Constant.API_LOG,t.getMessage());
                Toast.makeText(getApplicationContext(), Constant.SERVER_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureToolbar() {
        ActionBarDrawerToggle toggle;

        setSupportActionBar(binding.toolbar);
        binding.navigation.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,  binding.drawerLayout, binding.toolbar ,R.string.nav_open,R.string.nav_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        switch(item.getItemId()) {
            case R.id.logout:
                MyFunctions.logout(MainActivity.this);
                startActivity(new Intent(getApplicationContext(),SplashActivity.class));
                break;
            default:
                Toast.makeText(this, "coming soon...", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            finishAffinity();
            System.exit(0);
        }
        else{
            Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }



}