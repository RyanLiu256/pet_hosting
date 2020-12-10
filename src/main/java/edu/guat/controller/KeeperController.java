package edu.guat.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;

import edu.guat.po.Keeper;
import edu.guat.service.KeeperService;

@Controller
@RequestMapping("/keeper")
public class KeeperController {

    @Autowired
    private KeeperService keeperService;

    @RequestMapping("/findAll")
    public String findAll(Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<Keeper> pageInfo = new PageInfo<Keeper>(keeperService.findByPage(pageNum, pageSize));
        model.addAttribute("pageInfo", pageInfo);
        return "keeper";
    }

    @GetMapping("/update")
    @ResponseBody
    public Keeper findById(Integer kid) {
        return keeperService.findById(kid);
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(Keeper keeper) {
        if (keeper.getDescription()==""){
            keeper.setDescription("暂无");
        }
        if (keeperService.update(keeper) > 0) {
            return "ok";
        } else {
            return "error";
        }
    }

    @PostMapping("/updatePicture")
    @ResponseBody
    public String updatePicture(@RequestParam("picture")MultipartFile picture,Integer kid) {
        if (picture==null){
            return "error";
        }
        String fileName = picture.getOriginalFilename();
        String path = "D:/develop/JetBrains/IdeaProjects/pet_hosting/src/main/resources/static/img/keeper/";
        String savePath = path + fileName;
        File dest = new File(new File(savePath).getAbsolutePath());
        try {
            //上传到resources目录
            picture.transferTo(dest);
            Keeper keeper = new Keeper();
            keeper.setKid(kid);
            keeper.setPicture(fileName);
            keeperService.updatePicture(keeper);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer kid) {
        if (keeperService.delete(kid) > 0) {
            return "ok";
        } else {
            return "error";
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestParam("name") String name,@RequestParam("sex") Integer sex,@RequestParam("phone")String phone,
                      @RequestParam("description")String description,@RequestParam("picture") MultipartFile picture) {
        if (picture==null){
            return "error";
        }
        String fileName = picture.getOriginalFilename();
        String path = "D:/develop/JetBrains/IdeaProjects/pet_hosting/src/main/resources/static/img/keeper/";
        String savePath = path + fileName;
        File dest = new File(new File(savePath).getAbsolutePath());
        try {
            //上传到resources目录
            picture.transferTo(dest);
            Keeper keeper = new Keeper();
            keeper.setName(name);
            keeper.setSex(sex);
            keeper.setPhone(phone);
            if (description==""|description==null){
                description="暂无";
            }
            keeper.setDescription(description);
            keeper.setPicture(fileName);
            keeperService.add(keeper);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "exist";
        }
    }

}
