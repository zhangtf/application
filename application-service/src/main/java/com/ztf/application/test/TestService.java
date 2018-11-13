package com.ztf.application.test;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.ztf.application.model.User;
import com.ztf.application.page.PageParam;
import com.ztf.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/test")
public class TestService {


    private User getUser () {
        User user = new User();
        user.setAge(11);
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setId("test12345671");
        user.setUserName("test");
        return user;
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/list-user")
    public PageInfo<User> listUser () {
        IPage iPage = new PageParam(1, 3);
        return userService.listUser(iPage);
    }

    @RequestMapping("/insert")
    public User insert () {
        User user = getUser();
        int insert = userService.insert(user);
        return user;
    }
}
