package edu.guat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;

import edu.guat.mapper.ServiceMapper;
import edu.guat.po.Service;
import edu.guat.service.ServiceService;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceMapper serviceMapper;


    @Override
    public List<Service> findAll() {
        return serviceMapper.selectAll();
    }

    @Override
    public List<Service> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return serviceMapper.selectByPage();
    }

    @Override
    public Integer addService(Integer depositId, Integer serviceId) {
        return serviceMapper.insertService(depositId,serviceId);
    }

    @Override
    public List<Service> findService(Integer id) {
        return serviceMapper.selectService(id);
    }

    @Override
    public Integer updateService(Integer depositId, Integer serviceId) {
        return serviceMapper.updateService(depositId,serviceId);
    }

    @Override
    public Integer deleteService(Integer depositId) {
        return serviceMapper.deleteService(depositId);
    }

    @Override
    public Service findById(Integer id) {
        return serviceMapper.selectById(id);
    }

    @Override
    public Integer update(Service service) throws Exception {
        return serviceMapper.update(service);
    }

    @Override
    public Integer delete(Integer id) throws Exception {
        return serviceMapper.delete(id);
    }

    @Override
    public Integer add(Service service) throws Exception {
        return serviceMapper.add(service);
    }
}
