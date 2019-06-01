package com.example.jittanan.yhinyhang;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;


public class Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    LinearLayout date_birth;
    TextView text_view_birth;
    TextView element_name;
    ImageView profile;
    Button back_login;
   private TextInputLayout Text_Email;
   private TextInputLayout Pass_word ;
   private TextInputLayout confirm_pass;
    EditText name_edit;
    RadioButton radio_men_button ;
    RadioButton radio_women_button;
    TextView text_birth;
    TextView text_element;
    EditText food_edit;
    CircleImageView pic_profile;
    Button okay;


    private final int REQUEST_CODE=33;
    String TAG="register";

    private static final Pattern PASSWORD_PATTERN
            = Pattern.compile("^" +
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);


        date_birth = findViewById(R.id.date_birth);
        text_view_birth = findViewById(R.id.text_date);
        element_name = findViewById(R.id.element);
        profile = findViewById(R.id.user);
        back_login = findViewById(R.id.button_back_login);

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // user
        Text_Email = findViewById(R.id.TextEmail);
        Pass_word = findViewById(R.id.Password);
        confirm_pass = findViewById(R.id.confirm_pass);
        name_edit = findViewById(R.id.name);
        radio_men_button = findViewById(R.id.radio_men);
        radio_women_button = findViewById(R.id.radio_women);
        text_birth = findViewById(R.id.text_date);
        text_element = findViewById(R.id.element);
        food_edit = findViewById(R.id.food_lose);
        pic_profile = findViewById(R.id.user);

        okay = findViewById(R.id.ok);

        //Email
        Text_Email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override

            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    Text_Email.setError("กรุณากรอกอีเมล");
                    Text_Email.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    Text_Email.setError("รูปแบบอีเมลไม่ถูกต้อง");
                    Text_Email.requestFocus();
                } else {
                    Text_Email.setError(null);
                }

            }
        });

        //Password
        Pass_word.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    Pass_word.setError("กรุณากรอกรหัสผ่าน");
                    Pass_word.requestFocus();
                } else if (!PASSWORD_PATTERN.matcher(s.toString()).matches()) {
                    Pass_word.setError("รหัสผ่านมีความยาวน้อยกว่า 6 ตัวอักษร");
                    Pass_word.requestFocus();
                } else {
                    Pass_word.setError(null);
                }

            }
        });


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

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

        //calendar birthday
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

                        Uri selectedImage = data.getData();
                        profile.setImageURI(selectedImage);
                        Toast.makeText(this, "เปลี่ยนรูปภาพแล้ว", Toast.LENGTH_SHORT).show();

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


    private boolean validateEmail() {
        String emailInput = Text_Email.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            return false;
        } else {
            return true;
        }
    }





}
