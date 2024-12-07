package com.jaeshim.shorten.url.service;

import com.jaeshim.shorten.url.dto.request.ShortenUrlCreateRequestDto;
import com.jaeshim.shorten.url.dto.response.ShortenUrlCreateResponseDto;
import com.jaeshim.shorten.url.dto.response.ShortenUrlInformationDto;

public interface ShortenUrlService {

    ShortenUrlCreateResponseDto createShortenUrl(ShortenUrlCreateRequestDto request);

    String getRedirectUrl(String shortenUrlKey);

    ShortenUrlInformationDto getShortenUrlInformation(String shortenUrlKey);

}
