package com.sxt.service.impl;

import com.sxt.bean.Spouse;
import com.sxt.dao.SpouseDao;
import com.sxt.service.SpouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpouseServiceImpl implements SpouseService {

    @Autowired
    private SpouseDao spouseDao;

    @Override
    public List<Spouse> listSpouse() {
        return spouseDao.listSpouse();
    }
}
