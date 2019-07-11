package com.coding.spring.userplaylist.utilities;

import com.coding.spring.userplaylist.pojos.ArticleSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ContentSearchConnector {

    //TODO : expose the below values in the application property
    private String resourcePath = "search";
    private String host = "staging-gateway.mondiamedia.com  ";
    private String protocol = "https";
    private String apiVersion = "v1";
    private String basePath = "api/content";
    private String authorization = "Bearer C8a5e6dde-2d2e-4ecf-a74c-e478825db7b3";
    private String gateWaykey = "G7947bedc-32d0-53fa-5e41-d9eac5316ac4";

    private static final String GATEWAYKEY = "X-MM-GATEWAY-KEY";

    @Autowired
    private RestTemplateBuilder restTemplate;

    public void searchWithKeywords(String query){
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("q",query);
        performRequest(HttpMethod.GET, buildConnectorUrl(queryParams),getRequestEntity(),
                List.class);
    }

    private String buildConnectorUrl(MultiValueMap<String, String> queryParams){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
                .scheme(protocol)
                .host(host)
                .path(apiVersion)
                .path(basePath)
                .path(resourcePath);

        uriBuilder.queryParams(queryParams);
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
