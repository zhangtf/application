package com.ztf.application.service.impl;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztf.application.dao.UserMapper;
import com.ztf.application.model.User;
import com.ztf.application.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> listUser(IPage iPage) {
        logger.info("开始查询User列表");
        PageInfo<User> pageInfo = PageHelper.startPage(iPage).doSelectPageInfo(() -> userMapper.listUser());
        logger.error("结束查询User列表, 假如error");
        return pageInfo;
    }

    @Override
    public int insert(User user) {
        logger.error("开始保存user，假如异常日志");
        logger.error("结束保存user，假如异常日志");
        return userMapper.insert(user);
    }

}
