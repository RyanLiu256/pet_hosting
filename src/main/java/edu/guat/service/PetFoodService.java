package edu.guat.service;

import edu.guat.po.PetFood;

import java.util.List;

public interface PetFoodService {

    List<PetFood> findAll();

    List<PetFood> findByPage(Integer pageNum, Integer pageSize);

    PetFood findById(Integer id);

    Integer update(PetFood petFood) throws Exception;

    Integer delete(Integer id) throws Exception;

    Integer add(PetFood petFood) throws Exception;
}
