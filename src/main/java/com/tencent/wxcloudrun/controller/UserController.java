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

@RestController // REST形式，返回json等，不返回整个页面
@RequestMapping("/my")
public class UserController {

    private final UserService userService;
    private final Logger logger;

    @Autowired //自动注入Service对象
    public UserController(UserService userService){
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(CounterController.class);
    }

    @GetMapping("/loginOrRegister")
    public Response userLoginOrRegister(@RequestHeader("X-WX-OPENID") String openid) {
        logger.info("/my/loginOrRegister get request, openid: {}", openid);

        User user = userService.findByOpenid(openid);
        /*if (user != null) {
            throw new MyServiceException("A0000", "用户名已存在");
        }*/
        if(user != null){
            return Response.buildSuccess("用户已存在，直接登录");
        }
        /*UserVO userVO = new UserVO();
        userVO.setOpenid(openid);
        User newUser = new User();
        BeanUtils.copyProperties(userVO, newUser);*/
        User newUser = new User();
        newUser.setOpenid(openid);
        userService.createUser(newUser);
        return Response.buildSuccess("自动注册，直接登录");
    }
}
