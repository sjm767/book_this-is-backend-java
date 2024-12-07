package com.jaeshim.shorten.url.service.impl;

import com.jaeshim.shorten.url.exception.LackOfShortenUrlKeyException;
import com.jaeshim.shorten.url.exception.NotFoundShortenUrlException;
import com.jaeshim.shorten.url.dto.request.ShortenUrlCreateRequestDto;
import com.jaeshim.shorten.url.dto.response.ShortenUrlCreateResponseDto;
import com.jaeshim.shorten.url.dto.response.ShortenUrlInformationDto;
import com.jaeshim.shorten.url.entity.ShortenUrl;
import com.jaeshim.shorten.url.repository.ShortenUrlRepository;
import com.jaeshim.shorten.url.service.ShortenUrlService;
import com.jaeshim.shorten.url.util.impl.Base62ShortenUrlGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortenUrlServiceImpl implements ShortenUrlService {
    private static final int SHORT_URL_LENGTH = 8;

    private final Base62ShortenUrlGenerator shortenUrlGenerator;

    private final ShortenUrlRepository shortenUrlRepository;

    @Override
    public ShortenUrlCreateResponseDto createShortenUrl(ShortenUrlCreateRequestDto request) {
        String orgUrl = request.getOriginalUrl();
        String shortenUrlKey = getUniqueShortenUrlKey();

        ShortenUrl shortenUrl = ShortenUrl.builder()
                .originalUrl(orgUrl)
                .shortenUrlKey(shortenUrlKey)
                .redirectCount(0L)
                .build();

        shortenUrlRepository.saveUrl(shortenUrl);
        return ShortenUrlCreateResponseDto.convertToDto(shortenUrl);
    }

    @Override
    public String getRedirectUrl(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrl(shortenUrlKey);

        if (shortenUrl == null) {
            throw new NotFoundShortenUrlException();
        }

        shortenUrl.increaseRedirectCount();
        return shortenUrl.getOriginalUrl();
    }

    @Override
    public ShortenUrlInformationDto getShortenUrlInformation(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrl(shortenUrlKey);

        if (shortenUrl == null) {
            throw new NotFoundShortenUrlException();
        }

        return ShortenUrlInformationDto.convertToDto(shortenUrl);
    }

    private String getUniqueShortenUrlKey() {
        String shortenUrlKey = null;
        final int MAX_RETRY_COUNT = 5;
        int count = 0;

        while (count < MAX_RETRY_COUNT) {
            shortenUrlKey = shortenUrlGenerator.generateShortenUrl(SHORT_URL_LENGTH);

            if (shortenUrlRepository.findShortenUrl(shortenUrlKey) == null) {
                break;
            }
            count++;
        }

        if (count >= MAX_RETRY_COUNT) {
            throw new LackOfShortenUrlKeyException();
        }
        return shortenUrlKey;
    }
}
