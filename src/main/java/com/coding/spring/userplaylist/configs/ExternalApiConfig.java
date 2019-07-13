package com.coding.spring.userplaylist.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "externalapi")
public class ExternalApiConfig {

    private String resourcePathSearch;
    private String resourcePathArticle;
    private String host;
    private String protocol;
    private String apiVersion;
    private String basePath;
    private String authorization;
    private String gateWaykey;

    public String getResourcePathSearch() {
        return resourcePathSearch;
    }

    public void setResourcePathSearch(String resourcePathSearch) {
        this.resourcePathSearch = resourcePathSearch;
    }

    public String getResourcePathArticle() {
        return resourcePathArticle;
    }

    public void setResourcePathArticle(String resourcePathArticle) {
        this.resourcePathArticle = resourcePathArticle;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getGateWaykey() {
        return gateWaykey;
    }

    public void setGateWaykey(String gateWaykey) {
        this.gateWaykey = gateWaykey;
    }
}
