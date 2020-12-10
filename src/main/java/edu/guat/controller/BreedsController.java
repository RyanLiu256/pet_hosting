package edu.guat.controller;

import com.github.pagehelper.PageInfo;
import edu.guat.po.Breeds;
import edu.guat.po.Deposit;
import edu.guat.service.BreedsService;
import edu.guat.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/breeds")
public class BreedsController {

    @Autowired
    private BreedsService breedsService;

    @Autowired
    private DepositService depositService;

    @RequestMapping("/findAll")
    public String findAll(Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<Breeds> pageInfo = new PageInfo<Breeds>(breedsService.findByPage(pageNum, pageSize));
        model.addAttribute("pageInfo", pageInfo);
        return "breeds";
    }

    @GetMapping("/update")
    @ResponseBody
    public Breeds findById(Integer bid) {
        return breedsService.findById(bid);
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(Breeds breeds) {
            //旧价格
            Double oldprice = breedsService.findById(breeds.getBid()).getBreedPrice();
            //新价格
            Double newprice = breeds.getBreedPrice();
            //差价
            Double price_diff = newprice - oldprice;
            List<Deposit> deposits = depositService.findBySpeciesId(breeds.getBid());
            try {
                if (breedsService.update(breeds) > 0) {
                    if (deposits.size() > 0) {
                        for (Deposit deposit : deposits) {
                            Double totalPrice = deposit.getTotalPrice();
                            Integer dayCount = deposit.getDayCount();
                            deposit.setTotalPrice(totalPrice + price_diff * dayCount);
                            depositService.updatePrice(deposit.getTotalPrice(), deposit.getId());
                        }
                    }
                    return "ok";
                } else {
                    return "error";
                }
            }catch (Exception e){
                return "exist";
            }
    }

    @PostMapping("/updatePicture")
    @ResponseBody
    public String updatePicture(@RequestParam("picture")MultipartFile picture,Integer bid) {
        if (picture==null){
            return "error";
        }
        String fileName = picture.getOriginalFilename();
        String path = "D:/develop/JetBrains/IdeaProjects/pet_hosting/src/main/resources/static/img/pet/";
        String savePath = path + fileName;
        File dest = new File(new File(savePath).getAbsolutePath());
        try {
            //上传到resources目录
            picture.transferTo(dest);
            Breeds breeds = new Breeds();
            breeds.setBid(bid);
            breeds.setPicture(fileName);
            breedsService.updatePicture(breeds);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer bid) {
        try {
            if (breedsService.delete(bid) > 0) {
                return "ok";
            } else {
                return "error";
            }
        }catch (Exception e){
            return "depositing";
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestParam("species") String species, @RequestParam("breedPrice") Double breedPrice, @RequestParam("picture") MultipartFile picture) {
        if (picture.isEmpty()) {
            //文件为空
            return "error";
        }
        String fileName = picture.getOriginalFilename();  // 文件名
//        String path = this.getClass().getClassLoader().getResource("static").getPath()+"/img/";
        String path = "D:/develop/JetBrains/IdeaProjects/pet_hosting/src/main/resources/static/img/pet/";
        String savePath = path + fileName;
        File dest = new File(new File(savePath).getAbsolutePath());
        try {
            //上传到resources目录
            picture.transferTo(dest);
            Breeds breeds = new Breeds();
            breeds.setSpecies(species);
            breeds.setBreedPrice(breedPrice);
            breeds.setPicture(fileName);
            breedsService.add(breeds);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "exist";
        }
    }

}
