package com.example.grp20_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class WW1DisplayProfile extends AppCompatActivity {
    ImageView profilePhoto;
    /*
    When you click on your profile photo in the quiz you get here
    It just  simply displays some stats about your profile
    and gives you the option to delete it

     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ww1_user_profile);
        profilePhoto = findViewById(R.id.profilePhoto);

        Uri uri = Uri.parse(MainActivity.GLOBAL_PROFILE.getProfilePhotoString());
        //If you have a photo display it
        if(uri != null){
            Log.d("Uri" , uri.toString());
            profilePhoto.setImageURI(uri);
        }
    }
    public void DeleteProfile(View view){
        //Removes the file called "user_profile"
        File file = new File(getFilesDir(), "user_profile");
        boolean deleted = file.delete();
        if(deleted){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        //The Gallery photo will not be deleted until you either uninstall or overwrite it with a new photo

    }
}
