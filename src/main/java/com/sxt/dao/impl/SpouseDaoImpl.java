package com.sxt.dao.impl;

import com.sxt.bean.Spouse;
import com.sxt.dao.SpouseDao;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        Map<String, Object> sqlMap = jdbcTemplate.queryForMap(sql);
        list = jdbcTemplate.query(sql, new RowMapper<Spouse>() {

            @Override
            public Spouse mapRow(ResultSet resultSet, int i) throws SQLException {
                BeanHandler<Spouse> beanHandler = new BeanHandler<Spouse>(Spouse.class);
                Spouse handle = beanHandler.handle(resultSet);
                System.out.println(handle);
                return handle;
            }
        });
        List<Spouse> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Spouse>(Spouse.class));
        
        query.add(spouse);
        return query;
    }
}
