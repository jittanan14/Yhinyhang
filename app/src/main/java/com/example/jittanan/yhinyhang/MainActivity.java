package com.example.jittanan.yhinyhang;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.jittanan.yhinyhang.api.RetrofitClient;


public class MainActivity extends AppCompatActivity {

    RetrofitClient retro;
    EditText text_email ;
    EditText pass_word ;

    SharedPreferences sp;
    SharedPreferences.Editor edit;
    String PREF_NAME="Log in";

    Button logout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        logout = findViewById(R.id.button_logout);

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        edit = sp.edit();


        if(!sp.getBoolean("check_login", false)){
            startActivity(new Intent(MainActivity.this, LogIn.class));

        }
        else {
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit.clear();
                    edit.commit();
                    startActivity(new Intent(MainActivity.this,LogIn.class));
                }
            });

        }

    }

}



