package com.example.m3firstwork;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SecondActivity extends AppCompatActivity {
    private static final int REQUEST_TAKE_PHOTO = 10;
    private ImageView image;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button editImageBtn = findViewById(R.id.buttonEditImage);
        image = findViewById(R.id.imageProfile);
        Glide.with(this).load("https://i.pinimg.com/474x/23/ab/a6/23aba60b66ef08174bb7455c4a8a2d2f.jpg").into(image);

        String username = getIntent().getStringExtra("keyUsername");
        String password = getIntent().getStringExtra("keyPassword");

        TextView textViewU = findViewById(R.id.TextViewPersonName);
        TextView textViewP = findViewById(R.id.TextViewPassword);


        textViewU.setText(textViewU.getText().toString() + "" + username);
        textViewP.setText(textViewP.getText().toString() + "" + password);

        editImageBtn.setOnClickListener(view -> {

            final Intent galleryIntent = new Intent();
            galleryIntent.setType("image/*");
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            Intent chooser = new Intent(Intent.ACTION_CHOOSER);
            chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
            chooser.putExtra(Intent.EXTRA_TITLE, "Select from:");

            Intent[] intentArray = { cameraIntent };
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
            startActivityForResult(chooser, REQUEST_TAKE_PHOTO);

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap picturePhoto = (Bitmap) extras.get("data");
            Glide.with(this).load(picturePhoto).into(image);
        }
    }
}