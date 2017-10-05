package com.example.jeremybohannon.hmwk3;

import android.content.Context;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView triviaImage;
    TextView statusView;
    Button exitBtn, startBtn;
    ArrayList<Question> questionList = new ArrayList<>();

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
        startBtn.setOnClickListener(startBtnListern);


        if(isConnected()) {
            System.out.println("[ MainActivity | onCreate ] Requesting data...");
            new AsyncQuestion(this).execute("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");
        } else {
            Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    Button.OnClickListener startBtnListern = new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            startTriviaIntent();
        }
    };

    Button.OnClickListener exitBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    };

    public void setQuestions(String questions){
        System.out.println("[ MainActivity | setQuestions ] Got questions... making objects..");
        String[] questionsArr = questions.split("\n");

        for (int i = 0; i <= questionsArr.length - 1; i++) {
            String[] question = questionsArr[i].split(";");
            String triviaQuestion = question[1];
            String triviaPhoto = question[2];
            String[] answerChoices = new String[question.length -2];
            System.arraycopy(question, 3, answerChoices, 0, question.length - 1 - 3);
            int answerIndex = Integer.parseInt(question[question.length - 1]);

            questionList.add(new Question(triviaQuestion, triviaPhoto, answerChoices, answerIndex));
        }

        progressBar.setVisibility(View.GONE);
        triviaImage.setVisibility(View.VISIBLE);
        statusView.setText(R.string.statusDone);
        startBtn.setEnabled(true);
    }

    public void startTriviaIntent(){
        Intent intent = new Intent(MainActivity.this, TriviaActivity.class);
        intent.putExtra("EXTRA__QUESTIONS", questionList);
        startActivity(intent);
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
