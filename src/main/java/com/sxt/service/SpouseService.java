package com.sxt.service;

import com.sxt.bean.Spouse;
import com.sxt.bean.SpouseImage;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface SpouseService {

    /**
     * 查询spouse列表
     * @return
     */
    List<Spouse> listSpouse();

    /**
     * 保存spouseimg
     *
     * @param userdesc
     * @param genImageName
     * @param in
     * @return
     */
    Map<String, Object> uploadFile(String userdesc, String genImageName, InputStream in);

    /**
     * 保存spouse信息
     * @param sp
     * @return
     */
    Integer addSpouseDo(Spouse sp);

    /**
     * 查询spouseimage 通过id
     * @param id
     * @return
     */
    SpouseImage fidImageById(String id);

    /**
     * 查询spouse 通过id
     * @param id
     * @return
     */
    Spouse findSpouseById(String id);
}
