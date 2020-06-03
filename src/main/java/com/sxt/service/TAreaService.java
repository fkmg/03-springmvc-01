package com.sxt.service;

import com.sxt.bean.TArea;

import java.util.List;

public interface TAreaService {

    /**
     * 查询地区集合,根据父id与状态status
     * @param parentId
     * @param status
     * @return
     */
    List<TArea> listTAreaByParentIdAndStatus(String parentId, Integer status);
}
