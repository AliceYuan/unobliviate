package com.caren.unobliviate;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizletApiResponseObject {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("term_count")
    @Expose
    private Integer termCount;
    @SerializedName("created_date")
    @Expose
    private Integer createdDate;
    @SerializedName("modified_date")
    @Expose
    private Integer modifiedDate;
    @SerializedName("has_images")
    @Expose
    private Boolean hasImages;
    @SerializedName("subjects")
    @Expose
    private List<String> subjects = null;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("editable")
    @Expose
    private String editable;
    @SerializedName("has_access")
    @Expose
    private Boolean hasAccess;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("lang_terms")
    @Expose
    private String langTerms;
    @SerializedName("lang_definitions")
    @Expose
    private String langDefinitions;
    @SerializedName("terms")
    @Expose
    private List<QuizletTermResponseObject> terms = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getTermCount() {
        return termCount;
    }

    public void setTermCount(Integer termCount) {
        this.termCount = termCount;
    }

    public Integer getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Integer modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getHasImages() {
        return hasImages;
    }

    public void setHasImages(Boolean hasImages) {
        this.hasImages = hasImages;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public Boolean getHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(Boolean hasAccess) {
        this.hasAccess = hasAccess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLangTerms() {
        return langTerms;
    }

    public void setLangTerms(String langTerms) {
        this.langTerms = langTerms;
    }

    public String getLangDefinitions() {
        return langDefinitions;
    }

    public void setLangDefinitions(String langDefinitions) {
        this.langDefinitions = langDefinitions;
    }

    public List<QuizletTermResponseObject> getTerms() {
        return terms;
    }

    public void setTerms(List<QuizletTermResponseObject> terms) {
        this.terms = terms;
    }
}
