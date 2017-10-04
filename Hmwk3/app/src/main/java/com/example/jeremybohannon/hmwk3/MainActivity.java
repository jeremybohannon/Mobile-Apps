package com.example.jeremybohannon.hmwk3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView triviaImage;
    TextView statusView;
    Button exitBtn, startBtn;
    ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        triviaImage = (ImageView) findViewById(R.id.triviaImage);
        statusView = (TextView) findViewById(R.id.statusView);
        startBtn = (Button) findViewById(R.id.startBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(exitBtnListener);

        if(isConnected()) {
            new AsyncQuestion(this).execute("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");
        } else {
            Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    Button.OnClickListener exitBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    };

    public void setQuestions(String questions){
        progressBar.setVisibility(View.GONE);
        triviaImage.setVisibility(View.VISIBLE);
        statusView.setText(R.string.statusDone);
        startBtn.setEnabled(true);
        
        System.out.println(questions);


    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }
}
