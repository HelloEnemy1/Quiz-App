package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
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

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView myTextView;
    EditText myEditText;
    Button submitButton;
    Button resetButton;
    Button hintButton;
    Button englishButton;
    Button arabicButton;
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
        hintButton = (Button) findViewById(R.id.HintButton);
        englishButton = (Button) findViewById(R.id.EnglishButton);
        arabicButton = (Button) findViewById(R.id.ArabicButton);


        originalTextViewText = myTextView.getText().toString();
        originalToastText = myToast.getText().toString();


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!quizStarted) {
                    quizStarted = true;
                    submitButton.setText(R.string.button_submit);
                    myTextView.setText(questions[currentQuestionIndex].getQuestion());
                    hintButton.setVisibility(View.VISIBLE);
                    return;
                }

                String answer = myEditText.getText().toString();

                // Check answer (Y for true, N for false)
                boolean correctAnswer = questions[currentQuestionIndex].getAnswer();

                if (answer.equalsIgnoreCase("Y") && correctAnswer) {
                    myToast.setText(R.string.feedback_excellent);
                    sum += 100;
                } else if (answer.equalsIgnoreCase("N") && !correctAnswer) {
                    myToast.setText(R.string.feedback_excellent);
                    sum += 100;
                } else if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
                    myToast.setText(R.string.feedback_wrong);
                } else {
                    myToast.setText(R.string.feedback_invalid);
                    return;
                }


                currentQuestionIndex++;

                if (currentQuestionIndex < questions.length) {
                    myTextView.setText(questions[currentQuestionIndex].getQuestion());
                    myEditText.setText("");
                } else {
                    // All questions answered - launch Results
                    int finalScore = sum / qs;
                    Intent intent = new Intent(MainActivity.this, Results.class);
                    intent.putExtra("FINAL_SCORE", finalScore);
                    intent.putExtra("TOTAL_QUESTIONS", qs);
                    startActivity(intent);

                    // Reset the quiz for when user returns
                    resetQuiz();
                }
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetQuiz();
            }
        });

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizStarted && currentQuestionIndex < questions.length) {
                    Intent hintIntent = new Intent(MainActivity.this, ViewHintActivity.class);
                    hintIntent.putExtra("HINT", questions[currentQuestionIndex].getHint());
                    startActivity(hintIntent);
                }
            }
        });

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
            }
        });

        arabicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("ar");
            }
        });
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        // Recreate activity to apply changes
        recreate();
    }

    private void resetQuiz() {
        currentQuestionIndex = 0;
        sum = 0;
        quizStarted = false;

        myEditText.setText("");

        myTextView.setText(originalTextViewText);
        myToast.setText(originalToastText);

        submitButton.setText(R.string.button_start);
        hintButton.setVisibility(View.GONE);

        generateRandomQuestions();

        submitButton.setEnabled(true);
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