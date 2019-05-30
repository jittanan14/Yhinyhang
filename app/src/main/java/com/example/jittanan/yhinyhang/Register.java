package com.example.jittanan.yhinyhang;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    LinearLayout date_birth;
    TextView text_view_birth;
    TextView element_name;

    private final int REQUEST_CODE=33;

    String TAG="register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        date_birth = findViewById(R.id.date_birth);
        text_view_birth = findViewById(R.id.text_date);
        element_name = findViewById(R.id.element);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //calendar birthday


        //Select photo profile
        ImageView upload = findViewById(R.id.user);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_CODE);

            }
        });

        date_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    public void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month=month+1;
        String m1;
        String date;
        if (String.valueOf(month).length()==1){
            date = (String.valueOf(dayOfMonth) +" "+"0"+ String.valueOf(month) +" "+ String.valueOf(year+543));
            m1 = "0"+ String.valueOf(month);
        }
        else  {
            date = (String.valueOf(dayOfMonth) +" "+ String.valueOf(month)+" " + String.valueOf(year+543));
            m1 = String.valueOf(month);
        }
        text_view_birth.setText(date);

            Element_Cal(m1);
    }

    public  void Element_Cal(String m1){

        if (m1.equals("01")||m1.equals("05")||m1.equals("09")){
            element_name.setText("ธาตุดิน");
        }
        else if (m1.equals("03")||m1.equals("07")||m1.equals("11")){
            element_name.setText("ธาตุน้ำ");
        }
        else if (m1.equals("02")||m1.equals("06")||m1.equals("10")){
            element_name.setText("ธาตุลม");
        }
        else if (m1.equals("04")||m1.equals("08")||m1.equals("12")){
            element_name.setText("ธาตุไฟ");

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {

                case REQUEST_CODE:
                    if (resultCode == Activity.RESULT_OK) {
                        //data gives you the image uri. Try to convert that to bitmap
                        break;
                    } else if (resultCode == Activity.RESULT_CANCELED) {
                        Log.e(TAG, "Selecting picture cancelled");
                    }
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception in onActivityResult : " + e.getMessage());
        }
    }

}
