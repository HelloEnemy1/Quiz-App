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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        TextView myTextView;
        EditText myEditText;
        Button submitButton;
        myTextView = (TextView) findViewById(R.id.greeting);
        myEditText = (EditText) findViewById((R.id.typeName));
        submitButton = (Button) findViewById(R.id.SubmitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = myEditText.getText().toString();
                myTextView.setText("What is 5 + 5? :  "  + name);
            }


        });

    }
}