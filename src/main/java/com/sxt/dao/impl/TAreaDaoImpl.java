package com.sxt.dao.impl;

import com.sxt.bean.TArea;
import com.sxt.dao.TAreaDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TAreaDaoImpl  implements TAreaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TArea> listTAreaByParentIdAndStatus(String parentId, Integer status) {

        List<TArea> list = new ArrayList<>();
        String sql = "SELECT id,area_id AS areaId, parent_area_id AS parentAreaId,area_or_company areaOrCompany,STATUS FROM t_area WHERE  parent_area_id = ? AND STATUS = ?";
        //list  = jdbcTemplate.queryForList(sql,Spouse.class);
        //List<TArea> query = jdbcTemplate.queryForList(sql,new Object[]{parentId,status},TArea.class);
        String sql1 = "SELECT id,area_id AS areaId, parent_area_id AS parentAreaId,area_or_company areaOrCompany,STATUS FROM t_area";
        if(StringUtils.isBlank(parentId)){
            list = jdbcTemplate.query(sql1,new BeanPropertyRowMapper<TArea>(TArea.class));
        }else {
            list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TArea>(TArea.class),new Object[]{parentId,status});
        }
        return list;
    }
}
