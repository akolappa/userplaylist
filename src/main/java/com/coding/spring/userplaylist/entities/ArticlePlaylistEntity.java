package com.coding.spring.userplaylist.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "articleplaylist")
public class    ArticlePlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long apId;
    private String articleId;

    @ManyToOne
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PlaylistEntity playlistEntity;

   public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public PlaylistEntity getPlaylistEntity() {
        return playlistEntity;
    }

    public void setPlaylistEntity(PlaylistEntity playlistEntity) {
        this.playlistEntity = playlistEntity;
    }
}
