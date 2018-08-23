package com.florianbennissi.myquizzapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button trueButton;
    Button falseButton;
    TextView questionTextView;
    int index;
    int question;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    int score;


    private TrueFalse[] questionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, false),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, false),
            new TrueFalse(R.string.question_5, false),
            new TrueFalse(R.string.question_6, true),
            new TrueFalse(R.string.question_7, false),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, true),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true),
            new TrueFalse(R.string.question_14, true),
            new TrueFalse(R.string.question_15, false),
            new TrueFalse(R.string.question_16, false),
            new TrueFalse(R.string.question_17, false),
            new TrueFalse(R.string.question_18, true),
            new TrueFalse(R.string.question_19, true),
            new TrueFalse(R.string.question_20, false),

    };

    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100f / questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null){
            score = savedInstanceState.getInt("ScoreKey");
            index = savedInstanceState.getInt("IndexKey");
        }else{
            score = 0;
            index = 0;

        }

        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        questionTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        question = questionBank[index].getQuestionId();
        questionTextView.setText(question);
        mScoreTextView.setText("Score "+score+ "/"+ questionBank.length);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                updateQuestion();


            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                updateQuestion();
            }
        });

    }

    private void updateQuestion() {
        index = ++index % questionBank.length;
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score "+score+ "/"+ questionBank.length);

        if (index == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You scored " + score + " points!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }


        question = questionBank[index].getQuestionId();
        questionTextView.setText(question);

    }
        private void checkAnswer(boolean userAnswer){
            boolean correctAnswer = questionBank[index].isAnswer();

            if (correctAnswer == userAnswer) {
                Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
                score += 1;
            } else {
                Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        }


    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", score);
        outState.putInt("IndexKey", index);
    }
    }

