package com.jaeshim.product.management.entity.response;

import lombok.Getter;

@Getter
public enum ErrorCode {
    OK(0, "정상 응답"),
    ENTITY_NOT_FOUND(-1, "대상을 찾을 수 없음"),
    INVALID_ARGUMENT(-10, "잘못된 요청");

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}
