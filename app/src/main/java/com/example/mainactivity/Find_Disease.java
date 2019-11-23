package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.mainactivity.Questionbook.Symptoms;

public class Find_Disease extends AppCompatActivity {

    private TextView mQuestion;
    private ImageView mImageView;
    private Button mYesButton, mNoButton;
    private int mQuestionNumber =0;
    private int tempQuestionNumber=0;
    public static String[] results = new String[12];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__disease);

            mImageView = (ImageView)findViewById(R.id.imageview);
            mQuestion = (TextView)findViewById(R.id.question);
            mYesButton = (Button)findViewById(R.id.yesbtn);
            mNoButton = (Button)findViewById(R.id.nobtn);
            updateQuestion();

            mYesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tempQuestionNumber = mQuestionNumber-1;
                    results[tempQuestionNumber]=Questionbook.Symptoms[tempQuestionNumber];
                    //perform a check for last question
                    int a= mQuestionNumber;
                    String str=Integer.toString(a);

                    if(mQuestionNumber == Questionbook.questions.length){
                        printt();
                        startActivity(new Intent(getApplicationContext(),Result.class));
                        //Find_Disease.this.finish();
                    }
                    else
                    {
                        updateQuestion();printt();
                    }
                }
            });

            mNoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    results[mQuestionNumber-1]="HELLO";

                    if(mQuestionNumber == Questionbook.questions.length){
                        printt();
                        startActivity(new Intent(getApplicationContext(),Result.class));
                        //Find_Disease.this.finish();
                    }
                    else
                    {
                        updateQuestion();printt();
                    }

                }
            });







    }
    private void updateQuestion()
    {
        mImageView.setImageResource(Questionbook.images[mQuestionNumber]);
        mQuestion.setText(Questionbook.questions[mQuestionNumber]);
        mQuestionNumber++;
    }


    public void printt()
    {
        try
        {
            for(int i=0;i<results.length;i++)
            {
                Log.d("ff",results[i]);
            }
        }
        catch (Exception e)
        {
            System.out.println("erorro fvdfvdfvdfs");
        }

    }



}
