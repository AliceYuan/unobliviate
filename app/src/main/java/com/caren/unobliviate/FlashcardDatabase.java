package com.caren.unobliviate;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class FlashcardDatabase {
    private final AppDatabase db;

    FlashcardDatabase(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "flashcard-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public List<Flashcard> getAllCards() {
        return db.flashcardDao().getAll();
    }

    public void insertCard(Flashcard flashcard) {
        db.flashcardDao().insertAll(flashcard);
    }

    public void deleteCard(Flashcard flashcard) {
        db.flashcardDao().delete(flashcard);
    }

    public void updateCard(Flashcard flashcard) {
        db.flashcardDao().update(flashcard);
    }
}