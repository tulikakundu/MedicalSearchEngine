package com.example.mainactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class Find_Symptoms extends AppCompatActivity
{
    Button btn;
    String Disease_name;
    private static final String[] Disease = new String[]{
            "MALARIA","DENGUE","TUBERCULOSIS","COMMON COLD","DIARRHOEA","ASTHMA","TYPHOID","CHICKEN POX","DIABETES","THYROID","ECZEMA","PULMONITIS"
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__symptoms);
        btn =(Button) findViewById(R.id.submitbtn);

        final AutoCompleteTextView editTextt = findViewById(R.id.searchbar);
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.drop_down,Disease);
        editTextt.setAdapter(adapter);



            editTextt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override

                public void onItemClick(AdapterView<?> parent, View view, int Position, long l) {
                     Disease_name  = parent.getItemAtPosition(Position).toString().toUpperCase();


                    //startActivity(new Intent(Find_Symptoms.this,Treatment.class));
                }
            });

            editTextt.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    editTextt.showDropDown();
                    editTextt.requestFocus();
                    return false;
                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(Find_Symptoms.this,Treatment.class);
                    i.putExtra("disease_name",Disease_name);
                    startActivity(i);
                }
            });




        }



        //TODO !!!!!!! Give string  in Caps
       /* String dname="TUBERCULOSIS";
        Intent i =new Intent(Find_Symptoms.this,Treatment.class);
        i.putExtra("disease_name",dname);
        startActivity(i);*/



    }

