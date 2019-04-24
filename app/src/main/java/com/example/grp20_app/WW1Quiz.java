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
    public static ArrayList<WW1Quiz> get1918Quiz(Context context){
        ArrayList<WW1Quiz> ret = new ArrayList<>();
        //Question 1
        //options for questions
        ArrayList<Pair<Integer, String>> options = new ArrayList<>();
        options.add(new Pair<>(0 , "14 principles to cripple the Central Powers to never be able to make war again"));
        options.add(new Pair<>(0 , "14 principles to help the European powers after The War"));
        options.add(new Pair<>(1 , "14 statement of principles for peace to be used under peace negotiations"));
        options.add(new Pair<>(0 , "14 statement for deploying US soldiers in Europe"));


        //Optional photo
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.points14);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What President Woodrow Wilson's Fourteen points about?", options, icon));

        //Question 2
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "The Peace Treaty Between The Central Powers and Russia"));
        options.add(new Pair<>(1 , "The Peace Treaty Between The Central Powers and Ukraine"));
        options.add(new Pair<>(0 , "The Peace Treaty Between The Central Powers and Belarus"));
        options.add(new Pair<>(0 , "The Peace Treaty Between The Central Powers and Poland"));


        //Optional photo
        icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.brestlit);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What was the Treaty of Brest-Litovsk (9 February 1918)?", options, icon));

        //Question 3
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(1 , "The Peace Treaty Between The Central Powers and Russia"));
        options.add(new Pair<>(0 , "The Peace Treaty Between The Central Powers and Ukraine"));
        options.add(new Pair<>(0 , "The Peace Treaty Between The Central Powers and Belarus"));
        options.add(new Pair<>(0 , "The Peace Treaty Between The Central Powers and Poland"));


        //Optional photo
        icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.brestlitrus);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What was the Treaty of Brest-Litovsk (3 March 1918)?", options, icon));

        //Question 4
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "An Offence made by the Allied Forces to capitulate The German Empire"));
        options.add(new Pair<>(0 , "An Offence made by the Unites States when they entered Europe"));
        options.add(new Pair<>(0 , "An Offence made by the German Empire to defeat The Russian Empire"));
        options.add(new Pair<>(1 , "An Offence made by the German Empire to get a favourable Armistice with the Allies"));

        //Actually adding the question
        ret.add(new WW1Quiz(0, "Who had the Spring Offensive and what was it's goal?", options));


        //Question 5
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(1 , "A rapid series of Allied victory, that ended with the capitulation of The German Empire"));
        options.add(new Pair<>(0 , "The last German attempt to defeat the Allies, that eventually failed"));
        options.add(new Pair<>(0 , "An Anzac offence to rapidly defeat the Ottomans"));
        options.add(new Pair<>(0 , "The series of attack Unites States had when entering Europe "));

        //Actually adding the question
        ret.add(new WW1Quiz(0, "What was the Hundred Days Offensive?", options));




        return ret;
    }
    //For the Aftermath Quiz
    public static ArrayList<WW1Quiz> getAfterMathQuiz(Context context){
        ArrayList<WW1Quiz> ret = new ArrayList<>();
        //Question 1
        //options for questions
        ArrayList<Pair<Integer, String>> options = new ArrayList<>();
        options.add(new Pair<>(0 , "Kingdom of Bosnians, Serbs and Croats"));
        options.add(new Pair<>(0 , "Kingdom of Bosnians, Croats and Slovenes"));
        options.add(new Pair<>(1 , "Kingdom of Serbs, Croats and Slovenes"));
        options.add(new Pair<>(0 , "Kingdom of Bosnians, Serbs and Slovenes"));

        //Optional photo
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.yugoslav);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "This Nation would later become Yugoslavia, but what was it called right after the war (Until 1929)?", options, icon));
        //Question 2
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "28 June 1919"));
        options.add(new Pair<>(1 , "18 January 1919"));
        options.add(new Pair<>(0 , "15 March 1919"));
        options.add(new Pair<>(0 , "11 November 1918"));

        icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.signingof_ppc);

        //Actually adding the question
        ret.add(new WW1Quiz(0, "When did the Paris Peace Conference begin? ", options, icon));


        //Question 3
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "27 Countries and Nationalities"));
        options.add(new Pair<>(0 , "22 Countries and Nationalities"));
        options.add(new Pair<>(0 , "36 Countries and Nationalities"));
        options.add(new Pair<>(1 , "32 Countries and Nationalities"));

        //Actually adding the question
        ret.add(new WW1Quiz(0, "How many Counties and Nationalities did the Paris Peace Conference involve?", options));

        //Question 4
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Paris Peace Treaties"));
        options.add(new Pair<>(0 , "Nuremberg Trials"));
        options.add(new Pair<>(1 , "Treaty of Versailles"));
        options.add(new Pair<>(0 , "Disbandment of the League of Nations"));

        //Actually adding the question
        ret.add(new WW1Quiz(0, "What was the main takeaway  from the Paris Peace Conference?", options));

        //Question 5
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(1 , "28 June 1919"));
        options.add(new Pair<>(0 , "18 January 1919"));
        options.add(new Pair<>(0 , "15 March 1919"));
        options.add(new Pair<>(0 , "11 November 1918"));

        //Actually adding the question
        ret.add(new WW1Quiz(0, "When was the Treaty of Versailles singed", options));



        return ret;
    }

}
