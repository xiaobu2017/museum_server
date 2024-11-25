package com.museum.controller;


import com.museum.config.JsonResult;
import com.museum.config.PageResult;
import com.museum.damain.po.MsUser;
import com.museum.damain.query.PageQuery;
import com.museum.service.impl.UserService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/getdata")
    public JsonResult getdata(@RequestBody PageQuery pageQuery) {
        PageResult<MsUser> users = userService.listUserPage(pageQuery);
        return JsonResult.result(users);
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody MsUser user) {
        MsUser dbUser = userService.login(user);
        if(dbUser == null) {
            return JsonResult.failResult("用户名密码错误！");
        }
        return  JsonResult.result(dbUser);
    }

    @PostMapping("/register")
    public JsonResult register(@RequestBody MsUser msUser) {
        try {
            userService.saveMsUser(msUser);
            return JsonResult.result("成功！");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/editUserInfo")
    public JsonResult editUserInfo(@RequestBody MsUser msUser) {
        try {
            userService.editUserInfo(msUser);
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }
    @PostMapping("/deluser")
    public JsonResult deluser(@RequestBody MsUser msUser) {
        try {
            if(msUser.getId() == null) {
                throw new Exception("ID不允许为空！！！");
            }
            userService.deluser(msUser.getId());
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }
}
