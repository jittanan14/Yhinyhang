package com.example.jittanan.yhinyhang;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.jittanan.yhinyhang.Fragments.Fragment_foodcomment;
import com.example.jittanan.yhinyhang.Fragments.Fragment_graph;
import com.example.jittanan.yhinyhang.Fragments.Fragment_profile;
import com.example.jittanan.yhinyhang.Fragments.Fragment_search;
import com.example.jittanan.yhinyhang.api.RetrofitClient;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    //set message
                    builder.setMessage("คุณต้องการออกจากระบบหรือไม่");
                    //set cancelable
                    builder.setCancelable(true);
                    //set positive / yes button
                    builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                            edit.clear();
                            edit.commit();
                            startActivity(new Intent(MainActivity.this,LogIn.class));
                        }
                    });

                    //set negative / no button
                    builder.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                        //create alert dialog
                    AlertDialog alertdialog = builder.create();
                        //show alert dialog
                    alertdialog.show();


                }
            });

            loadFragment(new Fragment_foodcomment());

            BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
            navigation.setOnNavigationItemSelectedListener(this);

        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.food_comment : loadFragment(new Fragment_foodcomment());
                return true;
            case R.id.search : loadFragment(new Fragment_search());
                return true;
            case R.id.graph : loadFragment(new Fragment_graph());
                return true;
            case R.id.profile : loadFragment(new Fragment_profile());
                return true;
        }

        return false;
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}



