package edu.guat.service;

import edu.guat.po.*;

import java.util.List;

public interface AnalysisService {

    List<User> findAllUser();

    List<User> findDepositUser();

    Integer findUserDepositCount(String username);

    List<Breeds> findDepositingBreeds();

    Integer findDepositBreedsCount(Integer bid);

    List<PetFood> findDepositingPetfood();

    Integer findDepositPetfoodCount(Integer pid);

    List<Service> findDepositingService();

    Integer findDepositServiceCount(Integer serviceId);

    Double findTotalPrice();

    List<Breeds> findBreedsByMonth(Integer year, Integer month);

    Integer findBreedsCountByMonth(Integer year,Integer month, Integer speciesId);

    Integer findBreedsTotalDaysByMonth(Integer year,Integer month, Integer speciesId);

    List<PetFood> findPetfoodByMonth(Integer year,Integer month);

    Integer findPetfoodCountByMonth(Integer year,Integer month, Integer foodId);

    Integer findPetfoodTotalDaysByMonth(Integer year,Integer month, Integer fooId);

    List<Service> findServiceByMonth(Integer year,Integer month);

    Integer findServiceCountByMonth(Integer year,Integer month, Integer serviceId);

    Integer findServiceTotalDaysByMonth(Integer year,Integer month, Integer serviceId);

    Double findProfitByYear(Integer year, Integer month);

    List<Integer> findYears();

}
