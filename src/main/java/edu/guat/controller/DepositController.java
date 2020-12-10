package edu.guat.controller;

import com.github.pagehelper.PageInfo;
import edu.guat.po.*;
import edu.guat.service.*;
import edu.guat.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @Autowired
    private BreedsService breedsService;

    @Autowired
    private PetFoodService petFoodService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private KeeperService keeperService;

    @Autowired
    private AnalysisService analysisService;

    @RequestMapping("/findAll")
    public String findAll(Model model, HttpSession session,
                          @RequestParam(value = "userid", defaultValue = "") Integer userid,
                          @RequestParam(value = "username", defaultValue = "") String username,
                          @RequestParam(value = "year", defaultValue = "") Integer year,
                          @RequestParam(value = "month", defaultValue = "") Integer month,
                          @RequestParam(value = "state", defaultValue = "") Integer state,
                          @RequestParam(value = "ispay", defaultValue = "") Integer ispay,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer page,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer size) {
        PageInfo<Deposit> pageInfo = depositService.findAll(username,userid,year,month,state,ispay, page, size);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("years",analysisService.findYears());
        model.addAttribute("userid", userid);
        model.addAttribute("username", username);
        model.addAttribute("year1", year);
        model.addAttribute("month1", month);
        model.addAttribute("state1", state);
        model.addAttribute("ispay1", ispay);
        System.out.println("==="+ispay);
        session.setAttribute("breeds", breedsService.findAll());
        session.setAttribute("petFoods", petFoodService.findAll());
        session.setAttribute("services", serviceService.findAll());
        session.setAttribute("keepers", keeperService.findAll());
        return "deposit";
    }

    @GetMapping("/update")
    @ResponseBody
    public DepositAndService findById(Integer id) {
        Deposit deposit = depositService.findById(id);
        List<Service> services = serviceService.findService(id);
        DepositAndService depositAndService = new DepositAndService();
        depositAndService.setDeposit(deposit);
        depositAndService.setServices(services);
        return depositAndService;
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateDeposit(Deposit deposit) {
        //删除原来的服务
        serviceService.deleteService(deposit.getId());
        //获取入住时间
        Date startTime = deposit.getStartTime();
        //获取领回时间
        Date endTime = deposit.getEndTime();
        //计算托管天数
        long dayCount = (endTime.getTime() - startTime.getTime()) / (1000L * 3600L * 24L);
        if (dayCount == 0) {
            dayCount = 1;
        }
        //查询宠物品种托管价格
        Double breedsPrice = breedsService.findById(deposit.getBreeds().getBid()).getBreedPrice();
        //查询宠物粮食价格
        Double petFoodPrice = petFoodService.findById(deposit.getPetFood().getPid()).getPrice();
        //计算总价格
        Double totalPrice = (breedsPrice + petFoodPrice) * dayCount;
        deposit.setDayCount((int) dayCount);//设置天数
        deposit.setTotalPrice(totalPrice);//设置总价格
        //获取前端提交的服务
        Map<Integer, Service> services = deposit.getServices();
        Set<Integer> keySet = services.keySet();
        if (keySet != null && keySet.size() > 0) {
            depositService.updateDeposit(deposit);
            for (Integer key : keySet) {
                //获取前端提交的服务
                Service service = services.get(key);
                //获取服务的id
                Integer serviceId = service.getId();
                //获取服务的价格
                Double servicePrice = serviceService.findById(serviceId).getPrice();
                totalPrice += servicePrice * dayCount;    //修改总价格
                //获取托管id
                Integer depositId = deposit.getId();
                serviceService.addService(depositId, serviceId);
            }
            deposit.setTotalPrice(totalPrice);
            depositService.updatePrice(totalPrice, deposit.getId());
            return "ok";
        } else {
            depositService.updateDeposit(deposit);
            return "ok";
        }
    }

    @RequestMapping("/addDeposit")
    @ResponseBody
    public String addDeposit(Deposit deposit) {
        //获取入住时间
        Date startTime = deposit.getStartTime();
        //获取领回时间
        Date endTime = deposit.getEndTime();
        //计算托管天数
        long dayCount = (endTime.getTime() - startTime.getTime()) / (1000L * 3600L * 24L);
        if (dayCount == 0) {
            dayCount = 1;
        }
        //查询宠物品种托管价格
        Double breedsPrice = breedsService.findById(deposit.getBreeds().getBid()).getBreedPrice();
        //查询宠物粮食价格
        Double petFoodPrice = petFoodService.findById(deposit.getPetFood().getPid()).getPrice();
        //计算总价格
        Double totalPrice = (breedsPrice + petFoodPrice) * dayCount;
        deposit.setDayCount((int) dayCount);//设置天数
        deposit.setTotalPrice(totalPrice);//设置总价格
        Map<Integer, Service> services = deposit.getServices();
        Set<Integer> keySet = services.keySet();
        if (keySet != null && keySet.size() > 0) {
            depositService.addDeposit(deposit);
            for (Integer key : keySet) {
                //获取前端提交的服务
                Service service = services.get(key);
                //获取服务的id
                Integer serviceId = service.getId();
                //获取服务的价格
                Double servicePrice = serviceService.findById(serviceId).getPrice();
                totalPrice += servicePrice * dayCount;    //修改总价格
                //获取托管id
                Integer depositId = deposit.getId();
                serviceService.addService(depositId, serviceId);
            }
            deposit.setTotalPrice(totalPrice);
            depositService.updatePrice(totalPrice, deposit.getId());
            return "ok";
        } else {
            depositService.addDeposit(deposit);
            return "ok";
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteById(Integer id) {
        if (depositService.deleteById(id) > 0) {
            return "ok";
        } else {
            return "error";
        }
    }

    @RequestMapping("/mydeposit")
    public String mydeposit(Model model, HttpSession session,
                            @RequestParam(value = "userid", defaultValue = "") Integer userid,
                            @RequestParam(value = "petname", defaultValue = "") String petname,
                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        session.setAttribute("breeds", breedsService.findAll());
        session.setAttribute("petFoods", petFoodService.findAll());
        session.setAttribute("services", serviceService.findAll());
        session.setAttribute("keepers", keeperService.findAll());
        PageInfo<Deposit> pageInfo = new PageInfo<>(depositService.findMydeposit(userid, petname, pageNum, pageSize));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("petname", petname);
        return "mydeposit";
    }

    @RequestMapping("/findDetail")
    public String findDetail(Integer id, Model model) {
        model.addAttribute("deposit", depositService.findById(id));
        model.addAttribute("services", serviceService.findService(id));
        model.addAttribute("comment",depositService.findCommentById(id));
        return "deposit-detail";
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public String changeState(Integer state,String reason, Integer id) {
        if (state == 3) {
            int flag = depositService.findById(id).getIspay();
            if (flag == 1) {
                if (depositService.updatedepositState(state, id) > 0) {
                    return "ok1";
                } else {
                    return "error";
                }
            }else {
                return "nopay";
            }
        }
        if (state == 2 || state == 4) {
            int flag = depositService.findById(id).getIspay();
            if (flag == 2) {
                if (depositService.updatedepositState(state, id) > 0) {
                    return "ok2";
                } else {
                    return "error";
                }
            }else {
                return "havepay";
            }
        }
        if (depositService.updatedepositState(state, id) > 0) {
            return "ok3";
        } else {
            return "error";
        }
    }

    @RequestMapping("/reject")
    @ResponseBody
    public String reject(Integer state,String reason, Integer id) {
        System.out.println(reason);
        if (depositService.reject(state,reason, id) > 0) {
            return "ok";
        } else {
            return "error";
        }
    }

    @RequestMapping("/findIspay")
    @ResponseBody
    public Integer findIspay(Integer id){
        return depositService.findById(id).getIspay();
    }

    @RequestMapping("/changePay")
    @ResponseBody
    public String changePay(Integer ispay, Integer id) {
        if (ispay == 2){
            int flag = depositService.findById(id).getState();
            if (flag != 3){
                if (depositService.changePay(ispay, id) > 0) {
                    return "ok";
                } else {
                    return "error";
                }
            } else {
                return "finished";
            }
        }
        if (ispay == 1){
            int flag = depositService.findById(id).getState();
            if (flag == 1){
                if (depositService.changePay(ispay, id) > 0) {
                    return "ok";
                } else {
                    return "error";
                }
            } else {
                return "nopass";
            }
        }
        if (depositService.changePay(ispay, id) > 0) {
            return "ok";
        } else {
            return "error";
        }
    }

    @RequestMapping("/topay")
    public String topay() {
        return "pay";
    }

    @RequestMapping("/addComment")
    @ResponseBody
    public String addComment(String comment, Integer id){
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        depositService.addComment(comment1);
        depositService.updateCommentId(comment1.getCid(),id);
        return "ok";
    }

    @RequestMapping("/addReply")
    @ResponseBody
    public String addReply(String reply, Integer cid){
        depositService.addReply(reply,cid);
        return "ok";
    }

    @RequestMapping("/seeComment")
    public String seeComment(Integer cid,Model model){
        model.addAttribute("comment",depositService.findCommentByCid(cid));
        return "comment";
    }

    @RequestMapping("/commentlist")
    public String commentlist(Model model,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer page,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer size){
        PageInfo<Deposit> pageInfo = new PageInfo<>(depositService.findAllComment(page,size));
        model.addAttribute("pageInfo",pageInfo);
        return "comment-list";
    }

    @RequestMapping("/findCommentByCid")
    @ResponseBody
    public Comment findCommentByCid(Integer cid){
        return depositService.findCommentByCid(cid);
    }

    @RequestMapping("/changeReply")
    @ResponseBody
    public String changeReply(String reply, Integer cid){
        depositService.changeReply(reply,cid);
        return "ok";
    }

    @RequestMapping("/changeComment")
    @ResponseBody
    public String changeComment(String comment, Integer cid){
        depositService.changeComment(comment,cid);
        return "ok";
    }

}
