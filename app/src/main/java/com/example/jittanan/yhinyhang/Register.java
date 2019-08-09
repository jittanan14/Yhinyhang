package com.example.jittanan.yhinyhang;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jittanan.yhinyhang.api.RetrofitClient;

import java.util.Calendar;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    LinearLayout date_birth;
    TextView text_view_birth;
    TextView element_name;
    TextView yhinyhang;
    ImageView profile;
    Button back_login;
    TextInputLayout Text_Email;
    TextInputLayout Pass_word;
    TextInputLayout confirm_pass;
    EditText name_edit;
    RadioButton radio_men_button;
    RadioButton radio_women_button;
    TextView text_birth;
    TextView text_element;
    TextView text_body;
    EditText food_edit;
    CircleImageView pic_profile;
    Button okay;
    RetrofitClient retro;

    String link_image;
    private final int SELECT_IMAGE = 33;
    String TAG = "register";

    private static final Pattern PASSWORD_PATTERN
            = Pattern.compile("^" +
            "(?=\\S+$)" +           //no white spaces
            ".{6,}" +               //at least 6 characters
            "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();


        retro = new RetrofitClient();

        date_birth = findViewById(R.id.date_birth);
        text_view_birth = findViewById(R.id.text_date);
        element_name = findViewById(R.id.element);
        yhinyhang = findViewById(R.id.body);
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
        text_body = findViewById(R.id.body);
        food_edit = findViewById(R.id.food_lose);
        pic_profile = findViewById(R.id.user);

        //Add data_member to database server
        okay = findViewById(R.id.ok);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Text_Email.getEditText().getText().toString();
                String pass_word = Pass_word.getEditText().getText().toString();
                String username = name_edit.getText().toString();
                String gender = CheckGender();
                String birthday = text_birth.getText().toString();
                String element = text_element.getText().toString();
                String body = text_body.getText().toString();
                String food = food_edit.getText().toString();
                String picture = link_image;

//                Intent openlogin = new Intent(Register.this, Question.class);
//                startActivity(openlogin);
//                finish();

                Call<DefaultResponse> call = retro.getApi().createUser(email, pass_word, username,gender,birthday,element,body,food, picture);
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(response.body().isStatus()) {
//
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });

            }
        });

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

        //Select photo Fragment_profile
        ImageView upload = findViewById(R.id.user);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
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
        month = month + 1;
        int y1=0;
        String m1;
        String date;

        if (String.valueOf(month).length() == 1) {
            date = (String.valueOf(dayOfMonth) + " " + "0" + String.valueOf(month) + " " + String.valueOf(year + 543));
            m1 = "0" + String.valueOf(month);
        } else {
            date = (String.valueOf(dayOfMonth) + " " + String.valueOf(month) + " " + String.valueOf(year + 543));
            m1 = String.valueOf(month);
        }
        y1 = (year+543);
        text_view_birth.setText(date);

        Element_Cal(y1);
    }

    public void Element_Cal(int y1) {

        int element =0;
        int element_full = y1;
        int element_sum = element_full%10;

        int element_two = y1 % 100;

        if(element_sum == 6) {
            element = 6;
        }

        else if(element_sum < 6){
            if ((element_full%100) <= 5) {
                element_sum = (element_sum+10) - 6;
                element = element_sum;
                if (element > 6) {
                    element= element-6 ;
                }

            }
            else if ((element_full%100) >= 10 && (element_full%100) <= 99) {


                    while (element_two > 6) {
                        element_two -= 6;
                        element = element_two;
                    }
            }
        }
        else if (element_sum >6 ){
            element = element_sum - 6;
            Log.e("number ",Integer.toString(element));
        }


        if (element == 1 ) {
            element_name.setText("ธาตุไม้");
        } else if (element == 2 ) {
            element_name.setText("ธาตุดิน");
        } else if (element == 3) {
            element_name.setText("ธาตุไฟ");
        } else if (element == 4) {
            element_name.setText("ธาตุน้ำ");
        }else if (element == 5) {
            element_name.setText("ธาตุดิน");
        }else if (element == 6) {
            element_name.setText("ธาตุโลหะ(ทอง)");
        }

        yhinORyhang(element);

    }

    public void yhinORyhang(int element) {
        if (element == 1 || element == 9){
                yhinyhang.setText("หยิน");
        }else if (element == 2 || element == 8) {
            yhinyhang.setText("หยาง");
        }else if (element == 3) {
            yhinyhang.setText("หยิน");
        }else if (element == 4) {
            yhinyhang.setText("หยิน");
        }else if (element == 5) {
            yhinyhang.setText("หยาง");
        }else if (element == 6 || element == 7 ) {
            yhinyhang.setText("หยาง");
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {

                case SELECT_IMAGE:
                    if (resultCode == Activity.RESULT_OK) {

                        Uri selectedImage = data.getData();
                        profile.setImageURI(selectedImage);
                         link_image = selectedImage.toString();


                        Toast.makeText(this, selectedImage.toString(), Toast.LENGTH_SHORT).show();

//                        Bundle bundle = data.getExtras();
//                        Bitmap bitmap = bundle.getParcelable("data");
////                        image_user = imageToString(bitmap);
//                        Fragment_profile.setImageBitmap(bitmap);

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

    public String CheckGender() {

        if (radio_men_button.isChecked()) {
            return "m";
        } else {
            return "f";
        }
    }


}
