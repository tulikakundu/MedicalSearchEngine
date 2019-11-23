package com.example.mainactivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class Signup_Form extends AppCompatActivity {

    EditText emailid,password,name,age,height,weight;
    Button btn;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Medical search Engine");
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailid.getText().toString();
                String pw = password.getText().toString();
                if (email.isEmpty())
                {
                    emailid.setError("Please Enter email-id first");
                    emailid.requestFocus();

                }
                else if (pw.isEmpty())
                {
                    password.setError("Please Enter Password first");
                    password.requestFocus();
                }
                else if (pw.isEmpty() && email.isEmpty())
                {
                    Toast.makeText(Signup_Form.this, "Please Enter both the Fields !!", Toast.LENGTH_SHORT).show();
                }
                else if (!(pw.isEmpty() && email.isEmpty()))
                {
                    //user is new and correctly entered both the fields
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful())
                            {
                                //task is not successful!
                                Toast.makeText(Signup_Form.this, "Sign-Up Failed !!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                startActivity(new Intent(Signup_Form.this,Dashboard.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Signup_Form.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
