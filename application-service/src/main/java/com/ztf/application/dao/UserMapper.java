package com.ztf.application.dao;

import com.ztf.application.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试Mapper
 * @author zhangtf
 * @createTime 2018-11-11 11:11:11
 */
@Repository("userMapper")
public interface UserMapper {
    /**
     * 批量查询
     * @return 所有的user
     */
    public List<User> listUser();

    /**
     * 插入User
     * @param user  要插入的对象
     * @return      0：失败，1：成功
     */
    public int insert(User user);
}
