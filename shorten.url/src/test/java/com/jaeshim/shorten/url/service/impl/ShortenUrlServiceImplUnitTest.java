package com.jaeshim.shorten.url.service.impl;

import com.jaeshim.shorten.url.dto.request.ShortenUrlCreateRequestDto;
import com.jaeshim.shorten.url.entity.ShortenUrl;
import com.jaeshim.shorten.url.exception.LackOfShortenUrlKeyException;
import com.jaeshim.shorten.url.repository.ShortenUrlRepository;
import com.jaeshim.shorten.url.util.impl.Base62ShortenUrlGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShortenUrlServiceImplUnitTest {

    @Mock
    private ShortenUrlRepository shortenUrlRepository;

    @Mock
    private Base62ShortenUrlGenerator shortenUrlGenerator;

    @InjectMocks
    private ShortenUrlServiceImpl shortenUrlService;

    @Test
    @DisplayName("단축 URL이 계속 중복되면 LackOfShortenUrlKeyException 예외가 발생해야 한다")
    void throwLackOfShortenUrlKeyExceptionTest() {
        ShortenUrlCreateRequestDto request = new ShortenUrlCreateRequestDto("testURL");

        when(shortenUrlGenerator.generateShortenUrl(anyInt())).thenReturn("");
        when(shortenUrlRepository.findShortenUrl(any())).thenReturn(ShortenUrl.builder().build());

        assertThatThrownBy(() -> {
            shortenUrlService.createShortenUrl(request);
        }).isInstanceOf(LackOfShortenUrlKeyException.class);

    }

}