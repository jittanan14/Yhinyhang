package com.example.jittanan.yhinyhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Question extends AppCompatActivity {

    ArrayList<String> question;
    TextView Text_question ;
    Button button_next ;
    Button button_previous;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;

    RadioButton[] radioAnswer;

    int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().hide();

        Text_question = findViewById(R.id.Text_question);
        button_next = findViewById(R.id.button_next_question);
        button_previous = findViewById(R.id.button_previous_question);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        radioButton5 = findViewById(R.id.radioButton5);

        radioAnswer = new RadioButton[5];
        radioAnswer[0] = radioButton1;
        radioAnswer[1] = radioButton2;
        radioAnswer[2] = radioButton3;
        radioAnswer[3] = radioButton4;
        radioAnswer[4] = radioButton5;

        setQuestion();

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton1.setSelected(false);
                Log.e("Question", String.valueOf(radioButton1.isChecked()));

                radioButton1.setChecked(false);

                updateAnswer();

                String point_question = Checkpoint();
                if (!point_question.isEmpty()) {
                    updateQuestion();
                }
                else {
                    Toast.makeText(Question.this, "กรุณาเลือกระดับคะแนน", Toast.LENGTH_SHORT).show();
                }

            }
        });
//
//        button_previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String point_question = Checkpoint();
//                if (!point_question.isEmpty()) {
//                    for (int i = 0; i < question.size(); i++) {
//                        Text_question.setText(i + 1 + " " + question.get(i));
//                    }
//
//                }else {
//                    Toast.makeText(Question.this, "กรุณาเลือกระดับคะแนน", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });

    }

    public void updateQuestion() {
        Text_question.setText(index+". "+question.get(index));

        index++;
    }

    public void updateAnswer() {
        for(int i=0; i<radioAnswer.length; i++) {
            radioAnswer[i].setChecked(false);
        }
    }

    public int Random_question(){

        int j = 0;

        for (int i=0; i< question.size(); i++) {
            j=i;
            return j ;
        }


    return 0;

    }

    public void setQuestion(){
        question = new ArrayList<>();

        question.add(" ");
        //yhin
        question.add("คุณมีอาการหน้าซีดระดับใด ?");
        question.add("คุณหายใจเบาระดับใด ?");
        question.add("คุณขี้หนาวระดับใด ?");
        question.add("คุณรู้สึกไม่ค่อยมีแรงระดับใด ?");
        question.add("คุณอุจจาระน้อยและค่อนข้างเหลวระดับใด?");
        question.add("คุณปัสสาวะมากและใสระดับใด ?");
        question.add("คุณรู้สึกท้องอืดระดับใด ?");
        question.add("คุณร้อนในระดับใด ?");
        question.add("คุณปากแห้งระดับใด ?");
        question.add("คุณคอแห้งหรือไม่ ?");
        question.add("คุณข้ีหงุดหงิดหรือไม่ ?");
        question.add("คุณผิวแห้งหรือไม่ ?");
        question.add("คุณฝ่ามือและฝ่าเท้าร้อนหรือไม่ ?");

        //yhang
        question.add("คุณหน้าแดงระดับใด ?");
        question.add("คุณหายใจแรงระดับใด ?");
        question.add("คุณรู้สึกตัวร้อนระดับใด?");
        question.add("คุณคอแห้งระดับใด ?");
        question.add("คุณชอบด่ืมนํ้าเย็นระดับใด?");
        question.add("คุณท้องผูกระดับใด ?");
        question.add("คุณปัสสาวะเหลืองเข้มระดับใด ?");
        question.add("ฝ่ามือและฝ่าเท้าของคุณเย็นง่ายระดับใด ?");
        question.add("คุณปัสสาวะบ่อยตอนกลางคืนระดับใด ?");
        question.add("คุณหน้าตาซีดระดับใด ?");

        Text_question.setText(index + ". " + question.get(1));

    }

    public String Checkpoint() {

        if (radioButton1.isChecked()) {
            return "1";
        } else if(radioButton2.isChecked()){
            return "2";
        }else if (radioButton3.isChecked()){
            return "3";
        }else if (radioButton4.isChecked()) {
            return "4";
        }else {;
            return "5";
        }
    }

}
