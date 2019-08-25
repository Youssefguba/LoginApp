package com.techack.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.techack.loginapp.Retrofit.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText emailField, passwordField;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init Views..
        emailField = findViewById(R.id.email_edit_text);
        passwordField = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestDataFromUrl();
            }
        });
    }

    private void requestDataFromUrl() {
        final String login = emailField.getText().toString();
        final String password = passwordField.getText().toString();

        /*
         * Call Instance of Retrofit and Api to respond for calling of data from cpanel..
         * */
        Call<ResponseBody> responseBodyCall = RetrofitClient
                .getmInstance()
                .getApi()
                .userRegisteration(login, password);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                boolean s = response.isSuccessful();
                String s1;

                try {
                    s1 = response.body().string();
                    Toast.makeText(MainActivity.this, s1 + " Successful Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HelloWorld.class);
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, s + " Successful ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error msg", t);
                Toast.makeText(MainActivity.this, "Failed Login", Toast.LENGTH_SHORT).show();
            }
        });

    }
}