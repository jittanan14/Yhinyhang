package com.example.jittanan.yhinyhang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jittanan.yhinyhang.api.RetrofitClient;
import com.example.jittanan.yhinyhang.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAvtivity extends AppCompatActivity {

    RetrofitClient retro;
    EditText text_email;
    EditText pass_word;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    String PREF_NAME = "Log in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getSupportActionBar().hide();

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        edit = sp.edit();

        retro = new RetrofitClient();

        text_email = findViewById(R.id.TextEmail_login);
        pass_word = findViewById(R.id.TextPassword_login);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        TextView signIn = findViewById(R.id.signin);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        TextView signUp = findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAvtivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    private void userLogin() {

        final String email = text_email.getText().toString().trim();
        final String password = pass_word.getText().toString().trim();

        if (email.isEmpty()) {
            text_email.setError("กรุณากรอกอีเมล");
            pass_word.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            pass_word.setError("กรุณากรอกรหัสผ่าน");
            pass_word.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            text_email.setError("รูปแบบอีเมลไม่ถูกต้อง");
            text_email.requestFocus();
            return;
        }

        if (password.length() < 6) {
            pass_word.setError("รหัสผ่านต้องมีความยาวอย่างน้อย 6 ตัว");
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

//                    Toast.makeText(LoginAvtivity.this, response.body().getMessages(), Toast.LENGTH_LONG).show();

                    String email      = response.body().getUser().getEmail();
                    String username   = response.body().getUser().getUsername();
                    String gender     = response.body().getUser().getGender();
                    String birthday   = response.body().getUser().getBirthday();
                    String element    = response.body().getUser().getElement();
                    String foodLose   = response.body().getUser().getFood();
                    String image      = response.body().getUser().getImage_user();
                    String body       = response.body().getUser().getBody();
                    String numYhin    = response.body().getUser().getNum_yhin();
                    String numYhang   = response.body().getUser().getNum_yhang();

                    Log.e("User", username);

                    edit.putString("email", email);
                    edit.putString("username", username);
                    edit.putString("gender", gender);
                    edit.putString("birthday", birthday);
                    edit.putString("element", element);
                    edit.putString("foodLose", foodLose);
                    edit.putString("image", image);
                    edit.putString("body", body);
                    edit.putString("numYhin", numYhin);
                    edit.putString("numYhang", numYhang);

                    edit.putBoolean("SIGNIN", true);
                    edit.commit();

                    Intent intent = new Intent(LoginAvtivity.this, Question.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginAvtivity.this, response.body().getMessages(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginAvtivity.this, "ไม่ได้เชื่อมต่ออินเทอร์เน็ต", Toast.LENGTH_LONG).show();
            }
        });


    }


}



