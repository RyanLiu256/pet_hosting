package edu.guat.service;

import edu.guat.po.User;

import java.util.List;

public interface UserService {

    User findOne(User user);

    Boolean add(User user);

    List<User> findAll(Integer id, String username, String address, Integer pageNum, Integer pageSize);

    User findById(Integer id);

    Integer update(User user) throws Exception;

    Integer delete(Integer id);

    Integer updatePassword(User user);

    User findForUpdatePwd(User user);
}
