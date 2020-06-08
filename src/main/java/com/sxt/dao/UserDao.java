package com.sxt.dao;

import com.sxt.bean.User;

import java.sql.SQLException;

/**
 * Created by Lenovo on 2019/12/7.
 */
public interface UserDao {
    /**
     * 插入用户信息
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findUserByUserName(String username) throws SQLException;

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Integer updateUser(User user);
}
