package com.example.service;

import com.example.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author SongJunBao
 * @description:
 * @Date 2021/12/7 17:19
 * @Version 1.0
 * com.example.service
 */

public interface UserService {

    List<User> selectAll();

    User selectUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(int id);

    User selectUserByName(String name);

    List<User> selectAny(String userName, String name, Integer age, String sex, Integer isMry);

}
