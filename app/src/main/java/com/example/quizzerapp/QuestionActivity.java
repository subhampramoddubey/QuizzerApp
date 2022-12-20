package com.example.quizzerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView headQuestion;
    TextView noOfQues;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    List<Question> questions;
    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        noOfQues = findViewById(R.id.quesNo);
        confirm = findViewById(R.id.confirm);
        headQuestion = findViewById(R.id.Question);
        optionA = findViewById(R.id.optA);
        optionB = findViewById(R.id.optB);
        optionC = findViewById(R.id.optC);
        optionD = findViewById(R.id.optD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);

        questions = new ArrayList<Question>(){
            {
                add(new Question("Who is the Prime Minister of India ?", "C", "Rahul Gabdhi", "Amit Shah","Narendra Modi", "Amir Khan"));
                add(new Question("The Non-cooperation Movement began on which one of the following dates ?", "A", "January 1921", " November 1921","December 1921", "May 1921"));
                add(new Question("In which of the following places Mahatma Gandhi organised satyagraha for the first time in India?", "D", "Dandi", "Ahmedabad","Kheda", "Champaran"));
                add(new Question("In which of the following Indian National Congress sessions was the demand of‘Purna Swaraj’ formalised in December 1929?", "B", "Madras", "Lahore", "Calcutta", "Nagpur"));
                add(new Question("In which year the Second Round Table Conference was held?", "A", "1931", "1935", "1935", "1945"));
            }
        };

        loadQuestion();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadAnswer();

            }
        });



    }


    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }


    private void loadQuestion(){
        if(questions.size() > 0) {
            Question q = questions.remove(0);
            headQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            noOfQues.setText(String.valueOf(questions.size()+1));

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, ShowScoreActivity1.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer() {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.optA:
                Answer="A";
                break;

            case R.id.optB:
                Answer="B";
                break;

            case R.id.optC:
                Answer="C";
                break;

            case R.id.optD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();


        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            onRestart();
        }
        else {
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            onRestart();
        }

    }

}
