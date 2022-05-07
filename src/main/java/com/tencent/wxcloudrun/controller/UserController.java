package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.Response;
import com.tencent.wxcloudrun.exception.MyServiceException;
import com.tencent.wxcloudrun.model.po.User;
import com.tencent.wxcloudrun.model.vo.UserVO;
import com.tencent.wxcloudrun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController // REST形式，返回json等，不返回整个页面
@RequestMapping("/my")
public class UserController {

    private final UserService userService;
    private final Logger logger;

    @Autowired //自动注入Service对象
    public UserController(UserService userService){
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    /**
     * 登录或注册
     * @param openid
     * @return 用户角色
     */
    @GetMapping("/loginOrRegister")
    public Response userLoginOrRegister(@RequestHeader("X-WX-OPENID") String openid) {
        logger.info("/my/loginOrRegister get request, 目的：登录或注册，用户openid: {}", openid);

        User user = userService.findByOpenid(openid);
        HashMap<String, String> roleAndTele = new HashMap<>();
        if(user != null){
            roleAndTele.put("role", user.getRole().toString());
            roleAndTele.put("telenumber", user.getTelenumber());
            String msg = null;
            if(user.getRole() == '1'){
                msg = "未实名用户，直接登录";
            }else {
                msg = "已实名用户，直接登录";
            }
            return Response.buildSuccess(msg, roleAndTele);
        }
        /*UserVO userVO = new UserVO();
        userVO.setOpenid(openid);
        User newUser = new User();
        BeanUtils.copyProperties(userVO, newUser);*/
        User newUser = new User();
        newUser.setOpenid(openid);
        userService.createUser(newUser);
        roleAndTele.put("role", "1");
        roleAndTele.put("telenumber", "");
        return Response.buildSuccess("自动注册，直接登录", roleAndTele);
    }

    // 注销用户
    @GetMapping("/deleteUser")
    public Response userDelete(@RequestHeader("X-WX-OPENID") String openid) {
        logger.info("/my/deleteUser get request, 目的：注销用户，用户openid: {}", openid);

        int hasUser = userService.deleteUser(openid);
        if(hasUser == 0){
            return Response.buildFailed("10001", "用户不存在");
        }
        return Response.buildSuccess("用户已注销");
    }

    // 实名认证
    @PostMapping("/realName")
    public Response userRealName(@RequestBody UserVO userVO, @RequestHeader("X-WX-OPENID") String openid){
        logger.info("/my/realName post request, 目的：实名，用户openid: {}", openid);

        User userPO = new User();
        BeanUtils.copyProperties(userVO, userPO);
        userPO.setOpenid(openid); // Body中不携带openid，要从Header中获取
        userService.realName(userPO);
        return Response.buildSuccess("用户已实名");
    }

    // 获取实名信息
    @GetMapping("/getUserInfo")
    public Response getUserInfo(@RequestHeader("X-WX-OPENID") String openid){
        logger.info("/my/getUserInfo get request, 目的：获取实名信息，用户openid: {}", openid);

        User user = userService.findByOpenid(openid);
//        System.out.println(user);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Response.buildSuccess("实名信息获取成功", userVO);
    }
}
