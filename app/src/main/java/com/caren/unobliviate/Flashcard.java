package com.caren.unobliviate;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

@Entity
public class Flashcard {

    Flashcard(String question, String answer) {
        uid = UUID.randomUUID().toString();
        this.question = question;
        this.answer = answer;
    }

//    Flashcard(String question, String answer, String wrongAnswer1, String wrongAnswer2) {
//        uid = UUID.randomUUID().clockSequence();
//        this.question = question;
//        this.answer = answer;
//        this.wrongAnswer1 = wrongAnswer1;
//        this.wrongAnswer2 = wrongAnswer2;
//    }

    @PrimaryKey
    @NonNull
    private String uid;

    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "answer")
    private String answer;

    @Nullable
    @ColumnInfo(name = "wrong_answer_1")
    private String wrongAnswer1;

    @Nullable
    @ColumnInfo(name = "wrong_answer_2")
    private String wrongAnswer2;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Nullable
    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    @Nullable
    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }
}
