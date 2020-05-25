package com.sxt.dao;

import com.sxt.bean.Spouse;
import com.sxt.bean.SpouseImage;

import java.util.List;

public interface SpouseDao {

    /**
     * 展示spouse信息
     * @return
     */
    List<Spouse> listSpouse();

    /**
     * 添加spouseimage信息
     * @param image
     * @return
     */
    Integer addSpouseImage(SpouseImage image);

    /**
     * 保存spouse信息
     * @param sp
     * @return
     */
    Integer addSpouseDo(Spouse sp);

    /**
     * 查询spouseimage 信息通过id
     * @param id
     * @return
     */
    SpouseImage fidImageById(String id);

    /**
     * 查询spouse 信息
     * @param id
     * @return
     */
    Spouse findSpouseById(String id);

    /**
     * 修改spouse 信息
     * @param sp
     * @return
     */
    Integer updateSpouseDo(Spouse sp);
}
