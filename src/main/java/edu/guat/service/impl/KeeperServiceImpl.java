package edu.guat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import edu.guat.mapper.KeeperMapper;
import edu.guat.po.Keeper;
import edu.guat.service.KeeperService;

@Service
public class KeeperServiceImpl implements KeeperService {

    @Autowired
    private KeeperMapper keeperMapper;

    @Override
    public List<Keeper> findAll() {
        return keeperMapper.selectAll();
    }

    @Override
    public List<Keeper> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return keeperMapper.selectByPage();
    }

    @Override
    public Keeper findById(Integer id) {
        return keeperMapper.selectById(id);
    }

    @Override
    public Integer update(Keeper keeper) {
        return keeperMapper.update(keeper);
    }

    @Override
    public Integer delete(Integer id) {
        return keeperMapper.delete(id);
    }

    @Override
    public Integer add(Keeper keeper) {
        return keeperMapper.add(keeper);
    }

    @Override
    public Integer updatePicture(Keeper keeper) {
        return keeperMapper.updatePicture(keeper);
    }
}
