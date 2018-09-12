package com.caren.unobliviate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View questionSideView;
    private View answerSideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionSideView = findViewById(R.id.flashcard_question);
        answerSideView = findViewById(R.id.flashcard_answer);

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
    }
}
