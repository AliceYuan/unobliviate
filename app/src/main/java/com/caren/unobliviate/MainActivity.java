package com.caren.unobliviate;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_CARD_REQUEST_CODE = 20;
    private View questionSideView;
    private View answerSideView;

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(this);

        allFlashcards = flashcardDatabase.getAllCards();

        questionSideView = findViewById(R.id.flashcard_question);
        answerSideView = findViewById(R.id.flashcard_answer);

        questionSideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);
                ObjectAnimator fadeAltAnim = ObjectAnimator.ofFloat(answerSideView, View.ALPHA, 0, 1);
                fadeAltAnim.setDuration(100);
                fadeAltAnim.start();
                fadeAltAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ScaleAnimation scal = new ScaleAnimation(1, 2, 1, 2, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
                        scal.setDuration(300);
                        scal.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                ScaleAnimation scal = new ScaleAnimation(2, 1, 2, 1, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
                                scal.setDuration(300);
                                answerSideView.startAnimation(scal);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        answerSideView.startAnimation(scal);
                    }
                });
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

                Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                rightInAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        questionSideView.setVisibility(View.VISIBLE);

                        // make sure we don't get an IndexOutOfBoundsError
                        if (nextCardNumberToDisplay >= allFlashcards.size()) {
                            nextCardNumberToDisplay = 0;
                        }

                        ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(nextCardNumberToDisplay).getQuestion());
                        ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(nextCardNumberToDisplay).getAnswer());

                        // advance our pointer index so we can show the next card when the button is clicked again
                        nextCardNumberToDisplay++;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        findViewById(R.id.timer).setVisibility(View.VISIBLE);
                        startTimer();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        questionSideView.setVisibility(View.INVISIBLE);
                        answerSideView.setVisibility(View.INVISIBLE);
                        findViewById(R.id.timer).setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        questionSideView.startAnimation(rightInAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                questionSideView.startAnimation(leftOutAnim);

                // show questions side first whenever next button is clicked
//                questionSideView.setVisibility(View.VISIBLE);
//                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
            }
        });

        countDownTimer = new CountDownTimer(16000, 1000) {

            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.timer)).setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {

            }
        };

        startTimer();
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

    CountDownTimer countDownTimer;

    private void startTimer() {
        countDownTimer.cancel();
        countDownTimer.start();

    }
}
