package com.example.jittanan.yhinyhang;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jittanan.yhinyhang.api.RetrofitClient;

import java.io.ByteArrayOutputStream;
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
    EditText food_lose;
    TextInputLayout Text_Email;
    TextInputLayout Pass_word;
    TextInputLayout confirm_pass;
    TextInputLayout name_edit;
    RadioButton radio_men_button;
    RadioButton radio_women_button;
    RadioButton radioButton;
    TextView text_birth;
    TextView text_element;
    TextView text_body;
    RadioGroup radioGroup_gender;
    CircleImageView pic_profile;

    Button okay;
    RetrofitClient retro;
    String string_element;
    int e;

    CircleImageView CircleImageViewProfile;
    private int SELECT_IMAGE = 1001;
    private int CROP_IMAGE = 2001;

    private String image_user;

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
        food_lose = findViewById(R.id.food_lose);
        back_login = findViewById(R.id.button_back_login);
        radioGroup_gender = findViewById(R.id.radioGroup_gender);
        CircleImageViewProfile = findViewById(R.id.pic_user);

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
        name_edit = findViewById(R.id.Text_name);
        radio_men_button = findViewById(R.id.radio_men);
        radio_women_button = findViewById(R.id.radio_women);
        text_birth = findViewById(R.id.text_date);
        text_element = findViewById(R.id.element);
        text_body = findViewById(R.id.body);
        food_lose = findViewById(R.id.food_lose);
        pic_profile = findViewById(R.id.pic_user);

        pic_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Image from Gallery"), SELECT_IMAGE);
            }
        });

        //Add data_member to database server
        okay = findViewById(R.id.submit_button);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignUp();
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

        //Confirmpassword
        confirm_pass.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    confirm_pass.setError("กรุณากรอกรหัสผ่านอีกครั้ง");
                    confirm_pass.requestFocus();
                } else if (!PASSWORD_PATTERN.matcher(s.toString()).matches()) {
                    confirm_pass.setError("รหัสผ่านต้องมีความยาวน้อยกว่า 6 ตัวอักษร");
                    confirm_pass.requestFocus();
                } else if (!(s.toString().equals(confirm_pass.getEditText().getText().toString().trim()))) {
                    confirm_pass.setError("รหัสผ่านไม่ถูกต้อง");
                    confirm_pass.requestFocus();
                } else if (s.toString().equals(confirm_pass.getEditText().getText().toString().trim())) {
                    confirm_pass.setError(null);
                }
            }
        });
        //username
        name_edit.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    name_edit.setError("กรุณากรอกชื่อผู้ใช้");
                    name_edit.requestFocus();
                } else if (s.toString().length() > 50) {
                    name_edit.setError("ชื่อจริงมีความยาวเกินกำหนด");
                    name_edit.requestFocus();
                } else {
                    name_edit.setError(null);
                }
            }
        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

//        //Select photo Fragment_profile
//        ImageView upload = findViewById(R.id.user);
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
//            }
//        });

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
        int y1 = 0;
        String m1;
        String date;

        if (String.valueOf(month).length() == 1) {
            date = (String.valueOf(dayOfMonth) + " " + "0" + String.valueOf(month) + " " + String.valueOf(year + 543));
            m1 = "0" + String.valueOf(month);
        } else {
            date = (String.valueOf(dayOfMonth) + " " + String.valueOf(month) + " " + String.valueOf(year + 543));
            m1 = String.valueOf(month);
        }
        y1 = (year + 543);
        text_view_birth.setText(date);

        Element_Cal(y1);
    }

    public void Element_Cal(int y1) {

        int element = 0;
        int element_full = y1;
        int element_sum = element_full % 10;

        int element_two = y1 % 100;

        if (element_sum == 6) {
            element = 6;
        } else if (element_sum < 6) {
            if ((element_full % 100) <= 5) {
                element_sum = (element_sum + 10) - 6;
                element = element_sum;
                if (element > 6) {
                    element = element - 6;
                }

            } else if ((element_full % 100) >= 10 && (element_full % 100) <= 99) {


                while (element_two > 6) {
                    element_two -= 6;
                    element = element_two;
                }
            }
        } else if (element_sum > 6) {
            element = element_sum - 6;
            Log.e("number ", Integer.toString(element));
        }


        if (element == 1) {
            string_element = "ธาตุไม้";
            element_name.setText("ธาตุไม้");

        } else if (element == 2) {
            string_element = "ธาตุดิน";
            element_name.setText("ธาตุดิน");

        } else if (element == 3) {
            string_element = "ธาตุไฟ";
            element_name.setText("ธาตุไฟ");

        } else if (element == 4) {
            string_element = "ธาตุน้ำ";
            element_name.setText("ธาตุน้ำ");

        } else if (element == 5) {
            string_element = "ธาตุดิน";
            element_name.setText("ธาตุดิน");

        } else if (element == 6) {
            string_element = "ธาตุโลหะ(ทอง)";
            element_name.setText("ธาตุโลหะ(ทอง)");

        }
        e = element;
        yhinORyhang(element);


    }

    public String yhinORyhang(int element) {

        if (element == 1 || element == 9) {
            yhinyhang.setText("หยิน");
            return "หยิน";
        } else if (element == 2 || element == 8) {
            yhinyhang.setText("หยาง");
            return "หยาง";
        } else if (element == 3) {
            yhinyhang.setText("หยิน");
            return "หยิน";
        } else if (element == 4) {
            yhinyhang.setText("หยิน");
            return "หยิน";
        } else if (element == 5) {
            yhinyhang.setText("หยาง");
            return "หยาง";
        } else if (element == 6 || element == 7) {
            yhinyhang.setText("หยาง");
            return "หยาง";
        }
        return "";
    }

    private void userSignUp() {
        System.out.println("รูป : " + image_user);
        if (!validationError()) {
            Toast.makeText(this, "กรอกข้อมูลไม่ครบ", Toast.LENGTH_LONG).show();
            return;
        } else if (food_lose.getText().toString().isEmpty()) {
            Toast.makeText(this, "กรุณาระบุวัตถุดิบอาหารที่แพ้", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setMessage("คุณแน่ใจหรือไม่ว่าข้อมูลถูกต้อง?");
            builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    String email    = Text_Email.getEditText().getText().toString().trim();
                    String password = Pass_word.getEditText().getText().toString().trim();
                    String username = name_edit.getEditText().getText().toString().trim();
                    String gender   = CheckGender();
                    String birthday = text_birth.getText().toString();
                    String food     = food_lose.getText().toString();
                    String picture  = image_user;

//                    System.out.println("รูป : " + image_user);
//                    Log.e("Image", image_user);

                    Call<DefaultResponse> call = retro.getApi().createUser(email, password, username, gender, birthday, string_element, food, "", yhinORyhang(e), 0, 0);
                    call.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                            if (response.body().isStatus()) {
                                Toast.makeText(Register.this, "สมัครสมาชิกสำเร็จ", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this, LogIn.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {

                        }
                    });

                }
            });
            builder.setNegativeButton("ไม่ยืนยัน", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
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


    private boolean validatePassword() {
        String passwordInput = Pass_word.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String ConfirmPasswordInput = confirm_pass.getEditText().getText().toString().trim();

        if (ConfirmPasswordInput.isEmpty()) {
            return false;
        } else if (!PASSWORD_PATTERN.matcher(ConfirmPasswordInput).matches()) {
            return false;
        } else if (!(ConfirmPasswordInput.equals(Pass_word.getEditText().getText().toString().trim()))) {
            Toast.makeText(this, "รหัสผ่านไม่ตรงกัน",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateUsername() {
        String UsernameInput = name_edit.getEditText().getText().toString().trim();

        if (UsernameInput.isEmpty()) {
            return false;
        } else if (UsernameInput.length() > 50) {
            return false;
        } else {
            return true;
        }
    }

    public void checkButton(View v) {
        int radioId = radioGroup_gender.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        Toast.makeText(this, "กรุณาระบุเพศ" + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }

    private boolean validateBD() {
        String birthday = text_birth.getText().toString().trim();
        if (birthday.equals("เลือกวันเกิด")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateFoodLose() {
        String FoodLose = food_lose.getText().toString().trim();
        if (FoodLose.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


    private boolean validationError() {
        if (!validateEmail() || !validatePassword() || !validateConfirmPassword() || !validateUsername() || !validateBD() ) {
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

    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                if (data != null) {
                    CropImage(data.getData());
                }
            } else if (requestCode == CROP_IMAGE) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = bundle.getParcelable("data");

                image_user = imageToString(bitmap);

                CircleImageViewProfile.setImageBitmap(bitmap);
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(Register.this, "Canceled", Toast.LENGTH_SHORT).show();
        }
    }

    private void CropImage(Uri uri) {
        try {
            Intent CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.setDataAndType(uri, "image/*");
            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 180);
            CropIntent.putExtra("aspectX", 4);
            CropIntent.putExtra("aspectY", 4);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);
            startActivityForResult(CropIntent, CROP_IMAGE);
        } catch (ActivityNotFoundException ex) {
        }
    }

}
