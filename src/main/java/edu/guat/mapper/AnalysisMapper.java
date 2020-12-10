package edu.guat.mapper;

import edu.guat.po.*;

import java.util.List;

public interface AnalysisMapper {

    //查询系统中所有用户
    List<User> selectAllUser();

    //查询托管中的用户
    List<User> selectDepositUser();

    //查询查询托管中的用户的个数
    Integer selectUserDepositCount(String username);

    //查询托管中的宠物品种
    List<Breeds> selectDepositingBreeds();

    //查询宠物品种被托管的次数
    Integer selectDepositBreedsCount(Integer bid);

    //查询托管中的宠物粮食品牌
    List<PetFood> selectDepositingPetfood();

    //查询托管中的宠物粮食品牌的次数
    Integer selectDepositPetfoodCount(Integer pid);

    //查询托管中被选择的服务
    List<Service> selectDepositingService();

    //查询托管中的服务的个数
    Integer selectDepositServiceCount(Integer serviceId);

    //查询服务中的总费用
    Double selectTotalPrice();

    List<Breeds> selectBreedsByMonth(Integer year, Integer month);

    Integer selectBreedsCountByMonth(Integer year, Integer month, Integer speciesId);

    Integer selectBreedsTotalDaysByMonth(Integer year, Integer month, Integer speciesId);

    List<PetFood> selectPetfoodByMonth(Integer year,Integer month);

    Integer selectPetfoodCountByMonth(Integer year,Integer month, Integer foodId);

    Integer selectPetfoodTotalDaysByMonth(Integer year,Integer month, Integer foodId);

    List<Service> selectServiceByMonth(Integer year,Integer month);

    Integer selectServiceCountByMonth(Integer year,Integer month, Integer serviceId);

    Integer selectServiceTotalDaysByMonth(Integer year,Integer month, Integer serviceId);

    Double selectProfitByYear(Integer year, Integer month);

    List<Integer> selectYears();

}
