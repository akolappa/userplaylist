package com.coding.spring.userplaylist.utilities;

import com.coding.spring.userplaylist.configs.ExternalApiConfig;
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

    private static final String GATEWAYKEY = "X-MM-GATEWAY-KEY";

    @Autowired
    private ExternalApiConfig apiConfig;

    @Autowired
    private RestTemplateBuilder restTemplate;

    //calls the external search api with required parameters
    public Optional<List> searchWithKeywords(String query){
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("q",query);
        return performRequest(HttpMethod.GET, buildConnectorUrl(queryParams,apiConfig.getResourcePathSearch()),getRequestEntity(),
                List.class);
    }
    //calls the external api for article data with required parameters
    public List<ArticlePojo> getArticlesData(List<String> articleIds){

        List<ArticlePojo> articleList = new ArrayList<>();
        articleIds.forEach(e -> {
            articleList.add(performRequest(HttpMethod.GET, buildConnectorUrl(null,apiConfig.getResourcePathArticle()+e),
                    getRequestEntity(),
                    ArticlePojo.class)
                    .orElseThrow(() -> new IllegalArgumentException("Empty/Error Response from Api")));
        });
        return articleList;
    }

    private String buildConnectorUrl(MultiValueMap<String, String> queryParams, String resourcePath){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
                .scheme(apiConfig.getProtocol())
                .host(apiConfig.getHost())
                .path(apiConfig.getApiVersion())
                .path(apiConfig.getBasePath())
                .path(resourcePath);
        if(queryParams!=null && !queryParams.isEmpty()) {
            uriBuilder.queryParams(queryParams);
        }
        return uriBuilder.toUriString();
    }

    private HttpEntity<String> getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setBearerAuth(apiConfig.getAuthorization());
        headers.add(GATEWAYKEY,apiConfig.getGateWaykey());
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
