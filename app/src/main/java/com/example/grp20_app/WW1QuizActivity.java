package com.example.grp20_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
* The Quiz activity that start the a quiz fragment
* Both Main and QuizActivity both uses the same layout
* The only reason they are separate entities is so we can:
*   1. Save the progress in from a sensible place
*   2. To manage the back button, so it can't cheat the quiz (Main reason)
*/
public class WW1QuizActivity extends AppCompatActivity {
    private static View userView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintemp);
        LayoutInflater inflater = LayoutInflater.from(this);
        FrameLayout frame = findViewById(R.id.user_profile);
        userView = LayoutInflater.from(this).inflate(R.layout.ww1_user_stuff, frame, true);
        UserUpdate();
        WW1QuizListFragment quizListFragment = new WW1QuizListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.wikifrag, quizListFragment).addToBackStack("quiz_main")
                .commit();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            Log.d("Love", "Does this ever happen? ");
            FragmentManager fm = getSupportFragmentManager();
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
        }
        else {
            startActivity(new Intent(this, MainActivity.class));
        }



    }

    //If you click on your profile photo it opens some information about your profile
    public void OpenProfile(View view){
        startActivity(new Intent(this, WW1DisplayProfile.class));
    }
    @Override
    protected void onStop() {
        super.onStop();
        //save user data to file
        if(MainActivity.GLOBAL_PROFILE != null){
            MainActivity.GLOBAL_PROFILE.saveData(getApplicationContext());
        }

    }
    //This changes/updates the profile UI that lives in the quiz fragments
    //Remember to call this after any profile changes has been made
    public static void UserUpdate(){
        WW1UserProfile profile = MainActivity.GLOBAL_PROFILE;

        Log.d("Profile xp", String.valueOf(profile.getCurrXP()));
        //Username
        TextView name = userView.findViewById(R.id.userName);
        if(name == null){
            //We are calling this in the wrong layout
            //Bad Coding!!
            Log.d("BAD CODING!","We are being called in the wrong Layout");
            return;
        }
        name.setText(profile.getUserName());
        //Title
        TextView title = userView.findViewById(R.id.title);
        title.setText(profile.getTitle());
        //Lvl
        TextView level = userView.findViewById(R.id.user_level);
        level.setText("Level: " + profile.getLvl());
        //XP Text
        TextView xpText = userView.findViewById(R.id.text_XP);
        xpText.setText(profile.getCurrXP() + "/" + profile.getNextlvlXP() + " XP");
        //ProgressBarPercentage
        ProgressBar progressBar = userView.findViewById(R.id.progressBar);
        progressBar.setProgress(profile.getCurrXP());
        progressBar.setMax(profile.getNextlvlXP());
        //ProfilePhoto
        ImageView profilePhoto = userView.findViewById(R.id.profilePhoto);
        if(profile.getProfilePhotoString() != null){
            profilePhoto.setImageURI(Uri.parse(profile.getProfilePhotoString()));
        }
    }

}

