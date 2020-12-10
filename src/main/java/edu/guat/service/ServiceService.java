package edu.guat.service;

import java.util.List;

import edu.guat.po.Service;

public interface ServiceService {

    List<Service> findAll();

    List<Service> findByPage(Integer pageNum, Integer pageSize);

    Integer addService(Integer depositId, Integer serviceId);

    List<Service> findService(Integer id);

    Integer updateService(Integer depositId, Integer serviceId);

    Integer deleteService(Integer depositId);

    Service findById(Integer id);

    Integer update(Service service) throws Exception;

    Integer delete(Integer id) throws Exception;

    Integer add(Service service) throws Exception;
}
