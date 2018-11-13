package com.ztf.application.service;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.ztf.application.model.User;

public interface UserService {
    public PageInfo<User> listUser(IPage iPage);
    public int insert (User user);
}
