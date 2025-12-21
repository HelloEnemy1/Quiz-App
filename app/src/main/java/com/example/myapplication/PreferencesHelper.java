package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private static final String PREFS_NAME = "QuizAppPrefs";
    private static final String KEY_LANGUAGE = "language";
    private static final String KEY_HIGH_SCORE = "high_score";
    private static final String KEY_TOTAL_QUIZZES = "total_quizzes";

    private SharedPreferences prefs;

    public PreferencesHelper(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setLanguage(String languageCode) {
        prefs.edit().putString(KEY_LANGUAGE, languageCode).apply();
    }

    public String getLanguage() {
        return prefs.getString(KEY_LANGUAGE, "en");
    }

    public void setHighScore(int score) {
        int currentHigh = getHighScore();
        if (score > currentHigh) {
            prefs.edit().putInt(KEY_HIGH_SCORE, score).apply();
        }
    }

    public int getHighScore() {
        return prefs.getInt(KEY_HIGH_SCORE, 0);
    }

    // Total quizzes taken
    public void incrementQuizCount() {
        int current = getTotalQuizzes();
        prefs.edit().putInt(KEY_TOTAL_QUIZZES, current + 1).apply();
    }

    public int getTotalQuizzes() {
        return prefs.getInt(KEY_TOTAL_QUIZZES, 0);
    }

    // Clear all preferences
    public void clearAll() {
        prefs.edit().clear().apply();
    }
}