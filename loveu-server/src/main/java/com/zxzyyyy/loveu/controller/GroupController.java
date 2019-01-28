package com.zxzyyyy.loveu.controller;

import com.zxzyyyy.loveu.entity.Group;
import com.zxzyyyy.loveu.response.ResultMap;
import com.zxzyyyy.loveu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.zxzyyyy.loveu.response.ErrorCode.NO_GROUP;

@RestController
@RequestMapping(value = "/group")
public class GroupController {
    private final AuthService authService;

    @Autowired
    public GroupController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public ResultMap findGroupNameByUsername(@PathVariable String username) {
        Group group = authService.findByUserName(username).getGroup();
        if(group == null) {
            return new ResultMap().fail("001").message(NO_GROUP);
        }
        return new ResultMap().success().message("查询成功").data(group.getGroupName());
    }
}
