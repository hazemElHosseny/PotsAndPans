package com.example.zomy.potspans;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zomy.potspans.utils.DataClass;
import com.example.zomy.potspans.utils.Utilities;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPrefs = getSharedPreferences(Utilities.PREF_APP,MODE_PRIVATE);
        if(sharedPrefs.getInt(Utilities.PREF_SORT,0)==0){
            DataClass.getRecipes("1","r",SplashActivity.this);
        }else{
            DataClass.getRecipes("1","t",SplashActivity.this);
        }
    }
}
