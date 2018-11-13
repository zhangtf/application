package com.ztf.application.controller;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.ztf.application.model.User;
import com.ztf.application.page.PageParam;
import com.ztf.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list-user")
    public PageInfo<User> listUser () {
        IPage iPage = new PageParam(1, 3);
        return userService.listUser(iPage);
    }
}
