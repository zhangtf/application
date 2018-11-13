package com.ztf.application.dao;

import com.ztf.application.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("userMapper")
public interface UserMapper {
    public List<User> listUser();
    public int insert(User user);
}
