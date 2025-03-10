package com.chai.weblog.admin.controller;

import com.chai.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.chai.weblog.admin.service.AdminUserService;
import com.chai.weblog.common.aspect.ApiOperationLog;
import com.chai.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@Api(tags = "admin用户模块")
public class AdminUserController {
    @Autowired
    AdminUserService userService;
    @PostMapping("/password/update")
    @ApiOperation(value = "修改用户密码")
    @ApiOperationLog(description = "修改用户密码")
    public Response updatePassWord(@RequestBody@Validated UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO){
        return userService.updatePassword(updateAdminUserPasswordReqVO);
    }
    @PostMapping("/user/info")
    @ApiOperation(value = "获取用户信息")
    @ApiOperationLog(description = "获取用户信息")
    public Response findUserInfo() {
        return userService.findUserInfo();
    }


}
