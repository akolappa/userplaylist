package com.coding.spring.userplaylist.repositories;

import com.coding.spring.userplaylist.entities.ArticlePlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticlePlaylistRepo extends JpaRepository<ArticlePlaylistEntity, Long> {

    void deleteByArticleIdAndPlaylistEntity_Id(String articleId, Long playlistId);

    Optional<ArticlePlaylistEntity> findByArticleIdAndPlaylistEntity_Id(String articleId, Long playlistId);

    Optional<List<ArticlePlaylistEntity>> findByPlaylistEntity_Id (Long playlistId);

}
