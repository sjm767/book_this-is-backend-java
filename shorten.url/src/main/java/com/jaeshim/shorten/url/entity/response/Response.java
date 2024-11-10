package com.jaeshim.shorten.url.entity.response;

import com.jaeshim.shorten.url.entity.error.Error;
import lombok.Builder;

@Builder
public class Response<T> {
    private T data;
    private Error error;
}
