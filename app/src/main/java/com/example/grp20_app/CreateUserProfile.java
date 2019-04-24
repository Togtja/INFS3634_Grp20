package com.example.grp20_app;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/*
If the user have no profile (say first time starting the app)
He will be prompted to make one. hence this code
It Displayed the user_makeprofile layout
and ask the user for a name an optional photo
 */
public class CreateUserProfile extends AppCompatActivity {
    //Nr of photos needed from the gallery
    public static final int GET_FROM_GALLERY = 1;
    ImageButton imageButton;
    EditText text;
    Bitmap theImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_makeprofile);
        imageButton = findViewById(R.id.profileBtn);
        text = findViewById(R.id.profileName);
        theImage = null;


    }
    //We have a result from the gallery request
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData(); //Get the Uri from the gallery
            Bitmap bitmap;
            try {
                //Both get the bit map and set that as the image button photo
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageButton.setImageBitmap(bitmap);
                theImage = bitmap;
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    //the user clicked on the image and is now prompted to select a photo from his gallery
    public void getImage(View view){
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }
    public void submit(View view){

        //Save the photo you selected from gallery to our internal storage
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // Create imageDir
        File mypath = new File(cw.getDir("imageDir", Context.MODE_PRIVATE),"profile.jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            theImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Give the User_profile the path to the photo so we can call it in the future
        WW1UserProfile profile = new WW1UserProfile(text.getText().toString(), mypath.getAbsolutePath());
        profile.saveData(this); //We save the profile to internal storage
        //we start the main activity again, now with a profile on disk
        //startActivity(new Intent(this, MainActivity.class));
        this.finish();

    }

}
