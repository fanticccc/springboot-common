package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author SongJunBao
 * @description:
 * @Date 2021/12/7 17:20
 * @Version 1.0
 * com.example.service
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public User selectUserById(int id) {
        User user = userMapper.selectUserById(id);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public List<User> selectAny(String userName, String name, Integer age, String sex, Integer isMry) {
        List<User> users = Lists.newArrayList();
        try {
            users = userMapper.selectAny(userName, name, age, sex, isMry);
            return users;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> checkExcUser() {
        return null;
    }
}
