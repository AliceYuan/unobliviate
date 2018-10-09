package com.caren.unobliviate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_CARD_REQUEST_CODE = 20;
    private TextView questionSideView;
    private TextView answerSideView;

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
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(myIntent, ADD_CARD_REQUEST_CODE);
            }
        });

        findViewById(R.id.editBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("question", questionSideView.getText().toString());
                intent.putExtra("answer", answerSideView.getText().toString());
                MainActivity.this.startActivityForResult(intent, ADD_CARD_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_CARD_REQUEST_CODE && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");

            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);
        }
    }
}
