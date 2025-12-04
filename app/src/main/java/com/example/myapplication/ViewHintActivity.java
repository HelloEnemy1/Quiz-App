package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ViewHintActivity extends AppCompatActivity {

    TextView hintText;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load saved language preference
        loadLocale();

        setContentView(R.layout.activity_view_hint);

        hintText = findViewById(R.id.hintText);
        backButton = findViewById(R.id.backButton);

        // Get the hint from the intent
        Intent intent = getIntent();
        String hint = intent.getStringExtra("HINT");

        if (hint != null) {
            hintText.setText(hint);
        } else {
            hintText.setText(R.string.no_hint_available);
        }

        // Back button to return to quiz
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "en");
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}