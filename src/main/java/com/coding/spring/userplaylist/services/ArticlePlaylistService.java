package com.coding.spring.userplaylist.services;

import com.coding.spring.userplaylist.entities.ArticlePlaylistEntity;
import com.coding.spring.userplaylist.pojos.ArticlePojo;

import java.util.List;
import java.util.Optional;

public interface ArticlePlaylistService {

    Boolean addArticleToPlayList(ArticlePlaylistEntity articlePlaylistEntity);
    Boolean removeArticleFromPlayList(String articleId, Long playlistId);
    Optional<ArticlePlaylistEntity> findByArticleIdAndPlaylistId(String articleId, Long playlistId);
    List<ArticlePojo> getArticlesInUserPlaylist(Long playlistId);
}
