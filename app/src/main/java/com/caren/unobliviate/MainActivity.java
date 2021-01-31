package com.caren.unobliviate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_CARD_REQUEST_CODE = 20;
    private static final int EDIT_CARD_REQUEST_CODE = 30;
    private TextView questionSideView;

    FlashcardDatabase flashcardDatabase;

    List<Flashcard> allFlashcards;

    Flashcard cardEditing;

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
                questionSideView.setVisibility(View.INVISIBLE);
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


        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show questions side first whenever next button is clicked
                questionSideView.setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);

                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                int randomCardToShowIndex = getRandomNumber(0, allFlashcards.size() - 1);

                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(randomCardToShowIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(randomCardToShowIndex).getAnswer());

            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make sure we show the question side of the card
                questionSideView.setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);

                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards(); // update our list of cards

                getAndSetCardNumberToDisplayAfterDeletion();

                // show the last card shown
                if (allFlashcards.size() > 0) {
                    Flashcard cardToDisplay = allFlashcards.get(currentCardDisplayedIndex);
                    ((TextView) findViewById(R.id.flashcard_question)).setText(cardToDisplay.getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(cardToDisplay.getAnswer());
                } else {
                    // empty state??
                }
            }
        });

        findViewById(R.id.editBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("question", questionSideView.getText().toString());
                intent.putExtra("answer", ((TextView) findViewById(R.id.flashcard_answer)).getText().toString());
                cardEditing = getCardByQuestion(questionSideView.getText().toString());
                MainActivity.this.startActivityForResult(intent, EDIT_CARD_REQUEST_CODE);
            }
        });
    }

    public Flashcard getCardByQuestion(String question) {
        for (Flashcard f : allFlashcards) {
            if (f.getQuestion().equals(question)) {
                return f;
            }
        }
        return null;
    }

    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }

    public int getAndSetCardNumberToDisplayAfterDeletion() {
        if (currentCardDisplayedIndex == 0) { // we deleted the first card in the set
            // do nothing! we still want to display the first card in the set
        } else {
            currentCardDisplayedIndex--; // we want to show the previous card in the set now
        }

        return currentCardDisplayedIndex;
    }

    int currentCardDisplayedIndex = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CARD_REQUEST_CODE && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");

            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);

            flashcardDatabase.insertCard(new Flashcard(question, answer));

            allFlashcards = flashcardDatabase.getAllCards();
            currentCardDisplayedIndex = allFlashcards.size() - 1;

        } else if (requestCode == EDIT_CARD_REQUEST_CODE && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);
            cardEditing.setQuestion(question);
            cardEditing.setAnswer(answer);
            flashcardDatabase.updateCard(cardEditing);
            allFlashcards = flashcardDatabase.getAllCards();
            currentCardDisplayedIndex = allFlashcards.size() - 1;
        }
    }
}
