package com.sxt.service;

import com.sxt.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * Created by Lenovo on 2019/12/7.
 */
public interface UserService {

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

    /**
     * 登录用户
     * @param user
     * @param request
     * @param response
     * @return
     */
    boolean landUser(User user, HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException;
}
