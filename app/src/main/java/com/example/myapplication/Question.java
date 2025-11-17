package com.example.myapplication;

import androidx.annotation.NonNull;

public class Question {
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public Question(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public String getQuestion() {
        return "Is " + a + " + " + b + " = " + c  + "?";
    }
}
