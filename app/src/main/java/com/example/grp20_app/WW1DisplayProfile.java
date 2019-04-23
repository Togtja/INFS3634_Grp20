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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ww1_user_profile);
        profilePhoto = findViewById(R.id.profilePhoto);
        Uri uri = Uri.parse(MainActivity.GLOBAL_PROFILE.getProfilePhotoString());
        if(uri != null){
            Log.d("Uri" , uri.toString());
            profilePhoto.setImageURI(uri);
        }
    }
    public void DeleteProfile(View view){
        File file = new File(getFilesDir(), "user_profile");
        boolean deleted = file.delete();
        if(deleted){
            Log.d("Deleted?", "YAY!!!");
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
