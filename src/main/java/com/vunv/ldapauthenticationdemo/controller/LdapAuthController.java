package com.vunv.ldapauthenticationdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vu
 * @created 14/10/2024
 */
@RestController
public class LdapAuthController {
    @GetMapping("/")
    public String index() {
        return "Welcome to the home page!";
    }
}
