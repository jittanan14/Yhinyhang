package com.example.jittanan.yhinyhang;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

public class Question extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> questionArray;
    TextView Text_question;
    Button button_next;
    Button button_previous;
    Button button_confirmall;
    Button button_back_login;
    RadioButton radioButton;
    RadioGroup radioGroup;


    private int Score[];
    int yhin;
    int yhang;
    double sum_yhin  =0;
    double sum_yhang = 0;
    int index = 1;
    double max = Double.MIN_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().hide();

        Text_question = findViewById(R.id.Text_question);
        button_next = findViewById(R.id.button_next_question);
        button_next.setOnClickListener(this);
        button_previous = findViewById(R.id.button_previous_question);
        button_previous.setOnClickListener(this);
        button_confirmall = findViewById(R.id.button_confirmall);
        button_confirmall.setOnClickListener(this);
        button_back_login = findViewById(R.id.button_back_login);
        button_back_login.setOnClickListener(this);
        radioGroup = findViewById(R.id.radio_answer);


        setQuestion();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_previous_question:
                backQuestion();
                break;
            case R.id.button_next_question:
                nextQuestion();
                break;
            case R.id.button_confirmall :
                confirmAll();
                break;
            case R.id.button_back_login :
                Intent intent = new Intent(Question.this,LogIn.class);
                startActivity(intent);
        }
    }

    private void backQuestion() {
        if (index != 1)
            index--;

        Text_question.setText(index + ". " + questionArray.get(index));

        setAnswer(Score[index]);

        if (index == 1) {
            setButton(0);
        } else {
            setButton(2);
        }
    }

    private void nextQuestion() {
        if (index < questionArray.size() - 1)
            index++;

        Text_question.setText(index + ". " + questionArray.get(index));

        int score = Score[index];
        if (index == questionArray.size() - 1) {
            if (score > 0) {
                setAnswer(score);
                setButton(4);
            } else {
                setButton(3);
                radioGroup.clearCheck();
            }
        } else {
            if (score > 0) {
                setAnswer(score);
                setButton(2);
            } else {
                setButton(1);
                radioGroup.clearCheck();
            }
        }
    }

    private void setAnswer(int score) {
        switch (score) {
            case 1:
                radioButton = findViewById(R.id.radioButton1);
                radioButton.setChecked(true);
                break;
            case 2:
                radioButton = findViewById(R.id.radioButton2);
                radioButton.setChecked(true);
                break;
            case 3:
                radioButton = findViewById(R.id.radioButton3);
                radioButton.setChecked(true);
                break;
            case 4:
                radioButton = findViewById(R.id.radioButton4);
                radioButton.setChecked(true);
                break;
            case 5:
                radioButton = findViewById(R.id.radioButton5);
                radioButton.setChecked(true);
                break;
        }

        Log.e("Set Answer Score", String.valueOf(score));
    }

    private void setButton(int number) {

        /* Set Button
        0 >> first question have answer
        1 >> back&next question not have answer
        2 >> back&next question have answer
        3 >> last question not have answer
        4 >> last question have answer
        */

        switch (number) {
            case 0:
                button_previous.setVisibility(View.GONE);
                button_next.setEnabled(true);
                break;
            case 1:
                button_previous.setVisibility(View.VISIBLE);
                button_next.setVisibility(View.VISIBLE);
                button_next.setEnabled(false);
                button_confirmall.setVisibility(View.GONE);
                break;
            case 2:
                button_previous.setVisibility(View.VISIBLE);
                button_next.setVisibility(View.VISIBLE);
                button_next.setEnabled(true);
                button_confirmall.setVisibility(View.GONE);
                break;
            case 3:
                button_previous.setVisibility(View.VISIBLE);
                button_next.setVisibility(View.GONE);
                button_confirmall.setVisibility(View.VISIBLE);
                button_confirmall.setEnabled(false);
                break;
            case 4:
                button_next.setVisibility(View.GONE);
                button_confirmall.setVisibility(View.VISIBLE);
                button_confirmall.setEnabled(true);
                break;
        }
    }


    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        int score = Integer.parseInt(radioButton.getText().toString());
        Score[index] = score;


        if (index == questionArray.size() - 1) {
            button_confirmall.setEnabled(true);
        }
        else {
            button_next.setEnabled(true);
        }



        Toast.makeText(this, Integer.toString(score), Toast.LENGTH_SHORT).show();


    }


    public void setQuestion() {
        questionArray = new ArrayList<>();
        questionArray.add(" ");

        //yhin
        questionArray.add("คุณมีอาการหน้าซีดระดับใด ?");
        questionArray.add("คุณหายใจเบาระดับใด ?");
        questionArray.add("คุณขี้หนาวระดับใด ?");
        questionArray.add("คุณรู้สึกไม่ค่อยมีแรงระดับใด ?");
        questionArray.add("คุณอุจจาระน้อยและค่อนข้างเหลวระดับใด?");
        questionArray.add("คุณปัสสาวะมาก ระดับใด ?");
        questionArray.add("คุณรู้สึกท้องอืดระดับใด ?");
        questionArray.add("คุณร้อนใน ระดับใด ?");
        questionArray.add("คุณปากแห้งระดับใด ?");
        questionArray.add("คุณคอแห้งระดับใด ?");
        questionArray.add("คุณข้ีหงุดหงิดระดับใด ?");
        questionArray.add("คุณผิวแห้งระดับใด ?");
        questionArray.add("คุณฝ่ามือและฝ่าเท้าร้อนระดับใด ?");

        //yhang
        questionArray.add("คุณหน้าแดงระดับใด ?");
        questionArray.add("คุณหายใจแรงระดับใด ?");
        questionArray.add("คุณรู้สึกตัวร้อนระดับใด?");
        questionArray.add("คุณชอบด่ืมนํ้าเย็นระดับใด?");
        questionArray.add("คุณท้องผูกระดับใด ?");
        questionArray.add("คุณปัสสาวะเหลืองเข้มระดับใด ?");
        questionArray.add("ฝ่ามือและฝ่าเท้าของคุณเย็นง่ายระดับใด ?");
        questionArray.add("คุณปัสสาวะบ่อยตอนกลางคืนระดับใด ?");

        Score = new int[questionArray.size()];


        Text_question.setText(index + ". " + questionArray.get(1));
        button_previous.setVisibility(View.GONE);
        button_confirmall.setVisibility(View.GONE);
    }

    private void confirmAll() {

        CalculateNumyhinyhang();


        AlertDialog.Builder builder = new AlertDialog.Builder(Question.this, R.style.AlertDialogCustom);

       //เหลือกรณีเท่ากับจะให้เป็นตามที่คำนวณวันเกิดเลย
        int c = 0;
        if (sum_yhin > sum_yhang){
            max = sum_yhin;
            c=1;
        }else if (sum_yhang > sum_yhin) {
            max = sum_yhang;
            c=2;
        }


        String s1 = String.format("%.2f",sum_yhin);
        String s2 = String.format("%.2f",sum_yhang);

        builder.setTitle("ระดับคะแนน");
        if (c==1) {
            //set message
            builder.setMessage("หยินของคุณคือ " + s1 + "\nหยางของคุณคือ " + s2+"\n\nร่างกายของคุณมีความเป็นหยินมากกว่า");
            builder.setIcon(R.drawable.ic_yin);
        }
        else if (c==2){
            builder.setMessage("หยินของคุณคือ " + s1 + "\nหยางของคุณคือ " + s2+"\n\nร่างกายของคุณมีความเป็นหยางมากกว่า");
            builder.setIcon(R.drawable.ic_yang);
        }
        //set cancelable
        builder.setCancelable(true);
        //set positive / yes button

        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Question.this,MainActivity.class);
                startActivity(intent);

            }
        });

        //create alert dialog
        AlertDialog alertdialog = builder.create();
        //show alert dialog
        alertdialog.show();


    }

    public void CalculateNumyhinyhang(){

        for(int i = 1; i<=Score.length; i++){

            if(i>=1 && i<=13 ){
                sum_yhin += Score[i];
            }
            else if (i>=14 && i<=21) {
                sum_yhang += Score[i];
            }

        }

        sum_yhin = sum_yhin/13;
        sum_yhang = sum_yhang/8;


    }


}
