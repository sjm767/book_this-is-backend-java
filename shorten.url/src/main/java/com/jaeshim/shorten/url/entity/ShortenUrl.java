package com.jaeshim.shorten.url.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShortenUrl {
    private String originalUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public void increaseRedirectCount() {
        this.redirectCount = redirectCount + 1;
    }
}
