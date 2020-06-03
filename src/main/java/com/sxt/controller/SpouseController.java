package com.sxt.controller;

import com.sxt.bean.Spouse;
import com.sxt.bean.SpouseImage;
import com.sxt.bean.TArea;
import com.sxt.service.SpouseService;
import com.sxt.service.TAreaService;
import com.sxt.utils.IDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/spouse")
public class SpouseController {
    
    @Autowired
    private SpouseService spouseService;

    @Autowired
    private TAreaService tAreaService;

    @RequestMapping("list")
    public String spouseList(Model model){
        System.out.println("hello world");
        List<Spouse> list = spouseService.listSpouse();
        model.addAttribute("spouselist",list);
        return "spouse/spouselist";
    }

    @RequestMapping("add")
    public String toAddSpouseView(Model model){
        return "spouse/spouseadd";
    }


    @RequestMapping("uplodimage")
    @ResponseBody
    public Map<String,Object> toUpLoadImage(@RequestParam("userimage") MultipartFile uploadFile, String userdesc){

        //获取文件名称
        String fileName = uploadFile.getOriginalFilename();

        String genImageName = IDUtils.genImageName()+fileName.substring(fileName.lastIndexOf("."));
        /*//文件的上传路径
        String filePath = "E:\\java\\uploadfile\\" + fileName;
        File file = new File(filePath);
        //将文件上传到指定的路径
        uploadFile.transferTo(file);*/
        try {
            InputStream in = uploadFile.getInputStream();
            return spouseService.uploadFile(userdesc,genImageName,in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("addspousedo")
    public String addspousedo(@Validated Spouse sp, BindingResult result,Model model){
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError objectError:errors){
                System.out.println(objectError.getCode());
                System.out.println(objectError.getDefaultMessage());
                model.addAttribute("errors", errors);
            }
            return "spouse/spouseadd";
        }

        spouseService.addSpouseDo(sp);
        return "redirect:/spouse/list";
    }

    @RequestMapping("toUpdateView")
    public String toUpdateView(String id,Model model){
        Spouse spouse = spouseService.findSpouseById(id);
        model.addAttribute("spouse",spouse);
        if(spouse != null && StringUtils.isNotBlank(spouse.getImgs())){
            SpouseImage spouseImage = spouseService.fidImageById(spouse.getImgs());
            model.addAttribute("spouseImage",spouseImage);
        }

        return "spouse/spouseupdate";
    }

    @RequestMapping("updatespousedo")
    public String updatespousedo(Spouse sp){
        spouseService.updateSpouseDo(sp);
        return "redirect:/spouse/list";
    }

    @RequestMapping("ztreetest")
    public String toztreetestview(){
        return "spouse/area";
    }


    @RequestMapping("areatree")
    @ResponseBody
    public List<TArea> toareaTreetview(Model model,String id){
        if(StringUtils.isBlank(id)){
            id = "";
        }
        List<TArea> tAreas = tAreaService.listTAreaByParentIdAndStatus(id, 1);
        //model.addAttribute("tAreas",tAreas);
        //return "spouse/area";
        return tAreas;
    }

}
