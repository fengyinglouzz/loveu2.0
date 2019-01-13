package com.zxzyyyy.loveu.controller;

import com.zxzyyyy.loveu.dto.AuthDto;
import com.zxzyyyy.loveu.entity.Auth;
import com.zxzyyyy.loveu.response.Data;
import com.zxzyyyy.loveu.response.ResultMap;
import com.zxzyyyy.loveu.service.AuthService;
import com.zxzyyyy.loveu.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.zxzyyyy.loveu.response.ErrorCode.NOT_HAS_ACCOUNT;
import static com.zxzyyyy.loveu.response.ErrorCode.WRONG_PASSWORD;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenUtils tokenUtils;

    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    public AuthController(AuthService authService, TokenUtils tokenUtils) {

        this.authService = authService;
        this.tokenUtils = tokenUtils;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultMap login(@Valid @RequestBody AuthDto authDto, BindingResult bindingResult) {
        // 检查有没有输入用户名密码和格式对不对
        if (bindingResult.hasErrors()){
            return new ResultMap().fail("400").message("缺少参数或者参数格式不对").data("");
        }
        Auth auth = authService.findByUserName(authDto.getUsername());
        if (auth == null) {
            return new ResultMap().fail("434").message(NOT_HAS_ACCOUNT);
        }
        else if (!auth.getPassword().equals(authDto.getPassword())) {
            return new ResultMap().fail("438").message(WRONG_PASSWORD);
        }
        return new ResultMap().success().message("").data(new Data().addObj(tokenHeader, tokenUtils.generateToken(authDto)));
//        return new ResultMap().success().message("").data(new Data().addObj("username",tokenUtils.getUsernameFromToken(tokenUtils.generateToken(auth))));
    }
}
