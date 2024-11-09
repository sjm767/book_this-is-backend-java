package com.jaeshim.product.management.service.impl;

import com.jaeshim.product.management.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ValidationServiceImpl implements ValidationService {

    @Override
    public <T> void checkValid(@Valid T validationTarget) {
        // do nothing
    }
}
