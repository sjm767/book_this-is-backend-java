package com.jaeshim.shorten.url.util.impl;

import com.jaeshim.shorten.url.util.ShortenUrlGenerator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class Base62ShortenUrlGenerator implements ShortenUrlGenerator {

    private static final String BASE62_KEY = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final AtomicLong counter = new AtomicLong(1);

    @Override
    // Base62 인코딩을 통한 단축 URL 생성
    public String generateShortenUrl(String url, int urlLength) {
        // 1. 고유 ID 생성
        long id = counter.getAndIncrement();

        // 2. 단축 URL 생성
        StringBuilder shortUrl = new StringBuilder();
        while (id > 0) {
            int remainder = (int) (id % 62);
            shortUrl.append(BASE62_KEY.charAt(remainder));
            id /= 62;
        }

        // 자리수를 맞추기 위해 길이가 부족하면 앞에 랜덤 문자 추가
        while (shortUrl.length() < urlLength) {
            shortUrl.append(BASE62_KEY.charAt((int)(Math.random() * 62)));
        }

        return shortUrl.reverse().toString(); // 뒤집어서 최종 문자열 반환
    }
}
