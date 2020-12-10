package edu.guat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import edu.guat.po.Deposit;
import edu.guat.po.PetFood;
import edu.guat.service.DepositService;
import edu.guat.service.PetFoodService;

@Controller
@RequestMapping("/petfood")
public class PetFoodController {

    @Autowired
    private PetFoodService petFoodService;

    @Autowired
    private DepositService depositService;

    @RequestMapping("/findAll")
    public String findAll(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {
        PageInfo<PetFood> pageInfo = new PageInfo<>(petFoodService.findByPage(pageNum,pageSize));
        model.addAttribute("pageInfo", pageInfo);
        return "petfood";
    }

    @GetMapping("/update")
    @ResponseBody
    public PetFood findById(Integer pid) {
        return petFoodService.findById(pid);
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(PetFood petFood) {
        //旧价格
        Double oldprice = petFoodService.findById(petFood.getPid()).getPrice();
        //新价格
        Double newprice = petFood.getPrice();
        //差价
        Double price_diff = newprice - oldprice;
        List<Deposit> deposits = depositService.findByFoodId(petFood.getPid());
        try {
            if (petFoodService.update(petFood) > 0) {
                for (Deposit deposit : deposits) {
                    Double totalPrice = deposit.getTotalPrice();
                    Integer dayCount = deposit.getDayCount();
                    deposit.setTotalPrice(totalPrice + price_diff * dayCount);
                    depositService.updatePrice(deposit.getTotalPrice(), deposit.getId());
                }
                return "ok";
            } else {
                return "error";
            }
        }catch (Exception e){
            return "exist";
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer pid) {
        try {
            petFoodService.delete(pid) ;
            return "ok";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(PetFood petFood) {
        try {
            if (petFoodService.add(petFood) > 0) {
                return "ok";
            } else {
                return "error";
            }
        }catch (Exception e){
            return "exist";
        }
    }

}
