package com.example.mainactivity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class Login_Form extends AppCompatActivity {


    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Medical search Engine");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser =mFirebaseAuth.getCurrentUser();
                // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                // If once logged in , for re-login
                if(mFirebaseUser!=null)
                {

                    Toast.makeText(Login_Form.this, "You're Logged-In !!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login_Form.this,Dashboard.class));
                }
                else
                {
                    Toast.makeText(Login_Form.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }


    public void btn_SignupForn(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
    private EditText email,password;


    public void btn_Login(View view) {
        email = (EditText)findViewById(R.id.email_btn);
        password = (EditText)findViewById(R.id.pass_btn);

        //email.setText("new@gmail.com");
        //password.setText("admin12345");
       // String em="new@gmail.com";
        //String pw="admin12345";
        String em=email.getText().toString();
        String pw=password.getText().toString();



        /*if(email.getText().toString().equals("admin@gmail.com") && password.getText().toString().equals("admin"))
        {




            startActivity(new Intent(getApplicationContext(),Dashboard.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"wrong emailid/password",Toast.LENGTH_SHORT).show();
        }*/
        mFirebaseAuth.signInWithEmailAndPassword(em,pw).addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    // sign in successful
                    Toast.makeText(Login_Form.this, "sign-in successful !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login_Form.this,Dashboard.class));
                }
                else
                {
                    Toast.makeText(Login_Form.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
