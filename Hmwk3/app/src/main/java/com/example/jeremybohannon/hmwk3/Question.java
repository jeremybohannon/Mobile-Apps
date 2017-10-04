package com.example.jeremybohannon.hmwk3;

import java.io.Serializable;

/**
 * Created by jeremybohannon on 10/4/17.
 */

public class Question implements Serializable {
    String triviaQuestion;
    String triviaPhoto;
    String[] answerChoices;
    int answerIndex;

    public Question(String triviaQuestion, String triviaPhoto, String[] answerChoices, int answerIndex) {
        this.triviaQuestion = triviaQuestion;
        this.triviaPhoto = triviaPhoto;
        this.answerChoices = answerChoices;
        this.answerIndex = answerIndex;
    }

    public String getTriviaQuestion() {
        return triviaQuestion;
    }

    public void setTriviaQuestion(String triviaQuestion) {
        this.triviaQuestion = triviaQuestion;
    }

    public String getTriviaPhoto() {
        return triviaPhoto;
    }

    public void setTriviaPhoto(String triviaPhoto) {
        this.triviaPhoto = triviaPhoto;
    }

    public String[] getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(String[] answerChoices) {
        this.answerChoices = answerChoices;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }
}
