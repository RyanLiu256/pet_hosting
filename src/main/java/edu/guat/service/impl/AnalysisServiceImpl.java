package edu.guat.service.impl;

import edu.guat.mapper.AnalysisMapper;
import edu.guat.po.*;
import edu.guat.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public List<User> findAllUser() {
        return analysisMapper.selectAllUser();
    }

    @Override
    public List<User> findDepositUser() {
        return analysisMapper.selectDepositUser();
    }

    @Override
    public Integer findUserDepositCount(String username) {
        return analysisMapper.selectUserDepositCount(username);
    }

    @Override
    public List<Breeds> findDepositingBreeds() {
        return analysisMapper.selectDepositingBreeds();
    }

    @Override
    public Integer findDepositBreedsCount(Integer bid) {
        return analysisMapper.selectDepositBreedsCount(bid);
    }

    @Override
    public List<PetFood> findDepositingPetfood() {
        return analysisMapper.selectDepositingPetfood();
    }

    @Override
    public Integer findDepositPetfoodCount(Integer pid) {
        return analysisMapper.selectDepositPetfoodCount(pid);
    }

    @Override
    public List<Service> findDepositingService() {
        return analysisMapper.selectDepositingService();
    }

    @Override
    public Integer findDepositServiceCount(Integer serviceId) {
        return analysisMapper.selectDepositServiceCount(serviceId);
    }

    @Override
    public Double findTotalPrice() {
        return analysisMapper.selectTotalPrice();
    }

    @Override
    public List<Breeds> findBreedsByMonth(Integer year,Integer month) {
        return analysisMapper.selectBreedsByMonth(year,month);
    }

    @Override
    public Integer findBreedsCountByMonth(Integer year,Integer month, Integer speciesId) {
        return analysisMapper.selectBreedsCountByMonth(year,month,speciesId);
    }

    @Override
    public Integer findBreedsTotalDaysByMonth(Integer year,Integer month, Integer speciesId) {
        return analysisMapper.selectBreedsTotalDaysByMonth(year,month,speciesId);
    }

    @Override
    public List<PetFood> findPetfoodByMonth(Integer year,Integer month) {
        return analysisMapper.selectPetfoodByMonth(year,month);
    }

    @Override
    public Integer findPetfoodCountByMonth(Integer year,Integer month, Integer speciesId) {
        return analysisMapper.selectPetfoodCountByMonth(year,month,speciesId);
    }

    @Override
    public Integer findPetfoodTotalDaysByMonth(Integer year,Integer month, Integer fooId) {
        return analysisMapper.selectPetfoodTotalDaysByMonth(year,month,fooId);
    }

    @Override
    public List<Service> findServiceByMonth(Integer year,Integer month) {
        return analysisMapper.selectServiceByMonth(year,month);
    }

    @Override
    public Integer findServiceCountByMonth(Integer year,Integer month, Integer serviceId) {
        return analysisMapper.selectServiceCountByMonth(year,month,serviceId);
    }

    @Override
    public Integer findServiceTotalDaysByMonth(Integer year,Integer month, Integer serviceId) {
        return analysisMapper.selectServiceTotalDaysByMonth(year,month,serviceId);
    }

    @Override
    public Double findProfitByYear(Integer year, Integer month) {
        return analysisMapper.selectProfitByYear(year,month);
    }

    @Override
    public List<Integer> findYears() {
        return analysisMapper.selectYears();
    }


}
