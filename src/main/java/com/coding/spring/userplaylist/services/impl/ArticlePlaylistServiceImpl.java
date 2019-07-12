package com.coding.spring.userplaylist.services.impl;

import com.coding.spring.userplaylist.entities.ArticlePlaylistEntity;
import com.coding.spring.userplaylist.pojos.ArticlePojo;
import com.coding.spring.userplaylist.repositories.ArticlePlaylistRepo;
import com.coding.spring.userplaylist.services.ArticlePlaylistService;
import com.coding.spring.userplaylist.utilities.ContentSearchConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticlePlaylistServiceImpl implements ArticlePlaylistService {

    @Autowired
    private ArticlePlaylistRepo articlePlaylistRepo;

    @Autowired
    private ContentSearchConnector connector;

    @Override
    public Boolean addArticleToPlayList(ArticlePlaylistEntity articlePlaylistEntity) {
         articlePlaylistRepo.save(articlePlaylistEntity);
         return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean removeArticleFromPlayList(String articleId, Long playlistId) {
        if(!this.findByArticleIdAndPlaylistId(articleId,playlistId).isPresent()){
            return Boolean.FALSE;
        }
        articlePlaylistRepo.deleteByArticleIdAndPlaylistEntity_Id(articleId,playlistId);
        return Boolean.TRUE;
    }

    @Override
    public Optional<ArticlePlaylistEntity> findByArticleIdAndPlaylistId(String articleId, Long playlistId){
        return articlePlaylistRepo.findByArticleIdAndPlaylistEntity_Id(articleId,playlistId);
    }

    @Override
    public List<ArticlePojo> getArticlesInUserPlaylist(Long playlistId) {
        List<String> articleIds = articlePlaylistRepo
                                    .findByPlaylistEntity_Id(playlistId)
                                    .orElseThrow(IllegalArgumentException::new)
                                    .stream()
                                    .map(e -> e.getArticleId())
                                    .collect(Collectors.toList());

        return enrichArticleInformation(articleIds);
    }

    private List<ArticlePojo> enrichArticleInformation(List<String> articleIds){
           return connector.getArticlesData(articleIds);
    }

}
