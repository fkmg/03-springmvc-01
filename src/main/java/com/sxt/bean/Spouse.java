package com.sxt.bean;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.util.Date;

public class Spouse implements Serializable {

    /**id**/
    private Integer id;

    /**姓名**/
    private String name;

    /**性别**/
    private Integer sex;

    /**出生日期**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date birth;

    /**评分**/
    @NumberFormat(pattern = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$")
    private Double score;

    /**照片**/
    private String imgs;

    /**图片描述**/
    private String userdesc;

    public String getUserdesc() {
        return userdesc;
    }

    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }


    @Override
    public String toString() {
        return "Spouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birth=" + birth +
                ", score=" + score +
                ", imgs='" + imgs + '\'' +
                '}';
    }
}
