package com.coding.spring.userplaylist.pojos;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticlePojo {

    @JsonProperty("id")
    private String articleId;
    @JsonProperty("name")
    private String articleName;
    @JsonProperty("artistName")
    private String artistName;
    @JsonProperty("duration")
    private String duration;

    public ArticlePojo(String articleId, String articleName, String artistName) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.artistName = artistName;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
