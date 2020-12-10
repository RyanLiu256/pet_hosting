package edu.guat.service;

import edu.guat.po.Breeds;

import java.util.List;

public interface BreedsService {

    List<Breeds> findAll();

    List<Breeds> findByPage(Integer pageNum,Integer pageSize);

    Breeds findById(Integer id);

    Integer update(Breeds breeds) throws Exception;

    Integer delete(Integer id) throws Exception;

    Integer add(Breeds breeds) throws Exception;

    Integer updatePicture(Breeds breeds);
}
