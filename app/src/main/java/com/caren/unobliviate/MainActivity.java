package com.caren.unobliviate;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

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

        findViewById(R.id.next_buton).setOnClickListener(new View.OnClickListener() {
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
        if (requestCode == ADD_CARD_REQUEST_CODE) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");

            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);

            allFlashcards = flashcardDatabase.getAllCards();
            currentCardDisplayedIndex = allFlashcards.size() - 1;

        }
    }
}
