package com.ztf.application.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {

    private static final long serialVersionUID = -8918079271308353596L;

    private String id;
    private String userName;
    private Integer age;
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
