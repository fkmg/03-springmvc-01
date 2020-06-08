package com.sxt.service.impl;

import com.sxt.bean.User;
import com.sxt.dao.UserDao;
import com.sxt.jedis.JedisClient;
import com.sxt.service.UserService;
import com.sxt.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Lenovo on 2019/12/7.
 */

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = false)
    @Override
    public Integer insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public User findUserByUserName(String username) throws SQLException {
        return userDao.findUserByUserName(username);
    }

    @Transactional(readOnly = false)
    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean landUser(User user, HttpServletRequest request) throws SQLException {
        boolean flag = false;
        //根据用户名从数据库中查询用户
        User finduser = userDao.findUserByUserName(user.getUsername());
        //判断是否登录成功

        //获取用户的请求ip
        String addr = request.getRemoteAddr();
        int port = request.getRemotePort();
        if(finduser.getPassword().equals(user.getPassword())){
            jedisClient.hset("loginuser",addr, JsonUtils.objectToJson(user));
            flag = true;
        }
        return flag;
    }
}
