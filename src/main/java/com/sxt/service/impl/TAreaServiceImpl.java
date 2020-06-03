package com.sxt.service.impl;

import com.sxt.bean.TArea;
import com.sxt.dao.TAreaDao;
import com.sxt.service.TAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TAreaServiceImpl implements TAreaService {

    @Autowired
    private TAreaDao tAreaDao;

    @Override
    public List<TArea> listTAreaByParentIdAndStatus(String parentId, Integer status) {
        return tAreaDao.listTAreaByParentIdAndStatus(parentId,status);
    }
}
