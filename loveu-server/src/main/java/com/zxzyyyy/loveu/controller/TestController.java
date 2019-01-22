package com.zxzyyyy.loveu.controller;

import com.zxzyyyy.loveu.entity.Auth;
import com.zxzyyyy.loveu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/test")
public class TestController {

    private final AuthService authService;

    @Autowired
    public TestController(AuthService authService) {
        this.authService = authService;
    }


    @ResponseBody
    @RequestMapping(value = "/{x}", method = RequestMethod.GET)
    public Auth test(@PathVariable String x) {
        Auth auth = authService.findByUserName(x);
        return auth;
    }
}
