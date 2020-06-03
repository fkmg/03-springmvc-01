package com.sxt;

import com.sxt.bean.TArea;
import com.sxt.dao.TAreaDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml","classpath:applicationContext-servlet.xml","classpath:applicationContext-spring.xml"})
public class TestSpring {

    @Autowired
    private TAreaDao tAreaDao;

    @Test
    public void testGetTAreaList(){

        List<TArea> tAreas = tAreaDao.listTAreaByParentIdAndStatus("100000000000", 1);
        System.out.println(tAreas.size());
    }
}
