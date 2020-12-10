package edu.guat.service.impl;

import com.github.pagehelper.PageHelper;
import edu.guat.mapper.PetFoodMapper;
import edu.guat.po.PetFood;
import edu.guat.service.PetFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetFoodServiceImpl implements PetFoodService {

    @Autowired
    private PetFoodMapper petFoodMapper;

    @Override
    public List<PetFood> findAll() {
        return petFoodMapper.selectAll();
    }

    @Override
    public List<PetFood> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return petFoodMapper.selectByPage();
    }

    @Override
    public PetFood findById(Integer id) {
        return petFoodMapper.selectById(id);
    }

    @Override
    public Integer update(PetFood petFood) throws Exception {
        return petFoodMapper.update(petFood);
    }

    @Override
    public Integer delete(Integer id) throws Exception{
        return petFoodMapper.delete(id);
    }

    @Override
    public Integer add(PetFood petFood) throws Exception{
        return petFoodMapper.add(petFood);
    }
}
