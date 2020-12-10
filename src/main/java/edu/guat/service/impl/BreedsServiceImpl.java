package edu.guat.service.impl;

import com.github.pagehelper.PageHelper;
import edu.guat.mapper.BreedsMapper;
import edu.guat.po.Breeds;
import edu.guat.service.BreedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedsServiceImpl implements BreedsService {

    @Autowired
    private BreedsMapper breedsMapper;

    @Override
    public List<Breeds> findAll() {
        return breedsMapper.selectAll();
    }

    @Override
    public List<Breeds> findByPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return breedsMapper.selectByPage();
    }

    @Override
    public Breeds findById(Integer id) {
        return breedsMapper.selectById(id);
    }

    @Override
    public Integer update(Breeds breeds) throws Exception {
        return breedsMapper.update(breeds);
    }

    @Override
    public Integer delete(Integer id) throws Exception {
        return breedsMapper.delete(id);
    }

    @Override
    public Integer add(Breeds breeds) throws Exception {
        return breedsMapper.add(breeds);
    }

    @Override
    public Integer updatePicture(Breeds breeds) {
        return breedsMapper.updatePicture(breeds);
    }
}
