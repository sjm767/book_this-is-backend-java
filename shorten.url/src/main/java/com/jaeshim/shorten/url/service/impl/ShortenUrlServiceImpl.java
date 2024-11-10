package com.jaeshim.shorten.url.service.impl;

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
        String shortenUrlKey = null;

        do {
            shortenUrlKey = shortenUrlGenerator.generateShortenUrl(orgUrl, SHORT_URL_LENGTH);
        }
        while (shortenUrlRepository.findShortenUrl(shortenUrlKey) != null);

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

        // 없으면 404로 보내기
        
        if (shortenUrl != null) {
           shortenUrl.increaseRedirectCount();
        }
        return shortenUrl.getOriginalUrl();
    }

    @Override
    public ShortenUrlInformationDto getShortenUrlInformation(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrl(shortenUrlKey);
        
        // 없으면 404로 보내기

        return ShortenUrlInformationDto.convertToDto(shortenUrl);
    }
}
