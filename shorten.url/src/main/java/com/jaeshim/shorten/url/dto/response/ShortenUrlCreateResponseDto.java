package com.jaeshim.shorten.url.dto.response;

import com.jaeshim.shorten.url.entity.ShortenUrl;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShortenUrlCreateResponseDto {
    private String originalUrl;
    private String shortenUrl;

    public static ShortenUrlCreateResponseDto convertToDto(ShortenUrl entity) {
        return ShortenUrlCreateResponseDto.builder()
                .originalUrl(entity.getOriginalUrl())
                .shortenUrl(entity.getShortenUrlKey())
                .build();
    }
}
