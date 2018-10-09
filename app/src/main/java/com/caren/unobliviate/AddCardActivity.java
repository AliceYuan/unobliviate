package com.caren.unobliviate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");

        ((EditText) findViewById(R.id.questionTextField)).setText(question);
        ((EditText) findViewById(R.id.answerTextField)).setText(answer);

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredQuestion = ((EditText) findViewById(R.id.questionTextField)).getText().toString();
                String enteredAnswer = ((EditText) findViewById(R.id.answerTextField)).getText().toString();

                if (enteredAnswer.isEmpty() || enteredQuestion.isEmpty()) {
                    Toast.makeText(AddCardActivity.this,
                            "Must enter both Question and Answer!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                Intent data = new Intent();
                // Pass relevant data back as a result
                data.putExtra("question", enteredQuestion);
                data.putExtra("answer", enteredAnswer);
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes the activity, pass data to parent
            }
        });
    }
}
