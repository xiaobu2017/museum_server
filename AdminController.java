package com.museum.controller;


import com.museum.config.JsonResult;
import com.museum.damain.po.Admin;
import com.museum.service.impl.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2023-12-19
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin")
@RequiredArgsConstructor
public class AdminController {

    @Resource
    AdminService adminService;

    @ApiOperation("查询管理员")
    @PostMapping("/login")
    public JsonResult queryAdmin(@RequestBody Admin user) {
        String userName = user.getUsername();
        Admin admin = adminService.queryAdmin(userName);
        if(admin != null) {
            return JsonResult.result(admin);
        }else {
            return JsonResult.failResult("用户名或密码错误！");
        }
    }
}
