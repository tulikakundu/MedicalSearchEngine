package com.example.mainactivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class Treatment extends AppCompatActivity {
    DatabaseReference dref;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);
       /* String dname=getIntent().getStringExtra("disease_name");
        if(dname == NULL)
        {
            System.out.println("HELLO HJIHA");
        }
        Log.d("dname os",dname);
        dref = FirebaseDatabase.getInstance().getReference().child("DISEASE").child(dname);

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String salt1=dataSnapshot.child("TREATMENT").child("SALT1").getValue().toString();
                String salt2=dataSnapshot.child("TREATMENT").child("SALT2").getValue().toString();
                String dadi1=dataSnapshot.child("TREATMENT").child("DADI1").getValue().toString();
                String dadi2=dataSnapshot.child("TREATMENT").child("DADI2").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



    }
}
