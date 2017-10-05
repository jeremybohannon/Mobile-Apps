package com.example.jeremybohannon.hmwk3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity {

    private ImageView questionImage;
    private TextView questionNum, timeLeft, questionText, option1, option2, option3, option4;
    private Button quitBtn, nextBtn;

    private ArrayList<Question> questionList = new ArrayList<>();
    private int questionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        questionImage = (ImageView) findViewById(R.id.questionImage);

        questionNum   = (TextView) findViewById(R.id.questionNum);
        timeLeft      = (TextView) findViewById(R.id.timeLeft);
        questionText  = (TextView) findViewById(R.id.questionText);
        option1       = (TextView) findViewById(R.id.optionOne);
        option2       = (TextView) findViewById(R.id.optionTwo);
        option3       = (TextView) findViewById(R.id.optionThree);
        option4       = (TextView) findViewById(R.id.optionFour);

        quitBtn = (Button) findViewById(R.id.exitBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);

        if (getIntent() != null && getIntent().getExtras() != null) {
            questionList = (ArrayList<Question>) getIntent().getExtras().getSerializable("EXTRA__QUESTIONS");

            questionIndex = 0;
            Question currentQuestion = questionList.get(questionIndex);

            Bitmap image = BitmapFactory.decodeFile(currentQuestion.getTriviaPhoto());
            questionImage.setImageBitmap(image);

            questionNum.setText("" + questionIndex + 1);
            questionText.setText(currentQuestion.getTriviaQuestion());

            String[] answerChoices = currentQuestion.getAnswerChoices();
            option1.setText(answerChoices[0]);
            option2.setText(answerChoices[1]);
            option3.setText(answerChoices[2]);
            option4.setText(answerChoices[3]);

            int correctAnswer = currentQuestion.getAnswerIndex();
        }
    }
}
