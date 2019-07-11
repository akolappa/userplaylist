package com.coding.spring.userplaylist.services.impl;

import com.coding.spring.userplaylist.pojos.ArticlePojo;
import com.coding.spring.userplaylist.services.ArticleSearchService;
import com.coding.spring.userplaylist.utilities.ContentSearchConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleSearchServiceImpl implements ArticleSearchService {

    @Autowired
    ContentSearchConnector contentSearchConnector;

    @Override
    public List<ArticlePojo> searchArticleFromExternalService(String searchQuery) {
        contentSearchConnector.searchWithKeywords(searchQuery);
    }
}
