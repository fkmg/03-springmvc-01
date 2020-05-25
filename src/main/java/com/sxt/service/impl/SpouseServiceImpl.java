package com.sxt.service.impl;

import com.sxt.bean.Spouse;
import com.sxt.bean.SpouseImage;
import com.sxt.dao.SpouseDao;
import com.sxt.service.SpouseService;
import com.sxt.utils.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(readOnly = false)
public class SpouseServiceImpl implements SpouseService {

    @Autowired
    private SpouseDao spouseDao;

    @Override
    public List<Spouse> listSpouse() {
        return spouseDao.listSpouse();
    }


    @Override
    public Map<String, Object> uploadFile(String userdesc, String genImageName, InputStream in) {
        Map<String,Object> resultMap = new HashMap<>();
        boolean result = FtpUtil.uploadFile("192.168.2.129", 21, "ftpuser", "123456", "/home/ftp/", "/spouse", genImageName, in);
        if (result){
            //上传成功
            //将信息保存到数据库中
            SpouseImage image = new SpouseImage();
            image.setId(UUID.randomUUID().toString().replaceAll("-",""));
            image.setImgurl("http://192.168.2.129/spouse/"+genImageName);
            image.setUserdesc(userdesc);
            spouseDao.addSpouseImage(image);
            resultMap.put("status",200);
            resultMap.put("imgeId",image.getId());
            resultMap.put("userdesc",userdesc);
            resultMap.put("imgurl","http://192.168.2.129/spouse/"+genImageName);
            return resultMap;
        }
        //上传失败
        resultMap.put("status",500);
        return resultMap;
    }

    @Override
    public Integer addSpouseDo(Spouse sp) {
        return spouseDao.addSpouseDo(sp);
    }

    @Override
    public SpouseImage fidImageById(String id) {
        return spouseDao.fidImageById(id);
    }

    @Override
    public Spouse findSpouseById(String id) {
        return spouseDao.findSpouseById(id);
    }

    @Override
    public Integer updateSpouseDo(Spouse sp) {
        return spouseDao.updateSpouseDo(sp);
    }
}
