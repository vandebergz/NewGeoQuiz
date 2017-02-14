package com.example.evan.newgeoquiz;

/**
 * Created by Evan on 2/9/2017.
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private String mTrueAnswer;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public Question(int textResId, String answerTrue){
        mTextResId = textResId;
        mTrueAnswer = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public String isTrueAnswer() {
        return mTrueAnswer;
    }

    public void setTrueAnswer(String TrueAnswer) {
        this.mTrueAnswer = TrueAnswer;
    }
}
