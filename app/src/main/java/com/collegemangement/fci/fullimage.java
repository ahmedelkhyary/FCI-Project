package com.collegemangement.fci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class fullimage extends AppCompatActivity {
    ImageView imageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullimage);

        imageView = findViewById(R.id.image);

        Intent intent = getIntent();
         String image = intent.getStringExtra("image");

        try {
            Picasso.get().load(image).into(imageView);

        } catch (Exception e) {

        }
    }
}