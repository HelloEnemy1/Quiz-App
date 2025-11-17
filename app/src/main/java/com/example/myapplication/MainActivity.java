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
    TextView myToast;

    Question q;

    Question a;

    Question b;
    Question c;
    Question d;

    int sum;

    int qs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

int qs = 5;
int sum = 0;

        myToast = (TextView) findViewById(R.id.greeting1t);

        Question q = new Question(6, 6, 12);
        Question a = new Question(5, 5, 10);
        Question b = new Question(6, 5, 12);
        Question c = new Question(8, 2, 13);
        Question d = new Question(4, 8, 32);
        myTextView = (TextView) findViewById(R.id.greeting);
        myEditText = (EditText) findViewById((R.id.typeName));
        submitButton = (Button) findViewById(R.id.SubmitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.setText("Submit");
                myTextView.setText(q.getQuestion());
                String name = myEditText.getText().toString();

                if (name.equalsIgnoreCase("Y")) {
                    myToast.setText("Excellent! Score - " + 100);
                } else {
                    myToast.setText("Try again Score - " + 0);
                }

            }


        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.setText("Submit");
                myTextView.setText(a.getQuestion());
                String name = myEditText.getText().toString();

                if (name.equalsIgnoreCase("Y")) {
                    myToast.setText("Excellent! Score - " + 100);
                } else {
                    myToast.setText("Try again Score - " + 0);
                }

            }


        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.setText("Submit");
                myTextView.setText(b.getQuestion());
                String name = myEditText.getText().toString();

                if (name.equalsIgnoreCase("Y")) {
                    myToast.setText("Excellent! Score - " + 100);
                } else {
                    myToast.setText("Try again Score - " + 0);
                }

            }


        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.setText("Submit");
                myTextView.setText(c.getQuestion());
                String name = myEditText.getText().toString();

                if (name.equalsIgnoreCase("Y")) {
                    myToast.setText("Excellent! Score - " + 100);
                } else {
                    myToast.setText("Try again Score - " + sum / qs);
                }

            }


        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.setText("Submit");
                myTextView.setText(d.getQuestion());
                String name = myEditText.getText().toString();

                if (name.equalsIgnoreCase("Y")) {
                    myToast.setText("Excellent! Score - " + 100);
                } else {
                    myToast.setText("Try again Score - " + 0);
                }

            }


        });





    }
}
