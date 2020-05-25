package com.sxt.dao.impl;

import com.sxt.bean.Spouse;
import com.sxt.bean.SpouseImage;
import com.sxt.dao.SpouseDao;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SpouseDaoImpl implements SpouseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Spouse> listSpouse() {
        Spouse spouse = new Spouse();
        spouse.setId(1);
        spouse.setName("mg");
        spouse.setSex(1);
        Date birth = null;
        try {
            birth = DateUtils.parseDate("1997-06-05 12:01:00", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spouse.setBirth(birth);
        spouse.setScore(99.99);
        List<Spouse> list = new ArrayList<>();
        String sql = "SELECT id,name,sex,birth,score FROM spouse";
        //list  = jdbcTemplate.queryForList(sql,Spouse.class);
        List<Spouse> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Spouse>(Spouse.class));
        
        query.add(spouse);
        return query;
    }

    @Override
    public Integer addSpouseImage(SpouseImage image) {
        String sql = "INSERT INTO spouseimage(id,imgurl,userdesc) VALUES(?,?,?)";
        if(image !=null ){
            return jdbcTemplate.update(sql,new Object[]{image.getId(),image.getImgurl(),image.getUserdesc()});
        }

        return 0;
    }

    @Override
    public Integer addSpouseDo(Spouse sp) {
        if(sp != null){
            String sql = "INSERT INTO SPOUSE(NAME,SEX,BIRTH,SCORE,IMGS)VALUES(?,?,?,?,?)";
            return jdbcTemplate.update(sql,new Object[]{sp.getName(),sp.getSex(),sp.getBirth(),sp.getScore(),sp.getImgs()});
        }
        return 0;
    }

    @Override
    public SpouseImage fidImageById(String id) {
        String sql = "SELECT * FROM spouseimage WHERE id = ?";
        SpouseImage spouseImage = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<SpouseImage>(SpouseImage.class),id);
        return spouseImage;
    }

    @Override
    public Spouse findSpouseById(String id) {
        String sql = "SELECT * FROM spouse WHERE id = ?";
        Spouse spouse = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Spouse>(Spouse.class),id);
        return spouse;
    }

    @Override
    public Integer updateSpouseDo(Spouse sp) {
        String sql = "UPDATE SPOUSE SET NAME=?,SEX=?,BIRTH=?,SCORE=?,IMGS=? WHERE ID =?";
        return jdbcTemplate.update(sql,new Object[]{sp.getName(),sp.getSex(),sp.getBirth(),sp.getScore(),sp.getImgs(),sp.getId()});
    }
}
