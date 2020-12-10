package edu.guat.mapper;

import edu.guat.po.User;

import java.util.List;

public interface UserMapper {

    User select(User user);

    Boolean insert(User user);

    List<User> selectAll(Integer id, String username, String address);

    User selectById(Integer id);

    Integer update(User user) throws Exception;

    Integer delete(Integer id);

    Integer updatePassword(User user);

    User selectForUpdatePwd(User user);
}
