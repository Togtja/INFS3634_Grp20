package com.example.grp20_app;

import android.graphics.Bitmap;
import android.util.Pair;

import java.util.ArrayList;

public class WW1Quiz {
    private int id;
    private String question;
    private Bitmap image;
    private ArrayList<Pair<Integer, String>> anwser;
    private int correctAns;

    public WW1Quiz(int id, String question, Bitmap image, ArrayList<Pair<Integer, String>> anwser, int correctAns) {
        this.id = id;
        this.question = question;
        this.image = image;
        this.anwser = anwser;
        this.correctAns = correctAns;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public ArrayList<Pair<Integer, String>> getAnwser() {
        return anwser;
    }

    public void setAnwser(ArrayList<Pair<Integer, String>> anwser) {
        this.anwser = anwser;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
