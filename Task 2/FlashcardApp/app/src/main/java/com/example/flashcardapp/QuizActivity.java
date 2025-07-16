package com.example.flashcardapp;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private View cardFront, cardBack;
    private boolean isBackVisible = false;
    private int currentQuestion = 0;
    private int score = 0;
    private List<Question> questions;
    private TextView questionFrontText, answerBackText;
    private Button optionA, optionB, optionC, optionD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questions = Question.getSampleQuestions();

        cardFront = findViewById(R.id.cardFront);
        cardBack = findViewById(R.id.cardBack);
        questionFrontText = findViewById(R.id.questionFrontText);
        answerBackText = findViewById(R.id.answerBackText);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);

        showQuestion();

        View.OnClickListener listener = v -> {
            Button clicked = (Button) v;
            String selected = clicked.getText().toString();

            // Check answer
            if (selected.equals(questions.get(currentQuestion).getAnswer())) {
                score++;
            }

            // Show answer on card back
            answerBackText.setText("Correct Answer:\n" + questions.get(currentQuestion).getAnswer());

            // Flip the card automatically
            flipCard();

            // Delay loading next question
            new Handler().postDelayed(() -> {
                currentQuestion++;
                if (currentQuestion < questions.size()) {
                    flipCard(); // Flip back
                    showQuestion();
                } else {
                    // Navigate to result screen
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }, 2000); // 2 seconds delay
        };

        optionA.setOnClickListener(listener);
        optionB.setOnClickListener(listener);
        optionC.setOnClickListener(listener);
        optionD.setOnClickListener(listener);
    }

    private void showQuestion() {
        Question q = questions.get(currentQuestion);
        questionFrontText.setText(q.getQuestion());
        optionA.setText(q.getOptions()[0]);
        optionB.setText(q.getOptions()[1]);
        optionC.setText(q.getOptions()[2]);
        optionD.setText(q.getOptions()[3]);
    }

    private void flipCard() {
        AnimatorSet setOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        AnimatorSet setIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);

        if (!isBackVisible) {
            setOut.setTarget(cardFront);
            setIn.setTarget(cardBack);
            cardFront.setVisibility(View.GONE);
            cardBack.setVisibility(View.VISIBLE);
            isBackVisible = true;
        } else {
            setOut.setTarget(cardBack);
            setIn.setTarget(cardFront);
            cardBack.setVisibility(View.GONE);
            cardFront.setVisibility(View.VISIBLE);
            isBackVisible = false;
        }

        setOut.start();
        setIn.start();
    }

}
