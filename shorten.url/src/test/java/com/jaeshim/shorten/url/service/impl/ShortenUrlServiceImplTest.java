package com.jaeshim.shorten.url.service.impl;


import com.jaeshim.shorten.url.dto.request.ShortenUrlCreateRequestDto;
import com.jaeshim.shorten.url.dto.response.ShortenUrlCreateResponseDto;
import com.jaeshim.shorten.url.dto.response.ShortenUrlInformationDto;
import com.jaeshim.shorten.url.repository.ShortenUrlRepository;
import com.jaeshim.shorten.url.repository.impl.ShortenUrlRepositoryImpl;
import com.jaeshim.shorten.url.service.ShortenUrlService;
import com.jaeshim.shorten.url.util.impl.Base62ShortenUrlGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ShortenUrlServiceImplTest {


    static ShortenUrlService shortenUrlService;

    @BeforeAll
    static void init() {
        Base62ShortenUrlGenerator generator = new Base62ShortenUrlGenerator();
        ShortenUrlRepository shortenUrlRepository = new ShortenUrlRepositoryImpl();

        shortenUrlService = new ShortenUrlServiceImpl(generator, shortenUrlRepository);
    }

    @Test
    @DisplayName("단축 URL 생성을 검증한다")
    void createTest() {
        // given
        String originalUrl = "https://jaemni.tistory.com/entry/Windows-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-h2db-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0";
        ShortenUrlCreateRequestDto request = new ShortenUrlCreateRequestDto(originalUrl);

        // when
        ShortenUrlCreateResponseDto actual = shortenUrlService.createShortenUrl(request);

        // then
        assertThat(actual.getOriginalUrl()).isEqualTo(originalUrl);
        assertThat(actual.getShortenUrlKey().length()).isEqualTo(8);
    }

    @Test
    @DisplayName("리다이렉트 요청 시 정상적인 URL을 받아오는지 체크한다")
    void redirectTest() {
        // given
        String originalUrl = "https://jaemni.tistory.com/entry/Windows-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-h2db-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0";
        ShortenUrlCreateRequestDto request = new ShortenUrlCreateRequestDto(originalUrl);

        String shortenUrlKey = shortenUrlService.createShortenUrl(request).getShortenUrlKey();

        // when
        String actual = shortenUrlService.getRedirectUrl(shortenUrlKey);

        assertThat(actual).isEqualTo(originalUrl);
    }

    @Test
    @DisplayName("리다이렉트 요청 시 카운트가 정상적으로 증가하는지 확인한다")
    void redirectCountTest() {
        // given
        String originalUrl = "https://jaemni.tistory.com/entry/Windows-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-h2db-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0";
        ShortenUrlCreateRequestDto request = new ShortenUrlCreateRequestDto(originalUrl);

        String shortenUrlKey = shortenUrlService.createShortenUrl(request).getShortenUrlKey();

        // when
        shortenUrlService.getRedirectUrl(shortenUrlKey);
        shortenUrlService.getRedirectUrl(shortenUrlKey);
        shortenUrlService.getRedirectUrl(shortenUrlKey);

        ShortenUrlInformationDto actual = shortenUrlService.getShortenUrlInformation(shortenUrlKey);

        // then
        assertThat(actual.getRedirectCount()).isEqualTo(3);
    }
}