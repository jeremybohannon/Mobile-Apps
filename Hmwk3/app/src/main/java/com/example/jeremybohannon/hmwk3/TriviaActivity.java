package com.example.jeremybohannon.hmwk3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TriviaActivity extends AppCompatActivity {

    ImageView questionImage;
    TextView questionNum, timeLeft, questionText, optionOne, optionTwo, optionThree, optionFour;
    Button quitBtn, nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        questionImage = (ImageView) findViewById(R.id.questionImage);

        questionNum   = (TextView) findViewById(R.id.questionNum);
        timeLeft      = (TextView) findViewById(R.id.timeLeft);
        questionText  = (TextView) findViewById(R.id.questionText);
        optionOne     = (TextView) findViewById(R.id.optionOne);
        optionTwo     = (TextView) findViewById(R.id.optionTwo);
        optionThree   = (TextView) findViewById(R.id.optionThree);
        optionFour    = (TextView) findViewById(R.id.optionFour);

        quitBtn = (Button) findViewById(R.id.quitBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);
    }
}
