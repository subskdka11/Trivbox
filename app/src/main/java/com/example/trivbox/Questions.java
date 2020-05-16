package com.example.trivbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions extends AppCompatActivity {
    private TextView questionNoText, questionText;
    private Button option1, option2, option3, option4;
    private Result currentResult;
    int questionNo;
    List<Result> apiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        getAllId();

        Intent i = getIntent();
        apiResponse = (List<Result>) i.getExtras().getSerializable("name_this");
        questionNo = 1;
        currentResult = apiResponse.get(questionNo-1);

        setData();

        option1.setOnClickListener(checkAnswer);
        option2.setOnClickListener(checkAnswer);
        option3.setOnClickListener(checkAnswer);
        option4.setOnClickListener(checkAnswer);

    }

    private View.OnClickListener checkAnswer = new View.OnClickListener() {
        public void onClick(View v) {
            String clickedOption = (String) ((Button) v).getText();
            if (clickedOption.equals(currentResult.getCorrectAnswer())) {
                Toast.makeText(Questions.this, "Correct Answer", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Questions.this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
            }
            runThread();
            questionNo = questionNo + 1;
            if (questionNo <= 10) {
                currentResult = apiResponse.get(questionNo - 1);
                setData();
            }else{
                startActivity(new Intent(Questions.this, ClassicOptions.class));
            }
        }
    };

    private void runThread() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void getAllId(){
        questionNoText = findViewById(R.id.question_no);
        questionText = findViewById(R.id.question_id);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
    }

    public void setData() {
        String question = currentResult.getQuestion();
        String correctAnswer = currentResult.getCorrectAnswer();
        List<String> wrongAnswer = currentResult.getIncorrectAnswers();
        String questionType = currentResult.getType();

        questionNoText.setText("Question No. " + questionNo);
        questionText.setText(question);

        ArrayList<String> allOptions = new ArrayList<String>();
        allOptions.add(correctAnswer);
        allOptions.addAll(wrongAnswer);

        Collections.shuffle(allOptions);

        option1.setText(allOptions.get(0));
        option2.setText(allOptions.get(1));
        if (questionType.equals("multiple")){
            option3.setVisibility(View.VISIBLE);
            option4.setVisibility(View.VISIBLE);

            option3.setText(allOptions.get(2));
            option4.setText(allOptions.get(3));
        }else{
            option3.setVisibility(View.GONE);
            option4.setVisibility(View.GONE);
        }
    }
}
