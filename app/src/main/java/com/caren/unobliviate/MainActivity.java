package com.caren.unobliviate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_CARD_REQUEST_CODE = 20;
    private View questionSideView;

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(this);

        allFlashcards = flashcardDatabase.getAllCards();

        questionSideView = findViewById(R.id.flashcard_question);

        questionSideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionSideView.setCameraDistance(1000);
                findViewById(R.id.flashcard_answer).setCameraDistance(1000);

                questionSideView.animate()
                        .rotationY(90)
                        .setDuration(200)
                        .withEndAction(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        questionSideView.setVisibility(View.INVISIBLE);
                                        findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                                        // second quarter turn
                                        findViewById(R.id.flashcard_answer).setRotationY(-90);
                                        findViewById(R.id.flashcard_answer).animate()
                                                .rotationY(0)
                                                .setDuration(200)
                                                .start();
                                    }
                                }
                        ).start();

//                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(findViewById(R.id.flashcard_question), "scaleX", 1f, 0f);
//                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(findViewById(R.id.flashcard_answer), "scaleX", 0f, 1f);
//                oa1.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
////                        imageView.setImageResource(R.drawable.frontSide);
//                        findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
//                        findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
//                        oa2.start();
//                    }
//                });
//
//                oa1.setDuration(250);
//                oa2.setDuration(250);
//                oa1.start();
            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(myIntent, ADD_CARD_REQUEST_CODE);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });


        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }

        findViewById(R.id.next_buton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show questions side first whenever next button is clicked
                questionSideView.setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);

                // make sure we don't get an IndexOutOfBoundsError
                if (nextCardNumberToDisplay >= allFlashcards.size()) {
                    nextCardNumberToDisplay = 0;
                }

                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(nextCardNumberToDisplay).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(nextCardNumberToDisplay).getAnswer());

                // advance our pointer index so we can show the next card when the button is clicked again
                nextCardNumberToDisplay++;


            }
        });
    }

    int nextCardNumberToDisplay = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_CARD_REQUEST_CODE) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");

            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);

            flashcardDatabase.insertCard(new Flashcard(question, answer));

            allFlashcards = flashcardDatabase.getAllCards();
            nextCardNumberToDisplay = allFlashcards.size();

        }
    }
}
