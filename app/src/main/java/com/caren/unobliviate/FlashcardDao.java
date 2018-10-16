package com.caren.unobliviate;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FlashcardDao {
    @Query("SELECT * FROM flashcard")
    List<Flashcard> getAll();

    @Query("SELECT * FROM flashcard WHERE uid IN (:uid)")
    List<Flashcard> loadAllByIds(int[] uid);

    @Query(("SELECT * FROM flashcard"))
    List<Flashcard> loadAll();

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
//            + "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert
    void insertAll(Flashcard... flashcards);

    @Delete
    void delete(Flashcard flashcard);
}
