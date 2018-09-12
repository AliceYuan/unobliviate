package com.caren.unobliviate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View questionSideView;
    private View answerSideView;

    private View option1View;
    private View option2View;
    private View option3View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionSideView = findViewById(R.id.flashcard_question);
        answerSideView = findViewById(R.id.flashcard_answer);
        option1View = findViewById(R.id.option_1);
        option2View = findViewById(R.id.option_2);
        option3View = findViewById(R.id.option_3);

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
    }
}
