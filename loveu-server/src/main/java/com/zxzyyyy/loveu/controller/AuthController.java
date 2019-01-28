package com.zxzyyyy.loveu.controller;

import com.zxzyyyy.loveu.dto.AuthDto;
import com.zxzyyyy.loveu.dto.AuthGroupDto;
import com.zxzyyyy.loveu.dto.EmailDto;
import com.zxzyyyy.loveu.entity.Auth;
import com.zxzyyyy.loveu.entity.Group;
import com.zxzyyyy.loveu.entity.Role;
import com.zxzyyyy.loveu.response.Data;
import com.zxzyyyy.loveu.response.ResultMap;
import com.zxzyyyy.loveu.service.AuthService;
import com.zxzyyyy.loveu.service.GroupService;
import com.zxzyyyy.loveu.service.RoleService;
import com.zxzyyyy.loveu.util.GenerateCode;
import com.zxzyyyy.loveu.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.zxzyyyy.loveu.response.ErrorCode.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;
    private final RoleService roleService;
    private final GroupService groupService;
    private final TokenUtils tokenUtils;

    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    public AuthController(AuthService authService, TokenUtils tokenUtils, RoleService roleService, GroupService groupService) {
        this.groupService = groupService;
        this.authService = authService;
        this.roleService = roleService;
        this.tokenUtils = tokenUtils;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultMap login(@Valid @RequestBody AuthDto authDto, BindingResult bindingResult) {
        // 检查有没有输入用户名密码和格式对不对
        if (bindingResult.hasErrors()) {
            return new ResultMap().fail("400").message(PARAM_WRONG).data("");
        }
        Auth auth = authService.findByUserName(authDto.getUsername());
        if (auth == null) {
            return new ResultMap().fail("434").message(NOT_HAS_ACCOUNT);
        } else if (!auth.getPassword().equals(authDto.getPassword())) {
            return new ResultMap().fail("438").message(WRONG_PASSWORD);
        }
        return new ResultMap().success().message("").data(new Data().addObj(tokenHeader, tokenUtils.generateToken(authDto)));
//        return new ResultMap().success().message("").data(new Data().addObj("username",tokenUtils.getUsernameFromToken(tokenUtils.generateToken(auth))));
    }

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public ResultMap signup(@Valid @RequestBody AuthDto authDto, BindingResult bindingResult) {
        Auth auth = new Auth();
        Role role = roleService.findById(2);
        Group group = groupService.findById(2);
        auth.setEmail(authDto.getEmail());
        auth.setRole(role);
        auth.setGroup(group);
        auth.setPassword(authDto.getPassword());
        auth.setUsername(authDto.getUsername());
        authService.add(auth);
        return new ResultMap().success().message("成功注册").data(new Data(auth).addObj(tokenHeader, tokenUtils.generateToken(authDto)));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultMap findUser(@RequestParam String username) {
        Auth auth = authService.findByUserName(username);
        if(auth == null) {
            return new ResultMap().fail("438").message(NOT_HAS_ACCOUNT);
        }
        return new ResultMap().success().message("查询成功").data(auth);
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public ResultMap joinGroup(@RequestBody AuthGroupDto authGroupDto) {
        Auth auth = authService.findByUserName(authGroupDto.getUsername());
        Group group = groupService.findByGroupname(authGroupDto.getGroupName());
        auth.setGroup(group);
        authService.add(auth);
        return new ResultMap().success().message("设置成功").data(auth);
    }
}
