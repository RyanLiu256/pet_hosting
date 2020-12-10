package edu.guat.mapper;

import java.util.List;

import edu.guat.po.Keeper;

public interface KeeperMapper {

    List<Keeper> selectAll();

    List<Keeper> selectByPage();

    Keeper selectById(Integer kid);

    Integer update(Keeper keeper);

    Integer delete(Integer kid);

    Integer add(Keeper keeper);

    Integer updatePicture(Keeper keeper);
}
