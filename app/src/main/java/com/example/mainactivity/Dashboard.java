package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Medical search Engine");
    }
    public void btn_Symptoms(View view) {
        startActivity(new Intent(getApplicationContext(),Find_Symptoms.class));
    }
    public void btn_disease(View view) {
        startActivity(new Intent(getApplicationContext(),Find_Disease.class));
    }
}
