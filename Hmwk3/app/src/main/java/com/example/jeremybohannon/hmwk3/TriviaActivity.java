package com.example.jeremybohannon.hmwk3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity {

    private ImageView questionImage;
    private TextView questionNum, timeLeft, questionText;
    private RadioGroup answerChoiceGroup;
    private Button quitBtn, nextBtn;

    private ArrayList<Question> questionList = new ArrayList<>();
    private int questionIndex;
    private ArrayList<Integer> userAnswers = new ArrayList<>();

    ProgressBar progressBarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        questionImage = (ImageView) findViewById(R.id.questionImage);

        questionNum   = (TextView) findViewById(R.id.questionNum);
        timeLeft      = (TextView) findViewById(R.id.timeLeft);
        questionText  = (TextView) findViewById(R.id.questionText);

        answerChoiceGroup = (RadioGroup) findViewById(R.id.answerChoices);
        answerChoiceGroup.setOnCheckedChangeListener(answerChoiceChange);

        quitBtn = (Button) findViewById(R.id.quitBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setEnabled(false);
        quitBtn.setOnClickListener(quitOnClick);
        nextBtn.setOnClickListener(nextOnClick);

        progressBarImage = (ProgressBar) findViewById(R.id.progressBarImage);

        if (getIntent() != null && getIntent().getExtras() != null) {
            questionList = (ArrayList<Question>) getIntent().getExtras().getSerializable("EXTRA__QUESTIONS");

            questionIndex = 0;
            loadQuestionData(questionIndex);
        }
    }

    void setPhoto(Bitmap bitmap){
        questionImage.setImageBitmap(bitmap);
        progressBarImage.setVisibility(View.GONE);
        questionImage.setVisibility(View.VISIBLE);
        nextBtn.setEnabled(true);
    }

    private void loadQuestionData(int questionIndex) {
        Question currentQuestion = questionList.get(questionIndex);
        questionImage.setVisibility(View.GONE);
        progressBarImage.setVisibility(View.VISIBLE);
        nextBtn.setEnabled(false);
        new AsyncImage(this).execute(currentQuestion.getTriviaPhoto());

        questionNum.setText("" + questionIndex + 1);
        questionText.setText(currentQuestion.getTriviaQuestion());

        String[] answerChoices = currentQuestion.getAnswerChoices();

        answerChoiceGroup.setOrientation(LinearLayout.VERTICAL);
        answerChoiceGroup.removeAllViewsInLayout();

        for (int i = 0; i < answerChoices.length; i++) {
            if (answerChoices[i] != null) {
                Log.d("debug", answerChoices[i]);

                RadioButton answerChoice = new RadioButton(this);
                answerChoice.setId(questionIndex + i);
                answerChoice.setText(answerChoices[i]);
                answerChoiceGroup.addView(answerChoice);
            }
        }
    }

    RadioGroup.OnCheckedChangeListener answerChoiceChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
            RadioButton option = (RadioButton) findViewById(id);

            Question currentQuestion = questionList.get(questionIndex);
            String[] answerChoices = currentQuestion.getAnswerChoices();

            for (int i = 0; i < answerChoices.length; i++) {
                if (option.getText().equals(answerChoices[i])) {
                    if (questionIndex > userAnswers.size() - 1) {
                        userAnswers.add(i);
                    } else {
                        userAnswers.set(questionIndex, i);
                    }
                }
            }
        }
    };

    Button.OnClickListener quitOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    Button.OnClickListener nextOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (questionIndex == userAnswers.size()) {
                Toast.makeText(TriviaActivity.this, "Please select an answer choice.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (questionIndex != questionList.size() - 1) {
                questionIndex++;
                loadQuestionData(questionIndex);
            } else {
                Intent intent = new Intent(TriviaActivity.this, StatsActivity.class);
                intent.putExtra("USER_ANSWERS", userAnswers);
                startActivity(intent);
            }
        }
    };
}
