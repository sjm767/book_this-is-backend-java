package com.jaeshim.shorten.url.util.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class Base62ShortenUrlGeneratorTest {

    Base62ShortenUrlGenerator generator = new Base62ShortenUrlGenerator();

    @Test
    @DisplayName("지정된 자리 수 만큼의 단축 URL이 생성되는지 검증")

    void hasLengthEightTest() {
        // given
        String url = "https://jaemni.tistory.com/entry/Windows-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-h2db-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0";

        int actual = generator.generateShortenUrl(8).length();
        assertThat(actual).isEqualTo(8);
    }
}