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
        options.add(new Pair<>(1 , "Austria-Hungary"));
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
        options.add(new Pair<>(1 , "Battle of hartmannswillerkopf"));
        options.add(new Pair<>(0 , "Battle of Bolimów"));
        options.add(new Pair<>(0 , "Battle of Jassim"));
        options.add(new Pair<>(0 , "Battle of Albert"));


        //Optional photo
        Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.hartmannswillerkopf);
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
                R.drawable.saaad);
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
                R.drawable.erzeroum);
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
                R.drawable.jansmuts);
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
