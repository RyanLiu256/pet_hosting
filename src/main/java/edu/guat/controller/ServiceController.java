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
import edu.guat.po.Service;
import edu.guat.service.DepositService;
import edu.guat.service.ServiceService;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private DepositService depositService;

    @RequestMapping("/findAll")
    public String findAll(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<Service> pageInfo = new PageInfo<>(serviceService.findByPage(pageNum,pageSize));
        model.addAttribute("pageInfo", pageInfo);
        return "service";
    }

    @GetMapping("/update")
    @ResponseBody
    public Service findById(Integer id) {
        return serviceService.findById(id);
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(Service service) {
        //旧价格
        Double oldprice = serviceService.findById(service.getId()).getPrice();
        //新价格
        Double newprice = service.getPrice();
        //差价
        Double price_diff = newprice - oldprice;
        List<Deposit> deposits = depositService.findByServiceId(service.getId());
        try {
            if (serviceService.update(service) > 0) {
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
    public String delete(Integer id) {
        try {
            serviceService.delete(id) ;
            return "ok";
        } catch (Exception e) {
            return "depositing";
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(Service service) {
        try {
            if (serviceService.add(service) > 0) {
                return "ok";
            } else {
                return "error";
            }
        }catch (Exception e){
            return "exist";
        }
    }


}
