package com.jaeshim.shorten.url.controller;

import com.jaeshim.shorten.url.dto.request.ShortenUrlCreateRequestDto;
import com.jaeshim.shorten.url.dto.response.ShortenUrlCreateResponseDto;
import com.jaeshim.shorten.url.dto.response.ShortenUrlInformationDto;
import com.jaeshim.shorten.url.service.ShortenUrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class ShortenUrlRestController {

    private final ShortenUrlService shortenUrlService;

    // 단축 URL 얻기
    @PostMapping("/shorten-url")
    public ResponseEntity<ShortenUrlCreateResponseDto> getShortenUrl(@Valid @RequestBody ShortenUrlCreateRequestDto request) {
        return new ResponseEntity<>(shortenUrlService.createShortenUrl(request), HttpStatus.OK);
    }

    // 리다이렉트 URL 얻기
    @GetMapping("/{shortenUrlKey}")
    public ResponseEntity<?> getRedirectUrl(@Valid @PathVariable String shortenUrlKey) throws URISyntaxException {
        String orgUrl = shortenUrlService.getRedirectUrl(shortenUrlKey);

        URI redirectUri = new URI(orgUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    // 정보 조회
    @GetMapping("/shorten-url/{shortenUrlKey}")
    public ResponseEntity<ShortenUrlInformationDto> getShortenUrlInfo(@Valid @PathVariable String shortenUrlKey) {
        ShortenUrlInformationDto info = shortenUrlService.getShortenUrlInformation(shortenUrlKey);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
