package com.example.lavapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
// import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    private MaterialButton firstButtonlavAapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstButtonlavAapp = findViewById(R.id.login_lavAapp);
        setup();
    }

    public void setup() {
        firstButtonlavAapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(next);
            }
        });

    }


}