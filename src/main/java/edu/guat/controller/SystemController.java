package edu.guat.controller;

import edu.guat.po.*;
import edu.guat.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private AnalysisService analysisService;

    @RequestMapping("/tobackup")
    public String tobackup() {
        return "backup";
    }

    @RequestMapping("/backupdb")
    @ResponseBody
    public String backupdb(String dbusername, String dbpwd, String path, String filename) {
        //要备份的数据库名
        String dbName = "pethosting";
        if (filename == ""){
            filename = dbName+new Date().getTime() + ".sql";
        }
        //cmd命令
        String command = "cmd  /c  mysqldump -u" + dbusername + " -p" + dbpwd + " " + dbName + " > " + path +"/"+ filename;
        try {
            Process process = Runtime.getRuntime().exec(command);
            //备份的数据库名字为pethosting，数据库连接和密码均为root
            System.out.println(command);
            //使用该输入流获取命令行返回的错误信息
            InputStream es = process.getErrorStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(es));
            String line = reader.readLine();
            System.out.println(line);
            process.waitFor();
            es.close();
            reader.close();
            process.destroy();
            if ("mysqldump: [Warning] Using a password on the command line interface can be insecure.".equals(line)){
                File dbfile = new File(path+"/"+filename);
                InputStream is = new FileInputStream(dbfile);
                int read = is.read();
                is.close();
                if (read>0){
                    return "ok";
                }else {
                    dbfile.delete();
                    return "error";
                }
            }else {
                return "error";
            }
//            while((line = reader.readLine())!= null){
//                System.out.println(line);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/analysis")
    public String analysis(Model model) {
        //系统中的所有用户
        model.addAttribute("allUsers", analysisService.findAllUser());
        //托管中的用户
        List<User> UsersList = analysisService.findDepositUser();
        List<DepositUser> depositUsers = new ArrayList<>();
        for (User depositUser : UsersList) {
            DepositUser d = new DepositUser();
            d.setUsername(depositUser.getUsername());
            d.setDepositCount(analysisService.findUserDepositCount(depositUser.getUsername()));
            depositUsers.add(d);
        }
        model.addAttribute("depositUsers", depositUsers);
        //托管中的宠物品种
        List<Breeds> breedsList = analysisService.findDepositingBreeds();
        List<DepositBreeds> depositBreeds = new ArrayList<>();
        for (Breeds depositBreed : breedsList) {
            DepositBreeds breeds = new DepositBreeds();
            breeds.setSpecies(depositBreed.getSpecies());
            breeds.setDepositCount(analysisService.findDepositBreedsCount(depositBreed.getBid()));
            depositBreeds.add(breeds);
        }
        model.addAttribute("depositBreeds", depositBreeds);
        //托管中的宠物粮食
        List<PetFood> petFoodList = analysisService.findDepositingPetfood();
        List<DepositPetFood> depositPetFoods = new ArrayList<>();
        for (PetFood petFood : petFoodList) {
            DepositPetFood depositPetFood = new DepositPetFood();
            depositPetFood.setBrand(petFood.getBrand());
            depositPetFood.setDepositCount(analysisService.findDepositPetfoodCount(petFood.getPid()));
            depositPetFoods.add(depositPetFood);
        }
        model.addAttribute("depositPetfood", depositPetFoods);
        //托管中的服务
        List<Service> serviceList = analysisService.findDepositingService();
        List<DepositService> depositServices = new ArrayList<>();
        for (Service service : serviceList) {
            DepositService depositService = new DepositService();
            depositService.setServiceName(service.getServiceName());
            depositService.setDepositCount(analysisService.findDepositServiceCount(service.getId()));
            depositServices.add(depositService);
        }
        model.addAttribute("depositService", depositServices);
        //托管总收入
        model.addAttribute("totalPrice", analysisService.findTotalPrice());
        return "analysis";
    }

    @GetMapping("/breedsEcharts")
    public String toBreedsEcharts(Model model) {
        model.addAttribute("years",analysisService.findYears());
        return "breeds-echarts";
    }

    @PostMapping("/breedsEcharts")
    @ResponseBody
    public List<DepositBreeds> showBreedsEcharts() {
        List<Breeds> breedsList = analysisService.findDepositingBreeds();
        List<DepositBreeds> depositBreeds = new ArrayList<>();
        for (Breeds depositBreed : breedsList) {
            DepositBreeds breeds = new DepositBreeds();
            breeds.setSpecies(depositBreed.getSpecies());
            breeds.setDepositCount(analysisService.findDepositBreedsCount(depositBreed.getBid()));
            depositBreeds.add(breeds);
        }
        return depositBreeds;
    }

    @GetMapping("/petfoodEcharts")
    public String toPetfoodEcharts(Model model) {
        model.addAttribute("years",analysisService.findYears());
        return "petfood-echarts";
    }

    @PostMapping("/petfoodEcharts")
    @ResponseBody
    public List<DepositPetFood> showPetfoodEcharts() {
        List<PetFood> petFoodList = analysisService.findDepositingPetfood();
        List<DepositPetFood> depositPetFoods = new ArrayList<>();
        for (PetFood petFood : petFoodList) {
            DepositPetFood depositPetFood = new DepositPetFood();
            depositPetFood.setBrand(petFood.getBrand());
            depositPetFood.setDepositCount(analysisService.findDepositPetfoodCount(petFood.getPid()));
            depositPetFoods.add(depositPetFood);
        }
        return depositPetFoods;
    }

    @GetMapping("/serviceEcharts")
    public String toServiceEcharts(Model model) {
        model.addAttribute("years",analysisService.findYears());
        return "service-echarts";
    }

    @PostMapping("/serviceEcharts")
    @ResponseBody
    public List<DepositService> showServiceEcharts() {
        List<Service> serviceList = analysisService.findDepositingService();
        List<DepositService> depositServices = new ArrayList<>();
        for (Service service : serviceList) {
            DepositService depositService = new DepositService();
            depositService.setServiceName(service.getServiceName());
            depositService.setDepositCount(analysisService.findDepositServiceCount(service.getId()));
            depositServices.add(depositService);
        }
        return depositServices;
    }

    @GetMapping("/findBreedsByMonth")
    public String toFindBreedsByMonth(Model model) {
        model.addAttribute("years",analysisService.findYears());
        return "breeds-echarts";
    }

    @PostMapping("findBreedsByMonth")
    @ResponseBody
    public List<DepositBreeds> findBreedsByMonth(@RequestParam(value = "year") Integer year,
                                                 @RequestParam(value = "month", defaultValue = "1") Integer month) {
        List<Breeds> breedsList = analysisService.findBreedsByMonth(year, month);
        List<DepositBreeds> depositBreedsList = new ArrayList<>();
        for (Breeds breeds : breedsList) {
            DepositBreeds depositBreeds = new DepositBreeds();
            depositBreeds.setSpecies(breeds.getSpecies());
            depositBreeds.setDepositCount(analysisService.findBreedsCountByMonth(year, month, breeds.getBid()));
            int totalDays = analysisService.findBreedsTotalDaysByMonth(year, month, breeds.getBid());
            double price = breeds.getBreedPrice();
            //本月份该宠物品种的收益
            depositBreeds.setProfit(totalDays * price);
            depositBreedsList.add(depositBreeds);
        }
        return depositBreedsList;
    }

    @GetMapping("/findPetfoodByMonth")
    public String toFindPetfoodByMonth(Model model) {
        model.addAttribute("years",analysisService.findYears());
        return "petfood-echarts";
    }

    @PostMapping("findPetfoodByMonth")
    @ResponseBody
    public List<DepositPetFood> findPetfoodByMonth(@RequestParam(value = "year") Integer year,
                                                   @RequestParam(value = "month", defaultValue = "1") Integer month) {
        List<PetFood> petFoodList = analysisService.findPetfoodByMonth(year, month);
        List<DepositPetFood> depositPetFoodList = new ArrayList<>();
        for (PetFood petFood : petFoodList) {
            DepositPetFood depositPetFood = new DepositPetFood();
            depositPetFood.setBrand(petFood.getBrand());
            depositPetFood.setDepositCount(analysisService.findPetfoodCountByMonth(year, month, petFood.getPid()));
            int totalDays = analysisService.findPetfoodTotalDaysByMonth(year, month, petFood.getPid());
            double price = petFood.getPrice();
            //本月份该宠物粮食品牌的收益
            depositPetFood.setProfit(totalDays * price);
            depositPetFoodList.add(depositPetFood);
        }
        return depositPetFoodList;
    }

    @GetMapping("/findServiceByMonth")
    public String toFindServiceByMonth(Model model) {
        model.addAttribute("years",analysisService.findYears());
        return "service-echarts";
    }

    @PostMapping("findServiceByMonth")
    @ResponseBody
    public List<DepositService> findServiceByMonth(@RequestParam(value = "year") Integer year,
                                                   @RequestParam(value = "month", defaultValue = "1") Integer month) {
        List<Service> serviceList = analysisService.findServiceByMonth(year, month);
        List<DepositService> depositServiceList = new ArrayList<>();
        for (Service service : serviceList) {
            DepositService depositService = new DepositService();
            depositService.setServiceName(service.getServiceName());
            depositService.setDepositCount(analysisService.findServiceCountByMonth(year, month, service.getId()));
            //该服务在本月的总托管天数
            int totalDays = analysisService.findServiceTotalDaysByMonth(year, month, service.getId());
            //该服务的单价
            double price = service.getPrice();
            //本月份该服务的收益
            depositService.setProfit(totalDays * price);
            depositServiceList.add(depositService);
        }
        return depositServiceList;
    }

    @GetMapping("/findByYear")
    public String toCurrentYear(Model model) {
        model.addAttribute("years",analysisService.findYears());
        return "eve-month-profit";
    }

    @PostMapping("/findProfitByYear")
    @ResponseBody
    public List<MonthAndProfit> findProfitByYear(Integer year) {
        List<MonthAndProfit> monthAndProfitList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            MonthAndProfit monthAndProfit = new MonthAndProfit();
            Double profit = analysisService.findProfitByYear(year, i);
            monthAndProfit.setMonth(i + "月");
            monthAndProfit.setProfit(profit);
            monthAndProfitList.add(monthAndProfit);
        }
        return monthAndProfitList;
    }
}
