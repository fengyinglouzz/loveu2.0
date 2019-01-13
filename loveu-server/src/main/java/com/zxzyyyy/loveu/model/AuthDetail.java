package com.zxzyyyy.loveu.model;

import lombok.Data;

/**
 * @Description:
 */
@Data
public class AuthDetail {
    private String username;
    private String password;
    private String authorities;
}
