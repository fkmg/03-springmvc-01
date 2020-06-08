package com.sxt.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by Lenovo on 2019/12/6.
 */
public class User implements Serializable{

    //用户名
    @NotEmpty(message = "用户名为空")
    private String username;
    //密码
    @Length(min= 5,max = 20,message = "用户密码不得超过20位小于6位")
    private String password;
    //用户描述
    private String desc;
    //邮箱
    @Email
    private String email;
    //头像
    private String image;
    //性别
    private Integer sex;
    //爱好
    private String  loves;
    //手机号码
    private String tel;
    //家庭住址
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLoves() {
        return loves;
    }

    public void setLoves(String loves) {
        this.loves = loves;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User() {
    }

    public User(String username, String password, String desc) {
        this.username = username;
        this.password = password;
        this.desc = desc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", desc='" + desc + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", sex=" + sex +
                ", loves=" + loves +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
