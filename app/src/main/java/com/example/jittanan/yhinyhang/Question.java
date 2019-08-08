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
    int [] answer;
    TextView Text_question ;
    Button button_next ;
    Button button_previous;
    Button button_confirmall;
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
        button_confirmall = findViewById(R.id.button_confirmall);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        radioButton5 = findViewById(R.id.radioButton5);

        radioAnswer = new RadioButton[6];
        radioAnswer[0] = null;
        radioAnswer[1] = radioButton1;
        radioAnswer[2] = radioButton2;
        radioAnswer[3] = radioButton3;
        radioAnswer[4] = radioButton4;
        radioAnswer[5] = radioButton5;

        setQuestion();

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPoint() != 0) {
                    if(index < question.size()) {
                        updateQuestion();
//                        updateAnswer();
                    }
                } else {
                    Toast.makeText(Question.this, "กรุณาเลือกระดับคะแนน", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPoint() != 0 ) {

                        backward_question();

                } else {
                    Toast.makeText(Question.this, "กรุณาเลือกระดับคะแนน", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_confirmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double score = CalculatetoScore_yhinyhang();
                if(checkPoint() != 0){
                    Toast.makeText(Question.this,"คะแนน",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Question.this, "กรุณาเลือกระดับคะแนน", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public void updateQuestion() {
        index++;
        Text_question.setText(index+". "+question.get(index));

        if (index != 1){
            button_previous.setVisibility(View.VISIBLE);
            button_confirmall.setVisibility(View.GONE);


        }
        if (index == 21) {
            button_next.setVisibility(View.GONE);
            button_confirmall.setVisibility(View.VISIBLE);
        }



        answer[index]=(checkPoint());
        Log.e("updateAnswer", String.valueOf(answer[index]));

        Log.e("QQ", Integer.toString(checkPoint()));
    }

    public void updateAnswer() {
        for(int i=1; i<radioAnswer.length; i++) {
            radioAnswer[i].setChecked(false);

        }
    }

    public void backward_question(){
        if (index != 1){
            index--;
        }

        Log.e("Index", Integer.toString(index));
        Text_question.setText(index+". "+question.get(index));
        if (index == 1 ){
            button_previous.setVisibility(View.GONE);
            button_confirmall.setVisibility(View.GONE);

        }else {
            button_next.setVisibility(View.VISIBLE);
            button_confirmall.setVisibility(View.GONE);
        }

        Log.e("AnswerIndex: ", String.valueOf(answer[index]));

//        radioAnswer[answer[index]].setChecked(true);

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
        question.add("คุณปัสสาวะมาก ระดับใด ?");
        question.add("คุณรู้สึกท้องอืดระดับใด ?");
        question.add("คุณร้อนใน ระดับใด ?");
        question.add("คุณปากแห้งระดับใด ?");
        question.add("คุณคอแห้งระดับใด ?");
        question.add("คุณข้ีหงุดหงิดระดับใด ?");
        question.add("คุณผิวแห้งระดับใด ?");
        question.add("คุณฝ่ามือและฝ่าเท้าร้อนระดับใด ?");

        //yhang
        question.add("คุณหน้าแดงระดับใด ?");
        question.add("คุณหายใจแรงระดับใด ?");
        question.add("คุณรู้สึกตัวร้อนระดับใด?");
        question.add("คุณชอบด่ืมนํ้าเย็นระดับใด?");
        question.add("คุณท้องผูกระดับใด ?");
        question.add("คุณปัสสาวะเหลืองเข้มระดับใด ?");
        question.add("ฝ่ามือและฝ่าเท้าของคุณเย็นง่ายระดับใด ?");
        question.add("คุณปัสสาวะบ่อยตอนกลางคืนระดับใด ?");

        answer = new int[question.size()];
        answer[0]=0;

        Text_question.setText(index + ". " + question.get(1));
        button_previous.setVisibility(View.GONE);
        button_confirmall.setVisibility(View.GONE);
    }

    public int checkPoint() {
        for(int i=1; i<radioAnswer.length; i++) {
            if (radioAnswer[i].isChecked()) {
                 Log.e("eiei"+Integer.toString(i), Integer.toString(i));
                return i;
            }
        }
        return 0;
    }

    public Double CalculatetoScore_yhinyhang(){
        int score = checkPoint();
        int index = checkPoint();
        int sum=0;
        double score_yhin=0 ;
        double score_yhang = 0;
        int check = 0;

        if(index>= 1 && index <=13 && check ==1){
            sum += score ;
            check =1;

        }if (index>=14 && index <=21 && check == 2) {
            sum += score ;
            check = 2;
        }

        if (check == 1) {
            return score_yhin = sum/13;

        }
        if (check == 2){
            return score_yhang = sum/8;
        }
        return 0.0;
    }

}
