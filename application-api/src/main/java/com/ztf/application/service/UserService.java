package com.ztf.application.service;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.ztf.application.model.User;

/**
 * 测试Service接口
 * @author zhangtf
 * @createTime 2018-11-11 11:11:11
 */
public interface UserService {
    public PageInfo<User> listUser(IPage iPage);
    public int insert (User user);
}
