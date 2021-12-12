package com.example.m3firstwork;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private Button getBtnGo;
    private EditText ed1, ed2;
    private static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        Glide.with(this).load("https://i.pinimg.com/474x/23/ab/a6/23aba60b66ef08174bb7455c4a8a2d2f.jpg").into(image);
        getBtnGo = findViewById(R.id.button);
        getBtnGo.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            ed1 = findViewById(R.id.editTextTextUsername);
            ed2 = findViewById(R.id.editTextTextPassword);
            if (ed1.getText().length() < 6 && ed2.getText().length() < 6) {
                Toast.makeText(getApplicationContext(), "Ошибка, ваш пароль или ваш логин слишком короткий", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(getApplicationContext(), "Подключение...",Toast.LENGTH_SHORT).show();
                intent.putExtra("keyUsername", ed1.getText().toString());
                intent.putExtra("keyPassword", ed2.getText().toString());
                startActivity(intent);
            }
        });
    }
}
