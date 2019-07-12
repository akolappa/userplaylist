package com.coding.spring.userplaylist.services.impl;

import com.coding.spring.userplaylist.pojos.ArticlePojo;
import com.coding.spring.userplaylist.services.ArticleSearchService;
import com.coding.spring.userplaylist.utilities.ContentSearchConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleSearchServiceImpl implements ArticleSearchService {

    private static final String ARTICLEID = "id";
    private static final String ARTICLENAME = "name";
    private static final String ARTISTNAME = "artistName";

    @Autowired
    ContentSearchConnector contentSearchConnector;

    @Override
    public List<ArticlePojo> searchArticleFromExternalService(String searchQuery) {
        return (List<ArticlePojo>)contentSearchConnector.searchWithKeywords(searchQuery)
                .orElseThrow(() ->
                        new IllegalStateException("Empty Result from Api"))
                .stream()
                .map(e -> new ArticlePojo(((LinkedHashMap) e).get(ARTICLEID).toString()
                        ,((LinkedHashMap) e).get(ARTICLENAME).toString(),
                        ((LinkedHashMap) e).get(ARTISTNAME).toString()))
                .collect(Collectors.toList());
    }
}
