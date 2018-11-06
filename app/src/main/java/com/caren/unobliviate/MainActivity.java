package com.caren.unobliviate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.plattysoft.leonids.ParticleSystem;

public class MainActivity extends AppCompatActivity {

    private View questionSideView;
    private View answerSideView;

    private View option1View;
    private View option2View;
    private View option3View;

    private ImageView toggleShowingAnswersIcon;

    private boolean isShowingAnswers = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionSideView = findViewById(R.id.flashcard_question);
        answerSideView = findViewById(R.id.flashcard_answer);
        option1View = findViewById(R.id.option_1);
        option2View = findViewById(R.id.option_2);
        option3View = findViewById(R.id.option_3);
        toggleShowingAnswersIcon = findViewById(R.id.toggle_choices_visibility);

        questionSideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionSideView.setVisibility(View.GONE);
                answerSideView.setVisibility(View.VISIBLE);
            }
        });

        answerSideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerSideView.setVisibility(View.GONE);
                questionSideView.setVisibility(View.VISIBLE);
            }
        });

        option1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option1View.setBackgroundColor(getResources().getColor(R.color
                        .wrong_answer_indication_color, null));
                option3View.setBackgroundColor(getResources().getColor(R.color
                        .right_answer_indication_color, null));
            }
        });

        option2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option2View.setBackgroundColor(getResources().getColor(R.color
                        .wrong_answer_indication_color, null));
                option3View.setBackgroundColor(getResources().getColor(R.color
                        .right_answer_indication_color, null));
            }
        });

        option3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option3View.setBackgroundColor(getResources().getColor(R.color
                        .right_answer_indication_color, null));

                new ParticleSystem(MainActivity.this, 100, R.drawable.confetti_pink, 3000)
                        .setSpeedRange(0.2f, 0.5f)
                        .oneShot(option3View, 100);
            }
        });

        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option1View.setBackgroundColor(getResources().getColor(R.color
                        .original_option_answer_color, null));
                option2View.setBackgroundColor(getResources().getColor(R.color
                        .original_option_answer_color, null));
                option3View.setBackgroundColor(getResources().getColor(R.color
                        .original_option_answer_color, null));
            }
        });

        toggleShowingAnswersIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowingAnswers) {
                    option1View.setVisibility(View.GONE);
                    option2View.setVisibility(View.GONE);
                    option3View.setVisibility(View.GONE);
                    toggleShowingAnswersIcon.setImageResource(R.drawable.show_icon);
                } else {
                    option1View.setVisibility(View.VISIBLE);
                    option2View.setVisibility(View.VISIBLE);
                    option3View.setVisibility(View.VISIBLE);
                    toggleShowingAnswersIcon.setImageResource(R.drawable.hide_icon);
                }

                isShowingAnswers = !isShowingAnswers;
            }
        });
    }
}
