package edu.guat.service.impl;

import com.github.pagehelper.PageHelper;
import edu.guat.mapper.UserMapper;
import edu.guat.po.User;
import edu.guat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findOne(User user) {
        return userMapper.select(user);
    }

    @Override
    public Boolean add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> findAll(Integer id,String username,String address, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return userMapper.selectAll(id,username,address);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public Integer update(User user) throws Exception {
        return userMapper.update(user);
    }

    @Override
    public Integer delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public Integer updatePassword(User user) {
        return userMapper.updatePassword(user);
    }

    @Override
    public User findForUpdatePwd(User user) {
        return userMapper.selectForUpdatePwd(user);
    }

}
