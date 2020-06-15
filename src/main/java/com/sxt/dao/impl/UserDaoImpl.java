package com.sxt.dao.impl;

import com.sxt.bean.User;
import com.sxt.dao.UserDao;
import com.sxt.utils.Jdbc;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Lenovo on 2019/12/7.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Log log = LogFactory.getLog(UserDaoImpl.class);

    @Override
    public Integer insertUser(User user) {

        String sql = "INSERT INTO USER(username,PASSWORD,DESC,email,image,sex,loves,tel,address)VALUES(?,?,?,?,?,?,?,?,?)";
        //Boolean flag = Jdbc.update(sql, user.getUsername(), user.getPassword(), user.getDesc(), user.getEmail(), user.getImage(), user.getSex(), user.getLoves(), user.getTel(), user.getAddress());
        return jdbcTemplate.update(sql,new Object[]{ user.getUsername(), user.getPassword(), user.getDesc(), user.getEmail(), user.getImage(), user.getSex(), user.getLoves(), user.getTel(), user.getAddress()});
    }

    @Override
    public User findUserByUserName(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        ResultSet rst = Jdbc.search(sql, username);
        BeanHandler<User> beanHandler = new BeanHandler<User>(User.class);
        User user = beanHandler.handle(rst);
        return user;
    }

    @Override
    public Integer updateUser(User user) {
        String sql = "UPDATE USER SET DESC=?,email=?,image=?,sex=?,loves=?,tel=?,address=? WHERE username = ?";
        Boolean flag = Jdbc.update(sql, user.getDesc(), user.getEmail(), user.getImage(), user.getSex(), user.getLoves(), user.getTel(), user.getAddress(), user.getUsername());
        if(flag){
            return 1;
        }
        return -1;
    }
}
