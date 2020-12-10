package edu.guat.mapper;

import edu.guat.po.PetFood;

import java.util.List;

public interface PetFoodMapper {

    List<PetFood> selectAll();

    List<PetFood> selectByPage();

    PetFood selectById(Integer id);

    Integer update(PetFood petFood) throws Exception;

    Integer delete(Integer id) throws Exception;

    Integer add(PetFood petFood) throws Exception;
}
