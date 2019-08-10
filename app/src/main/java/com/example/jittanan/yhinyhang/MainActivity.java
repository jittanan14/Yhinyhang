package com.example.jittanan.yhinyhang;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;
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
    TextView text_create;
    ActionBar toolbar;
    Button logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        toolbar = getSupportActionBar();

        logout = findViewById(R.id.button_logout);
        text_create = findViewById(R.id.text_create);
        logout = findViewById(R.id.button_logout);
        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        edit = sp.edit();

        logout.setVisibility(View.GONE);

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
            case R.id.food_comment :
                text_create.setText("รายการอาหารที่แนะนำ");
                loadFragment(new Fragment_foodcomment());
                logout.setVisibility(View.GONE);
                return true;
            case R.id.search :
                text_create.setText("ค้นหาเมนูอาหาร");
                loadFragment(new Fragment_search());
                logout.setVisibility(View.GONE);
                return true;
            case R.id.graph :
                text_create.setText("ประวัติการรับประทานอาหาร");
                loadFragment(new Fragment_graph());
                logout.setVisibility(View.GONE);
                return true;
            case R.id.profile :
                text_create.setText("โปรไฟล์ของฉัน");
                loadFragment(new Fragment_profile());
                logout.setVisibility(View.VISIBLE);
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



