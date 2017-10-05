package com.example.jeremybohannon.hmwk3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
//Jeremy Bohannon Elizabeth Thompson
//StatsActivity.java
//Hmwk3
public class StatsActivity extends AppCompatActivity {

    TextView percentageViewStats, tryAgainView;
    ProgressBar progressBarStats;

    Button exitBtn, tryAgainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        tryAgainView = (TextView) findViewById(R.id.tryAgainView);
        tryAgainView.setText(R.string.tryAgain);
        int correctAnswers = 0;
        double totalQuestions = 16.0;
        double percentCorrect;

        if (getIntent() != null && getIntent().getExtras() != null) {
            correctAnswers = getIntent().getIntExtra("EXTRA_CORRECT", 0);
        }

        System.out.println("In stats.. correct answers: " + correctAnswers );

        percentCorrect = (correctAnswers / totalQuestions) * 100;
        percentCorrect = Math.floor(percentCorrect);

        percentageViewStats = (TextView) findViewById(R.id.percentageViewStats);
        progressBarStats = (ProgressBar) findViewById(R.id.progressBarStats);

        exitBtn = (Button) findViewById(R.id.exitBtn);
        tryAgainBtn = (Button) findViewById(R.id.tryAgainBtn);

        progressBarStats.setProgress(correctAnswers);
        percentageViewStats.setText(percentCorrect + "%");

        exitBtn.setOnClickListener(exitBtnListener);

        tryAgainBtn = (Button) findViewById(R.id.tryAgainBtn);
        tryAgainBtn.setOnClickListener(tryAgainListener);

        if(correctAnswers == totalQuestions) tryAgainView.setText("Congrats!");

    }

    Button.OnClickListener exitBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };



    Button.OnClickListener tryAgainListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent returnIntent = new Intent();
            setResult(TriviaActivity.RESULT_OK, returnIntent);
            finish();
        }
    };
}
