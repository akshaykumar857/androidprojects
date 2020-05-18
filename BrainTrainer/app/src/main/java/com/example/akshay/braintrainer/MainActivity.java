package com.example.akshay.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView quesTextView;
    Button button;
    Button btn1;
    Button btn2;
    Button btn3;
    Button playAgain;
    Button btn4;
    int loccorrect;
    int noques=0;
    int score=0;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    public void goBtn(View view)
    {

        button.setVisibility(View.INVISIBLE);
        ConstraintLayout constraintL=(ConstraintLayout)findViewById(R.id.constraintL);
        constraintL.setVisibility(View.VISIBLE);
        play(constraintL);
    }
    public void play(View view)
    {
        timerTextView.setText("30s");
        playAgain.setVisibility(View.INVISIBLE);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        resultTextView.setVisibility(View.INVISIBLE);
        score=0;
        noques=0;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noques));
        CountDownTimer countDownTimer=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done");
                playAgain.setVisibility(View.VISIBLE);
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
            }
        }.start();
        question(resultTextView);
    }
    public void chooseAns(View view) {
        resultTextView.setVisibility(View.VISIBLE);
        if (Integer.toString(loccorrect).equals(view.getTag().toString()))
        {
            resultTextView.setText("Correct!");
            score++;
        }
        else
        {
            resultTextView.setText("Wrong!!");
        }
        noques++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noques));
        question(resultTextView);
    }
    public void question(View view)
    {

        Random rand=new Random();
        int a,b;
        a=rand.nextInt(21);
        b=rand.nextInt(21);
        ArrayList<Integer> answers=new ArrayList<Integer>();
        quesTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        int correctans,wrongans;
        correctans=a+b;
       // Log.i("correct",Integer.toString(correctans));
        loccorrect=rand.nextInt(4);
        for(int i=0;i<4;i++)
        {
            if(i==loccorrect)
            {
                answers.add(a+b);
            }
            else
            {
                wrongans=rand.nextInt(50);
                while(wrongans==a+b)
                {
                    wrongans=rand.nextInt(50);
                }
                answers.add(wrongans);
            }
        }
        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
        btn4.setText(Integer.toString(answers.get(3)));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        button.setVisibility(View.VISIBLE);
        quesTextView=(TextView)findViewById(R.id.quesTextView);
        resultTextView=(TextView)findViewById(R.id.resultTextView);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        playAgain=(Button)findViewById(R.id.playAgain);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
        btn4=(Button)findViewById(R.id.button4);
    }
}
