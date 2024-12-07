package com.jaeshim.shorten.url.controller;

import com.jaeshim.shorten.url.dto.request.ShortenUrlCreateRequestDto;
import com.jaeshim.shorten.url.dto.response.ShortenUrlCreateResponseDto;
import com.jaeshim.shorten.url.service.ShortenUrlService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ShortenUrlRestController.class)
public class ShortenUrlRestControllerTest {

    @MockBean
    private ShortenUrlService shortenUrlService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("원래의 URL로 리다이렉트 되어야한다")
    void redirectTest() throws Exception {
        // given
        String expect = "http://123123.com";

        // when
        when(shortenUrlService.getRedirectUrl(any())).thenReturn(expect);

        // then
        mockMvc.perform(get("/any-key"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", expect));
    }
}
