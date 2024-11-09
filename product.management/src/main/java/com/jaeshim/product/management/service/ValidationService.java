package com.jaeshim.product.management.service;

import jakarta.validation.Valid;

public interface ValidationService {
    <T> void checkValid(@Valid T validationTarget);
}
