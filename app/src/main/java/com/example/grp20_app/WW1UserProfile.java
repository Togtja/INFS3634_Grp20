package com.example.grp20_app;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class WW1UserProfile implements Serializable {
    private String profilePhotoString;
    private String userName;
    private String title;
    private int lvl;
    private int currXP;
    private int highScore;
    private int nextlvlXP;

    WW1UserProfile(String userName, String uriString ) {
        this.userName = userName;
        this.profilePhotoString = uriString;
        this.lvl = 0;
        this.title = level.get(lvl);
        this.currXP = 0;
        this.nextlvlXP = 100;
        this.highScore = 0;

    }
    WW1UserProfile(String userName){
        this.userName = userName;
        this.profilePhotoString = null;
        this.lvl = 0;
        this.currXP = 0;
        this.nextlvlXP = 100;
        this.highScore = 0;

    }

    public String getProfilePhotoString() {
        return profilePhotoString;
    }

    public void setProfilePhotoString(String profilePhotoString) {
        this.profilePhotoString = profilePhotoString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLvl() {
        return lvl;
    }

    public void addLvl() {
        this.lvl++;
    }

    public int getCurrXP() {
        return currXP;
    }

    public void setCurrXP(int currXP) {
        this.currXP = currXP;
        if(this.currXP >= this.nextlvlXP){
            this.currXP -= this.nextlvlXP;
            this.addLvl();
            this.title = level.get(lvl);
            int xpNeeded = (int) (getLvl() * Math.log10(getLvl()+1) * 1000);
            //Round it to closest 100
            this.nextlvlXP = ((xpNeeded + 99) / 100 ) * 100;
            this.setCurrXP(this.currXP);//checks if you have leveled up twice
        }
    }
    public void addCurrXP(int addXP) {
        this.setCurrXP(this.currXP + addXP);


    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNextlvlXP() {
        return nextlvlXP;
    }

    public void setNextlvlXP(int nextlvlXP) {
        this.nextlvlXP = nextlvlXP;
    }

    public void saveData(Context context){
        try {
            FileOutputStream fos = context.openFileOutput("user_profile", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(this);
            os.close();
            fos.close();
        }
        catch (IOException e){

        }
    }
    public static WW1UserProfile getData(Context context){
        try {
            FileInputStream fis = context.openFileInput("user_profile");
            ObjectInputStream is = new ObjectInputStream(fis);
            WW1UserProfile profile = (WW1UserProfile) is.readObject();
            is.close();
            fis.close();
            return profile;
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
    private static ArrayList<String> level = new ArrayList<String>() {
        {
            add("Farmer");              //Level 0
            add("Private");             //Level 1
            add("Lance Corporal");      //Level 2
            add("Corporal");            //Level 3
            add("Sergeant");            //Level 4
            add("Second Lieutenant");   //Level 5
            add("Lieutenant");          //Level 6
            add("Captain");             //Level 7
            add("Major");               //Level 8
            add("Lieutenant-Colonel");  //Level 9
            add("Brigadier-General");   //Level 10
            add("Major-General");       //Level 11
            add("Lieutenant-General");  //Level 12
            add("General");             //Level 13
            add("Field-Marshal");       //Level 14
        }
    };

}
