package com.coding.spring.userplaylist.utilities;

import com.coding.spring.userplaylist.pojos.ArticlePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContentSearchConnector {

    //TODO : expose the below values in the application property
    private String resourcePathSearch = "search";
    private String resourcePathArticle = "article/";
    private String host = "staging-gateway.mondiamedia.com";
    private String protocol = "https";
    private String apiVersion = "v1";
    private String basePath = "/api/content/";
    private String authorization = "Bearer Cc56cad42-0464-4e15-af88-b31e86c83e7b";
    private String gateWaykey = "G7947bedc-32d0-53fa-5e41-d9eac5316ac4";

    private static final String GATEWAYKEY = "X-MM-GATEWAY-KEY";

    @Autowired
    private RestTemplateBuilder restTemplate;

    public Optional<List> searchWithKeywords(String query){
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("q",query);
        return performRequest(HttpMethod.GET, buildConnectorUrl(queryParams,resourcePathSearch),getRequestEntity(),
                List.class);
    }

    public List<ArticlePojo> getArticlesData(List<String> articleIds){

        List<ArticlePojo> articleList = new ArrayList<>();
        articleIds.forEach(e -> {
            articleList.add(performRequest(HttpMethod.GET, buildConnectorUrl(null,resourcePathArticle+e),
                    getRequestEntity(),
                    ArticlePojo.class)
                    .orElseThrow(() -> new IllegalArgumentException()));
        });
        return articleList;
    }

    private String buildConnectorUrl(MultiValueMap<String, String> queryParams, String resourcePath){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
                .scheme(protocol)
                .host(host)
                .path(apiVersion)
                .path(basePath)
                .path(resourcePath);
        if(queryParams!=null && !queryParams.isEmpty()) {
            uriBuilder.queryParams(queryParams);
        }
        return uriBuilder.toUriString();
    }

    private HttpEntity<String> getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add(HttpHeaders.AUTHORIZATION,authorization);
        headers.add(GATEWAYKEY,gateWaykey);
        return new HttpEntity<>(headers);
    }

    private <T> Optional<T> performRequest(HttpMethod httpMethod,String url, HttpEntity<String> requestEntity ,
                                        Class<T> responseType){
        try{
            ResponseEntity<T> responseEntity = restTemplate.build().exchange(URI.create(url), httpMethod, requestEntity,
                    responseType);
            T responseBody = responseEntity.getBody();
            return Optional.ofNullable(responseBody);
        }catch (HttpClientErrorException ex){
            return Optional.empty();
        }
    }

}
