package com.example.evan.newgeoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;
    private EditText mEssayAnswer;
    private Button mSubmitButton;
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private Question[] mEssayQuestionBank = new Question[] {
            new Question(R.string.question_oceans, "Benar"),
            new Question(R.string.question_mideast, "Salah"),
            new Question(R.string.question_africa, "Salah"),
            new Question(R.string.question_americas, "Benar"),
            new Question(R.string.question_asia, "Benar"),
    };
    private int mCurrentIndex = 0;
    private String essayAnswer = "";

    private static final String TAG = "QuizActivity"; //for LOG.d
    private static final String KEY_INDEX = "index"; //for onSavedInstanceState

    //THIS METHOD IS USED TO UPDATE QUESTION DEPENDS ON THE INDEX VALUE
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    //THIS METHOD IS USED TO UPDATE ESSAY QUESTION DEPENDS ON THE INDEX VALUE
    private void updateEssayQuestion(){
        int question = mEssayQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    //THIS METHOD CHECKS WHETHER USER INPUT IS TRUE OR FALSE
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    //THIS METHOD CHECKS WHETHER ANSWER IS TRUE OR FALSE
    private void checkEssayAnswer(String userAnswer){
        String answerIsTrue = mEssayQuestionBank[mCurrentIndex].isTrueAnswer();
        int messageResId = 0;
        if (userAnswer.equalsIgnoreCase(answerIsTrue)){
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    //THIS IS THE MAIN METHOD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        //THIS IS FOR TRUE OR FALSE QUESTIONS
        /*mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });*/

        //THIS IS FOR ESSAY QUESTIONS
        mEssayAnswer = (EditText) findViewById(R.id.submit_answer);


        mSubmitButton = (Button) findViewById(R.id.submit_button);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                essayAnswer = mEssayAnswer.getText().toString();
                checkEssayAnswer(essayAnswer);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mPrevButton = (Button) findViewById(R.id.prev_button);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mEssayQuestionBank.length;
                //updateQuestion(); INACTIVATED FOR ESSAY MODE
                updateEssayQuestion();
            } });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) % mEssayQuestionBank.length;
                if (mCurrentIndex < 0) mCurrentIndex = mEssayQuestionBank.length-1;
                //updateQuestion(); INACTIVATED FOR ESSAY MODE
                updateEssayQuestion();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
}
