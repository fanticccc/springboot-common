package com.example.controller;

import com.example.annotation.SysLog;
import com.example.cache.UserCache;
import com.example.constant.ResultStatus;
import com.example.config.MyCache;
import com.example.pojo.AnyUserResultRes;
import com.example.constant.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户管理", tags = "用户页")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserCache userCache;

    /**
     * 查新所有
     *
     * @return
     */
    @CrossOrigin
    @GetMapping("/selectAll")
    @SysLog("查询所有用户")
    @ApiOperation(notes = "查询所有用户", value = "查询所有用户")
    public AnyUserResultRes selectAll(
            @ApiParam(value = "分页pageSize") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "分页pageNum,页码1开始") @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum
    ) {
        // Result <List<User>> result = new Result<>();
        AnyUserResultRes result = new AnyUserResultRes();
        userService.selectAll();
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.selectAll();
        result.setCode(Result.SUCCESS);
        result.setContent(list);
        result.setTotal(list.size());
        result.setCurrentPage(pageNum);
        result.setPageSize(pageSize);
        return result;
    }

    /**
     * 测试 springboot 缓存
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping("selectUser")
    @MyCache
    @ApiOperation(notes = "查询用户测试缓存", value = "查询用户测试缓存")
    public Result selectOne(@RequestParam(value = "id") Integer id) {
        User user = userService.selectUserById(id);
        System.out.println("cache used in this method-----");
        if (ObjectUtils.isEmpty(user)) {
            return new Result().setCode(ResultStatus.FAIL.getCode()).setMessage("该用户不存在");
        }
        return new Result().setCode(ResultStatus.SUCCESS.getCode()).setMessage(ResultStatus.SUCCESS.getMsg()).setContent(user);
    }
    /**
     * 查看缓存信息
     * @return
     */
    /*private User getCache(){
        Cache cache = cacheManage.getCache();

    }*/

    /**
     * 按条件查询符合要求用户
     *
     * @param userName
     * @param name
     * @param age
     * @param sex
     * @param isMry
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/selectAny")
    @CrossOrigin
    @SysLog("按条件查询用户")

    public AnyUserResultRes selectAny(
            @ApiParam(value = "用户名") @RequestParam(value = "userName", required = false) String userName,
            @ApiParam(value = "真实姓名") @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "年龄") @RequestParam(value = "age", required = false) Integer age,
            @ApiParam(value = "性别") @RequestParam(value = "sex", required = false) String sex,
            @ApiParam(value = "是否结婚") @RequestParam(value = "isMry", required = false) Integer isMry,
            @ApiParam(value = "分页pageSize") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "分页pageNum,页码1开始") @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum
    ) {
        AnyUserResultRes result = new AnyUserResultRes();
        int sum = 0;
        List<User> usersList = Lists.newArrayList();
        usersList = userService.selectAny(userName, name, age, sex, isMry);
        if (!CollectionUtils.isEmpty(usersList)) {
            sum = usersList.size();
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            usersList = userService.selectAny(userName, name, age, sex, isMry);
            result.setCode(ResultStatus.SUCCESS.getCode());
            result.setMessage(ResultStatus.SUCCESS.getMsg());
            result.setTotal(sum);
            result.setCurrentPage(pageNum);
            result.setPageSize(pageSize);
            result.setContent(usersList);
            for (User user : usersList) {
                userCache.add(user.getName(), user);
            }
            return result;
        } catch (Exception ex) {
            log.error("Method: Controller User selectAny  error:{}", ex.getMessage());
            result.setCode(ResultStatus.FAIL.getCode());
            result.setMessage(ex.getMessage());
            return result;
        }
    }

    /**
     * 测试查询缓存
     *
     * @param name key
     * @return user
     */
    @SysLog("测试缓存")
    @GetMapping("selectOne")
    public Result selectOne(@RequestParam(value = "name") String name) {
        //先查缓存，不存在再查库
        User user = userCache.get(name);
        if (ObjectUtils.isEmpty(user)) {
            System.out.println("cache has not this data");
            user = userService.selectUserByName(name);
        }
        if (ObjectUtils.isEmpty(user)) {
            return new Result().setCode(ResultStatus.FAIL.getCode()).setMessage("该用户不存在");
        }
        return new Result().setCode(ResultStatus.SUCCESS.getCode()).setMessage(ResultStatus.SUCCESS.getMsg()).setContent(user);
    }

    /**
     * 删除用户
     *
     * @return
     */
    @CrossOrigin
    @PostMapping("/deleteUser")
    @ApiOperation(notes = "删除用户", value = "删除用户")
    public String deleteUser(@RequestBody String id) {
        try {
            userService.deleteUserById(Integer.parseInt(id));
            return "用户id为" + id + "的用户删除成功！";
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    @CrossOrigin
    @PostMapping("/updateOrAddUser")
    @ApiOperation(notes = "更新用户", value = "更新用户")
    public String addUser(@RequestBody User user) {
        log.info(user.getName());
        try {
            userService.addUser(new User(18, "cmo", "小黄ya", 188, "男", 0));
            return ResultStatus.SUCCESS.getMsg();
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    @CrossOrigin
    @PostMapping("/addAll")
    @Transactional(rollbackFor = Exception.class)
    public Boolean addAll(List<User> list) {
        list.forEach(a -> {
            userService.addUser(a);
        });
        return null;
    }
}
