package edu.guat.service;

import java.util.List;

import edu.guat.po.Keeper;

public interface KeeperService {

    List<Keeper> findAll();

    List<Keeper> findByPage(Integer pageNum, Integer pageSize);

    Keeper findById(Integer id);

    Integer update(Keeper keeper);

    Integer delete(Integer id);

    Integer add(Keeper keeper);

    Integer updatePicture(Keeper keeper);
}
