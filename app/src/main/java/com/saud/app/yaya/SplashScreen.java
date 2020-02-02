package com.saud.app.yaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;




public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //targeting the main activity
        startActivity(new Intent(this,ActivityMain.class));
        finish();
    }
}
