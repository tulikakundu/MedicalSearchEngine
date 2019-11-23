package com.example.mainactivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class Treatment extends AppCompatActivity {
    DatabaseReference dref;
    String salt1,salt2,dadi1,dadi2;
    Button btn;
    TextView a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);
        a=findViewById(R.id.salt11);
        b=findViewById(R.id.salt22);
        btn = findViewById(R.id.nxt);
        String dname=getIntent().getStringExtra("disease_name");
        //String employee=getIntent().getStringExtra("employee");
        if(dname == NULL)
        {
            //dname=employee;
        }
        Log.d("dname os",dname);
        dref = FirebaseDatabase.getInstance().getReference().child("DISEASE").child(dname);

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 salt1=dataSnapshot.child("TREATMENT").child("SALT1").getValue().toString();
                 salt2=dataSnapshot.child("TREATMENT").child("SALT2").getValue().toString();
                 a.setText(salt1);
                 b.setText(salt2);
                 dadi1=dataSnapshot.child("TREATMENT").child("DADI1").getValue().toString();
                 dadi2=dataSnapshot.child("TREATMENT").child("DADI2").getValue().toString();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Treatment.this,Dadi.class);
                Bundle extras =new Bundle();
                extras.putString("dadi1",dadi1);
                extras.putString("dadi2",dadi2);
                i.putExtras(extras);
                startActivity(i);
            }
        });



    }
}
