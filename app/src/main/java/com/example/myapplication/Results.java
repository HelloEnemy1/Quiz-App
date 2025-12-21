package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Results extends AppCompatActivity {

    TextView scoreText;
    TextView messageText;
    Button restartButton;
    Button emailButton;

    PreferencesHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create preferences helper FIRST
        prefsHelper = new PreferencesHelper(this);

        // Load saved language preference
        loadLocale();

        setContentView(R.layout.activity_results);

        scoreText = findViewById(R.id.scoreText);
        messageText = findViewById(R.id.messageText);
        restartButton = findViewById(R.id.restartButton);
        emailButton = findViewById(R.id.emailButton);

        // Get the score from the intent
        Intent intent = getIntent();
        int finalScore = intent.getIntExtra("FINAL_SCORE", 0);
        int totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 5);

        // Save high score and increment quiz count
        try {
            prefsHelper.setHighScore(finalScore);
            prefsHelper.incrementQuizCount();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Display the score as percentage
        scoreText.setText(getString(R.string.your_score, finalScore + "%"));

        // Display a message based on performance
        String message;
        if (finalScore >= 80) {
            message = getString(R.string.message_excellent);
        } else if (finalScore >= 60) {
            message = getString(R.string.message_good);
        } else if (finalScore >= 40) {
            message = getString(R.string.message_keep_practicing);
        } else {
            message = getString(R.string.message_dont_give_up);
        }
        messageText.setText(message);

        // Restart button to go back to MainActivity
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restartIntent = new Intent(Results.this, MainActivity.class);
                restartIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(restartIntent);
                finish();
            }
        });

        // Email button to send score via email (implicit intent)
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmailWithScore(finalScore, totalQuestions);
            }
        });
    }

    private void sendEmailWithScore(int score, int total) {
        String subject = getString(R.string.email_subject);
        String body = getString(R.string.email_body, score + "%");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"student@example.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.email_chooser_title)));
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle case where no email app is available
        }
    }

    private void loadLocale() {
        String language = prefsHelper.getLanguage();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}