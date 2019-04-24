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
        options.add(new Pair<>(0 , "Joseph Stalin"));
        options.add(new Pair<>(0 , "Nicholas II"));
        options.add(new Pair<>(1 , "Franz Ferdinand"));
        options.add(new Pair<>(0 , "David Lloyd George"));

        //Optional photo
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.archyduke);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Who is the person that was killed in this event?", options, icon));

        //Question 2
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Moscow"));
        options.add(new Pair<>(0 , "Vienna"));
        options.add(new Pair<>(0 , "Belgrade"));
        options.add(new Pair<>(1 , "Sarajevo"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "In which city was Franz Ferdinand killed?", options));

        //Question 3
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Germany, Russia, Italy"));
        options.add(new Pair<>(1 , "France, Britain, Russia"));
        options.add(new Pair<>(0 , "France, Spain, Netherlands"));
        options.add(new Pair<>(0 , "Austria, Germany, Italy"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What three European countries signed an alliance called the Triple Entente?", options));

        //Question 4
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Germany"));
        options.add(new Pair<>(1 , "Hungary"));
        options.add(new Pair<>(0 , "France"));
        options.add(new Pair<>(0 , "Italy"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What country declared war on Serbia at the very start of World War I?", options));

        //Question 5
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(1 , "Europe"));
        options.add(new Pair<>(0 , "Africa"));
        options.add(new Pair<>(0 , "Asia"));
        options.add(new Pair<>(0 , "North America"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "World War I was mostly fought between the countries of what continent?", options));



        return ret;

    }
    //For the 1914 Quiz
    public static ArrayList<WW1Quiz> get1914Quiz(Context context){
        ArrayList<WW1Quiz> ret = new ArrayList<>();
        //Question 1
        //options for questions
        ArrayList<Pair<Integer, String>> options = new ArrayList<>();
        options.add(new Pair<>(0 , "Battle of Komarów"));
        options.add(new Pair<>(0 , "Battle of Albert"));
        options.add(new Pair<>(0 , "Battle of Gnila Lipa"));
        options.add(new Pair<>(1 , "Skirmish at Joncherey"));

        //Optional photo
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.joncherey);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What is the name of this event that happened on the border between Germany and France?", options, icon));

        //Question 2
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Skirmish at Joncherey"));
        options.add(new Pair<>(0 , "Siege of Antwerp"));
        options.add(new Pair<>(1 , "Battle of Halen"));
        options.add(new Pair<>(0 , "Battle of Albert"));


        //Optional photo
        Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.halen);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What is the name of this event?", options, icon1));


        //Question 3
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Battle of guise"));
        options.add(new Pair<>(1 , "First Battle of Ypres"));
        options.add(new Pair<>(0 , "Battle of Aisne"));
        options.add(new Pair<>(0 , "Siege of Antwerp"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Which battle was the end of the 'race to the sea'?", options));

        //Question 4
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "10000 people"));
        options.add(new Pair<>(1 , "6000 people"));
        options.add(new Pair<>(0 , "50000 people"));
        options.add(new Pair<>(0 , "20000 people"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "How many civilians were killed during Germany's invasion to Belgium?", options));

        //Question 5
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(1 , "Neutral"));
        options.add(new Pair<>(0 , "Germany and Austria"));
        options.add(new Pair<>(0 , "Britain and France"));
        options.add(new Pair<>(0 , "United States and Allies"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Which side was the Belgium on at the start of the war?", options));

        return ret;
    }
    //For the 1915 Quiz
    public static ArrayList<WW1Quiz> get1915Quiz(Context context){
        ArrayList<WW1Quiz> ret = new ArrayList<>();
        //Question 1
        //options for questions
        ArrayList<Pair<Integer, String>> options = new ArrayList<>();
        options.add(new Pair<>(1 , "Jassin"));
        options.add(new Pair<>(0 , "Zimbabwe"));
        options.add(new Pair<>(0 , "Ghana"));
        options.add(new Pair<>(0 , "South Africa"));

        //Optional photo
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.jassin);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Where was this battle took place?", options, icon));

        //Question 2
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(1 , "Battle of Hartmannswillerkopf"));
        options.add(new Pair<>(0 , "Battle of Bolimów"));
        options.add(new Pair<>(0 , "Battle of Jassim"));
        options.add(new Pair<>(0 , "Battle of Albert"));


        //Optional photo
        Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.Hartmannswillerkopf);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What is this memorial refer to? ", options, icon1));


        //Question 3
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Atlantic sea"));
        options.add(new Pair<>(1 , "North sea"));
        options.add(new Pair<>(0 , "Mediterranean sea "));
        options.add(new Pair<>(0 , "Pacific sea"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Where was Battle of Dogger Bank occurred?", options));

        //Question 4
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Derfflinger"));
        options.add(new Pair<>(1 , "Blücher"));
        options.add(new Pair<>(0 , "Moltke"));
        options.add(new Pair<>(0 , "Seydlitz"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Which Germany ship was sink by the British force?", options));

        //Question 5
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(1 , "Britain"));
        options.add(new Pair<>(0 , "Germany"));
        options.add(new Pair<>(0 , "France"));
        options.add(new Pair<>(0 , "United States"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Who won the Battle of Dogger Bank", options));

        return ret;
    }
    //For the 1916 Quiz
    public static ArrayList<WW1Quiz> get1916Quiz(Context context){
        ArrayList<WW1Quiz> ret = new ArrayList<>();
        //Question 1
        //options for questions
        ArrayList<Pair<Integer, String>> options = new ArrayList<>();
        options.add(new Pair<>(1 , "Austria-Hungary and Montenegro"));
        options.add(new Pair<>(0 , "Montenegro and Serbia"));
        options.add(new Pair<>(0 , "Bosnia and Serbia"));
        options.add(new Pair<>(0 , "Austria-Hungary and Albania"));


        //Actually adding the question
        ret.add(new WW1Quiz(0, "Who was involved on the Battle of Mojkovac?", options));

        //Question 2
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Battle of Dujaila"));
        options.add(new Pair<>(0 , "Battle of Hanna"));
        options.add(new Pair<>(0 , "Battle of Jassim"));
        options.add(new Pair<>(1 , "Battle of Sheikh Sa'ad"));


        //Optional photo
        Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.Saaad);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What is the name of this battle? ", options, icon1));


        //Question 3
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(1 , "Iraq"));
        options.add(new Pair<>(0 , "Iran"));
        options.add(new Pair<>(0 , "India"));
        options.add(new Pair<>(0 , "Turkey"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Where was Battle of Sheikh Sa'ad occurred?", options));

        //Question 4
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Battle of Aleppo"));
        options.add(new Pair<>(1 , "Erzurum Offensive"));
        options.add(new Pair<>(0 , "Battle of Transylvania"));
        options.add(new Pair<>(0 , "Monastir Offensive"));

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.Erzeroum);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What is the name of this battle?", options, icon));

        //Question 5
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(1 , "Russia"));
        options.add(new Pair<>(0 , "Germany"));
        options.add(new Pair<>(0 , "Ottoman"));
        options.add(new Pair<>(0 , "Britain"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Who won the Battle of Battle of Erzurum", options));

        return ret;    }
    //For the 1917 Quiz
    public static ArrayList<WW1Quiz> get1917Quiz(Context context){
        ArrayList<WW1Quiz> ret = new ArrayList<>();
        //Question 1
        //options for questions
        ArrayList<Pair<Integer, String>> options = new ArrayList<>();
        options.add(new Pair<>(1 , "Jan Smuts"));
        options.add(new Pair<>(0 , "Frederick Selous"));
        options.add(new Pair<>(0 , "Louis Botha"));
        options.add(new Pair<>(0 , "Paul Kruger"));

        //Optional photo
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.JanSmuts);
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Who is this general? He played an important role during the Battle of Behobeho", options, icon));

        //Question 2
        //options for questions
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "India-Pakistan border"));
        options.add(new Pair<>(0 , "Palestine-Israel border"));
        options.add(new Pair<>(1 , "Sinai–Palestine border"));
        options.add(new Pair<>(0 , "Iraq"));



        //Actually adding the question
        ret.add(new WW1Quiz(0, "Where was Battle of Rafa occurred? ", options));


        //Question 3
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Ottoman Empire"));
        options.add(new Pair<>(1 , "Britain"));
        options.add(new Pair<>(0 , "French"));
        options.add(new Pair<>(0 , "Germany"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Who won the Battle of Rafa?", options));

        //Question 4
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Morse Code"));
        options.add(new Pair<>(1 , "Zimmermann Telegram"));
        options.add(new Pair<>(0 , "Enigma Code"));
        options.add(new Pair<>(0 , "Playfair cipher"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "What was the name of a secret diplomatic communication issued from the German Foreign Office?", options));

        //Question 5
        //multiple correct answers
        options = new ArrayList<>();
        options.add(new Pair<>(0 , "Britain"));
        options.add(new Pair<>(1 , "Germany"));
        options.add(new Pair<>(0 , "France"));
        options.add(new Pair<>(0 , "United States"));
        //Actually adding the question
        ret.add(new WW1Quiz(0, "Who won the Battle of Nambanje", options));

        return ret;
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
