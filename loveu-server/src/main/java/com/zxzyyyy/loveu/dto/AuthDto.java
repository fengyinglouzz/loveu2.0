package com.zxzyyyy.loveu.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthDto {
    private String email;

    @NotNull(message="用户名不允许为空")
    private String username;

    @NotNull(message="密码不允许为空")
    private String password;}
