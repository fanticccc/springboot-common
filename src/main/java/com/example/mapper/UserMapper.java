package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
//此注解表示mybatis 的一个 mapper 类(在springboot启动类指定了扫描包,这里就可以不用mapper注解)

@Repository
public interface UserMapper {

    List<User> selectAll();

    User selectUserById(int id);

    User selectUserByName(String name);

    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(int id);

    List<User> selectAny(@Param("userName") String userName,
                         @Param("name") String name,
                         @Param("age") Integer age,
                         @Param("sex") String sex,
                         @Param("isMry") Integer isMry);

}
