package com.example.lavapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG_MAIN = "_WelcomeActivity_";
    /*Intent intent = getIntent();
    String position = intent.getStringExtra("position");*/
    BottomNavigationView navigationView;
    NavHostFragment navHostFragment;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
        setup();
        // setup();
    }

    private void setup() {
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void init(){
        navigationView = findViewById(R.id.bottom_nav_view);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
    }

    /*private void setup() {
        Bundle bundle = new Bundle();
        bundle.putString("position", position);

// Set Fragmentclass Arguments
        FormServicioFragment formServicioFragment = new FormServicioFragment();
        formServicioFragment.setArguments(bundle);
    }*/
}