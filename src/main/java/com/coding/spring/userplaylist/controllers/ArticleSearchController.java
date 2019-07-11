package com.coding.spring.userplaylist.controllers;

import com.coding.spring.userplaylist.pojos.ArticlePojo;
import com.coding.spring.userplaylist.services.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleSearchController {

    @Autowired
    ArticleSearchService articleSearchService;

    @GetMapping(value = "/search/{query}")
    public List<ArticlePojo> searchArticle(@PathVariable("query") String searchQuery){
        return articleSearchService.searchArticleFromExternalService(searchQuery);
    }
}
