package edu.guat.mapper;

import edu.guat.po.Breeds;

import java.util.List;

public interface BreedsMapper {

    List<Breeds> selectAll();

    List<Breeds> selectByPage();

    Breeds selectById(Integer id);

    Integer update(Breeds breeds) throws Exception;

    Integer delete(Integer id) throws Exception;

    Integer add(Breeds breeds) throws Exception;

    Integer updatePicture(Breeds breeds);
}
