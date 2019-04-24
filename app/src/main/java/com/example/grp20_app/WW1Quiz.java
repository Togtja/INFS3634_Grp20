package com.example.grp20_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;

import java.util.ArrayList;
/*
* Stores the info you need to make and retrieve quizzes
*
*
* */
public class WW1Quiz {
    private int id; //Quiz id, At the moment it's not being used for anything
    private String question; //The Actual Question
    private Bitmap image;   //An Image if that is needed
    //// (Currently it needs to be a drawable, bit if we want to get it from a URL, all the code already exist for that)
    private ArrayList<Pair<Integer, String>> options; //the different options
    private int correctAns; //The Amount of correct answers i.e select the correct answers

    //Constructor for multiple correct answers and an image/bitmap
    public WW1Quiz(int id, String question, ArrayList<Pair<Integer, String>> answers, int correctAns,  Bitmap image) {
        if(correctAns <= 0){
            throw new IllegalArgumentException("Amount of correct answers can't be 0 or less");
        }
        this.id = id;
        this.question = question;
        this.image = image;
        this.options = answers;
        this.correctAns = correctAns;
    }
    //Constructor for multiple correct answers without image/bitmap
    public WW1Quiz(int id, String question, ArrayList<Pair<Integer, String>> answers, int correctAns) {
        if(correctAns <= 0){
            throw new IllegalArgumentException("Amount of correct answers can't be 0 or less");
        }
        this.id = id;
        this.question = question;
        this.image = null;
        this.options = answers;
        this.correctAns = correctAns;
    }
    //Constructor for 1 correct answers and an image/bitmap
    public WW1Quiz(int id, String question, ArrayList<Pair<Integer, String>> answers, Bitmap image) {

        this.id = id;
        this.question = question;
        this.image = image;
        this.options = answers;
        this.correctAns = 1;
    }
    //Constructor for 1 correct answers without image/bitmap
    public WW1Quiz(int id, String question, ArrayList<Pair<Integer, String>> answers) {

        this.id = id;
        this.question = question;
        this.image = null;
        this.options = answers;
        this.correctAns = 1;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public ArrayList<Pair<Integer, String>> getAnswer() {
        return options;
    }

    public void setAnswer(ArrayList<Pair<Integer, String>> answers) {
        this.options = answers;
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
    /*
    * Here we create the different questions/quizzes for the different categories
    * It takes in a Context as uses that to find the drawable
    */
    //For the BuildUp Quiz
    public static ArrayList<WW1Quiz> getBuildUpQuiz(Context context){
        ArrayList<WW1Quiz> ret = new ArrayList<>();
        //Question 1
            //options for questions
        ArrayList<Pair<Integer, String>> options = new ArrayList<>();
        options.add(new Pair<>(0 , "Not me"));
        options.add(new Pair<>(0 , "Not me"));
        options.add(new Pair<>(1 , "But it's me Dio"));
        options.add(new Pair<>(0 , "Yahh no, not me"));

        //Optional photo
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
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Not me"));
        options.add(new Pair<>(1 , "HEYAAHHHH"));
        options.add(new Pair<>(0 , "Maaaan, I wish"));
        options.add(new Pair<>(1 , "YIP YIP MOTHERFUCKER"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Hey It's a Test Question", options, 2));

        return ret;

    }
    //For the 1914 Quiz
    public static ArrayList<WW1Quiz> get1914Quiz(Context context){
        return null;
    }
    //For the 1915 Quiz
    public static ArrayList<WW1Quiz> get1915Quiz(Context context){
        return null;
    }
    //For the 1916 Quiz
    public static ArrayList<WW1Quiz> get1916Quiz(Context context){
        return null;
    }
    //For the 1917 Quiz
    public static ArrayList<WW1Quiz> get1917Quiz(Context context){
        return null;
    }
    //For the 1918 Quiz
    static ArrayList<WW1Quiz> get1918Quiz(Context context){
        return null;
    }
    //For the Aftermath Quiz
    public static ArrayList<WW1Quiz> getAfterMathQuiz(Context context){
        return null;
    }

}
