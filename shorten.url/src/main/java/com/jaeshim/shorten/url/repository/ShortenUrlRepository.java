package com.jaeshim.shorten.url.repository;

import com.jaeshim.shorten.url.entity.ShortenUrl;

public interface ShortenUrlRepository {

    void saveUrl(ShortenUrl shortenUrl);

    ShortenUrl findRedirectUrl(String shortenUrlKey);

    ShortenUrl findShortenUrl(String shortenUrlKey);

    void updateShortenUrl(ShortenUrl shortenUrl);

    void deleteShortenUrl(String shortenUrlKey);
}
