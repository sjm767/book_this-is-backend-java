package com.jaeshim.shorten.url.dto.response;

import com.jaeshim.shorten.url.entity.ShortenUrl;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShortenUrlInformationDto {

    private String originalUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public static ShortenUrlInformationDto convertToDto(ShortenUrl entity) {
        return ShortenUrlInformationDto.builder()
                .originalUrl(entity.getOriginalUrl())
                .shortenUrlKey(entity.getShortenUrlKey())
                .redirectCount(entity.getRedirectCount())
                .build();
    }
}
