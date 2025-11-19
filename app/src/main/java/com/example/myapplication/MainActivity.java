package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView myTextView;
    EditText myEditText;
    Button submitButton;
    Button resetButton;
    TextView myToast;

    Question[] questions;
    int currentQuestionIndex = 0;
    int sum = 0;
    int qs = 5;
    String originalTextViewText = "";
    String originalToastText = "";
    boolean quizStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        questions = new Question[5];
        generateRandomQuestions();

        myToast = (TextView) findViewById(R.id.greeting1t);
        myTextView = (TextView) findViewById(R.id.greeting);
        myEditText = (EditText) findViewById((R.id.typeName));
        submitButton = (Button) findViewById(R.id.SubmitButton);
        resetButton = (Button) findViewById(R.id.Reset);


        originalTextViewText = myTextView.getText().toString();
        originalToastText = myToast.getText().toString();


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!quizStarted) {
                    quizStarted = true;
                    submitButton.setText("Submit");
                    myTextView.setText(questions[currentQuestionIndex].getQuestion());
                    return;
                }

                String answer = myEditText.getText().toString();

                // Check answer (Y for true, N for false)
                boolean correctAnswer = questions[currentQuestionIndex].getAnswer();

                if (answer.equalsIgnoreCase("Y") && correctAnswer) {
                    myToast.setText("Excellent!");
                    sum += 100;
                } else if (answer.equalsIgnoreCase("N") && !correctAnswer) {
                    myToast.setText("Excellent!");
                    sum += 100;
                } else if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
                    myToast.setText("Wrong!");
                } else {
                    myToast.setText("Please enter Y or N!");
                    return;
                }


                currentQuestionIndex++;

                if (currentQuestionIndex < questions.length) {
                    myTextView.setText(questions[currentQuestionIndex].getQuestion());
                    myEditText.setText("");
                } else {
                    // All questions answered
                    int finalScore = sum / qs;
                    myToast.setText("Quiz Complete! Final Score - " + finalScore);
                    submitButton.setEnabled(false);
                }
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentQuestionIndex = 0;
                sum = 0;
                quizStarted = false;

                myEditText.setText("");


                myTextView.setText(originalTextViewText);
                myToast.setText(originalToastText);


                submitButton.setText("Start");


                generateRandomQuestions();

                submitButton.setEnabled(true);
            }
        });
    }


    private void generateRandomQuestions() {
        for (int i = 0; i < 5; i++) {
            int num1 = (int)(Math.random() * 10) + 1; // Random number 1-10
            int num2 = (int)(Math.random() * 10) + 1;
            int correctSum = num1 + num2;


            if (Math.random() < 0.5) {

                questions[i] = new Question(num1, num2, correctSum);
            } else {

                int offset = (int)(Math.random() * 3) + 1;
                int wrongSum = Math.random() < 0.5 ? correctSum + offset : correctSum - offset;
                // Make sure wrong sum is positive
                if (wrongSum < 1) wrongSum = correctSum + offset;
                questions[i] = new Question(num1, num2, wrongSum);
            }
        }
    }
}