package com.jaeshim.springboot.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoParameterAjaxRestController {

    @GetMapping("/get-with-no-parameter")
    public String getWithNoParameter() {
        return "파라미터가 없는 GET 요청";
    }

}
