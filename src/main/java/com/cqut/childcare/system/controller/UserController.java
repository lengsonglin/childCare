package com.cqut.childcare.system.controller;

import com.cqut.childcare.common.domain.vo.ApiResult;
import com.cqut.childcare.common.exception.CommonErrorEnum;
import com.cqut.childcare.system.domain.dto.UserInfoDto;
import com.cqut.childcare.system.domain.entity.User;
import com.cqut.childcare.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @Author Faiz
 * @ClassName UserController
 * @Version 1.0
 */
@Api(value = "管理员相关接口", tags = {"管理员相关接口"})
@RestController
@RequestMapping("api/system/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "修改管理员信息")
    @PostMapping("/update")
    public ResponseEntity<?> updateUserInfo(@Valid @RequestBody UserInfoDto userInfoDto) {
        boolean success = userService.updateUserInfo(userInfoDto);
        return success ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("更新失败");
    }

    @ApiOperation(value = "管理员登录")
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(String account,String password){
        User user = userService.lambdaQuery()
                .eq(User::getAccount, account)
                .eq(User::getPassword, DigestUtils.md5DigestAsHex(password.getBytes()))
                .one();
        if(Objects.nonNull(user))
            return ResponseEntity.ok(user);
        return ResponseEntity.badRequest().body("用户名或者密码错误!!");
    }

    @ApiOperation(value = "获取所有管理员信息")
    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser(){

        List<User> list = userService.lambdaQuery().list();
        return ResponseEntity.ok(list);
    }
} 