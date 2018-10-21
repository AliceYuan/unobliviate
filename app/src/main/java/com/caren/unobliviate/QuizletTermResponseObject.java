package com.caren.unobliviate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizletTermResponseObject {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("term")
    @Expose
    private String term;
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("image")
    @Expose
    private Object image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }
}
