package edu.guat.mapper;

import java.util.List;

import edu.guat.po.Service;

public interface ServiceMapper {

    List<Service> selectAll();

    List<Service> selectByPage();

    Integer insertService(Integer depositId, Integer serviceId);

    List<Service> selectService(Integer id);

    Integer updateService(Integer depositId, Integer serviceId);

    Integer deleteService(Integer depositId);

    Service selectById(Integer id);

    Integer update(Service service) throws Exception;

    Integer delete(Integer id) throws Exception;

    Integer add(Service service) throws Exception;
}
