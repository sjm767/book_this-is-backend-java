package com.jaeshim.shorten.url.repository.impl;

import com.jaeshim.shorten.url.entity.ShortenUrl;
import com.jaeshim.shorten.url.repository.ShortenUrlRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ShortenUrlRepositoryImpl implements ShortenUrlRepository {

    private final Map<String, ShortenUrl> shortenUrlMap = new ConcurrentHashMap<>();

    @Override
    public void saveUrl(ShortenUrl shortenUrl) {
        shortenUrlMap.put(shortenUrl.getShortenUrlKey(), shortenUrl);
    }

    @Override
    public ShortenUrl findRedirectUrl(String shortenUrlKey) {
        return null;
    }

    @Override
    public ShortenUrl findShortenUrl(String shortenUrlKey) {
        return shortenUrlMap.get(shortenUrlKey);
    }

    @Override
    public void updateShortenUrl(ShortenUrl shortenUrl) {
        shortenUrlMap.put(shortenUrl.getShortenUrlKey(), shortenUrl);
    }

    @Override
    public void deleteShortenUrl(String shortenUrlKey) {
        shortenUrlMap.remove(shortenUrlKey);
    }
}
