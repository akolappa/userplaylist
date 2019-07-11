package com.coding.spring.userplaylist.services;

import com.coding.spring.userplaylist.pojos.ArticlePojo;

import java.util.List;

public interface ArticleSearchService {

    List<ArticlePojo> searchArticleFromExternalService(String searchQuery);
}
