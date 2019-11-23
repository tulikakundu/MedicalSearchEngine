package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Dadi extends AppCompatActivity {

    TextView a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dadi);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String dadi1=extras.getString("dadi1");
        String dadi2=extras.getString("dadi2");
        a = findViewById(R.id.dadi11);
        b = findViewById(R.id.dadi22);
        a.setText(dadi1);
        b.setText(dadi2);

    }
}
