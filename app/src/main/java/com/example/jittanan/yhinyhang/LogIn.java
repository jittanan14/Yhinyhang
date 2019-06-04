package com.example.jittanan.yhinyhang;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.jittanan.yhinyhang.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {

    RetrofitClient retro;
    EditText text_email ;
    EditText pass_word ;

    SharedPreferences sp;
    SharedPreferences.Editor edit ;
    String PREF_NAME="Log in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        sp = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        edit = sp.edit();

        retro = new RetrofitClient();

        text_email = findViewById(R.id.TextEmail_login);
        pass_word = findViewById(R.id.TextPassword_login);



        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        TextView regis = findViewById(R.id.signup);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);

            }
        });

        TextView sig_in = findViewById(R.id.signin);
        sig_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.signin:
                        userLogin();
                        break;
                    case R.id.signup:
                        startActivity(new Intent(LogIn.this, Register.class));
                        break;
                }
            }
        });


    }
    private void userLogin() {

        final String email = text_email.getText().toString().trim();
        final String password = pass_word.getText().toString().trim();

        if (email.isEmpty()) {
            text_email.setError("Email is required");
            pass_word.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            text_email.setError("Enter a valid email");
            text_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            pass_word.setError("Password required");
            pass_word.requestFocus();
            return;
        }

        if (password.length() < 6) {
            pass_word.setError("Password should be atleast 6 character long");
            pass_word.requestFocus();
            return;
        }

        Call<LoginResponse> call = retro.getApi().userLogin(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().isStatus()) {
                    System.out.println(response.body().isStatus());
                    System.out.println(response.body().getMessages());
                    System.out.println(response.body().getUser().getEmail());
                    Toast.makeText(LogIn.this, response.body().getMessages(), Toast.LENGTH_LONG).show();

                    edit.putBoolean("check_login",true);
                    edit.commit();

                    startActivity(new Intent(LogIn.this, MainActivity.class));


                } else {
                    Toast.makeText(LogIn.this, response.body().getMessages(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LogIn.this, "ไม่ได้เชื่อมต่ออินเทอร์เน็ต", Toast.LENGTH_LONG).show();
            }
        });


    }


}



