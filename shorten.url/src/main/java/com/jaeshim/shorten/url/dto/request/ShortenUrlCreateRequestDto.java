package com.jaeshim.shorten.url.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShortenUrlCreateRequestDto {

    @NotBlank
//    @URL(regexp = "^(https?):\\/\\/(www\\.)?([a-zA-Z0-9-]{2,256}\\.[a-z]{2,6})(:[0-9]{1,5})?(\\/[^\\s]*)?$\n")
    private final String originalUrl;

    @JsonCreator
    public ShortenUrlCreateRequestDto(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
