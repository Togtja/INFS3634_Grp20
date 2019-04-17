package com.example.grp20_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;

import java.util.ArrayList;

public class WW1Quiz {
    private int id;
    private String question;
    private Bitmap image;
    private ArrayList<Pair<Integer, String>> answers;
    private int correctAns;

    public WW1Quiz(int id, String question, ArrayList<Pair<Integer, String>> answers, int correctAns,  Bitmap image) {
        if(correctAns <= 0){
            throw new IllegalArgumentException("Amount of correct answers can't be 0 or less");
        }
        this.id = id;
        this.question = question;
        this.image = image;
        this.answers = answers;
        this.correctAns = correctAns;
    }
    public WW1Quiz(int id, String question, ArrayList<Pair<Integer, String>> answers, int correctAns) {
        if(correctAns <= 0){
            throw new IllegalArgumentException("Amount of correct answers can't be 0 or less");
        }
        this.id = id;
        this.question = question;
        this.image = null;
        this.answers = answers;
        this.correctAns = correctAns;
    }
    public WW1Quiz(int id, String question, ArrayList<Pair<Integer, String>> answers, Bitmap image) {

        this.id = id;
        this.question = question;
        this.image = image;
        this.answers = answers;
        this.correctAns = 1;
    }
    public WW1Quiz(int id, String question, ArrayList<Pair<Integer, String>> answers) {

        this.id = id;
        this.question = question;
        this.image = null;
        this.answers = answers;
        this.correctAns = 1;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public ArrayList<Pair<Integer, String>> getAnswer() {
        return answers;
    }

    public void setAnswer(ArrayList<Pair<Integer, String>> answers) {
        this.answers = answers;
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

    static ArrayList<WW1Quiz> getBuildUpQuiz(Context context){
        ArrayList<WW1Quiz> ret = new ArrayList<>();
        //Question 1
            //options for questions
        ArrayList<Pair<Integer, String>> options = new ArrayList<>();
        options.add(new Pair<>(0 , "Not me"));
        options.add(new Pair<>(0 , "Not me"));
        options.add(new Pair<>(1 , "But it's me Dio"));
        options.add(new Pair<>(0 , "Yahh no, not me"));

        //Optonal photo
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.archyduke);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Hey It's a Test Question", options, icon));

        //Question 2
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Not me"));
        options.add(new Pair<>(1 , "HEYAAHHHH"));
        options.add(new Pair<>(0 , "Maaaan, I wish"));
        options.add(new Pair<>(0 , "Yahh no, not me"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Hey It's a Test Question", options));

        //Question 3
        //multiple correct annswers

        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Not me"));
        options.add(new Pair<>(1 , "HEYAAHHHH"));
        options.add(new Pair<>(0 , "Maaaan, I wish"));
        options.add(new Pair<>(1 , "YIP YIP MOTHERFUCKER"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Hey It's a Test Question", options, 2));

        return ret;

    }
    static ArrayList<WW1Quiz> get1914Quiz(){
        return null;
    }
    static ArrayList<WW1Quiz> get1915Quiz(){
        return null;
    }
    static ArrayList<WW1Quiz> get1916Quiz(){
        return null;
    }
    static ArrayList<WW1Quiz> get1917Quiz(){
        return null;
    }
    static ArrayList<WW1Quiz> get1918Quiz(){
        return null;
    }
    static ArrayList<WW1Quiz> getAfterMatchQuiz(){
        return null;
    }

}
