package com.example.jittanan.yhinyhang;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
                    Toast.makeText(LogIn.this, response.body().getMessages(), Toast.LENGTH_LONG).show();
                 
                      startActivity(new Intent(LogIn.this, Question.class));




                    edit.putBoolean("check_login",true);
                    edit.commit();



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



