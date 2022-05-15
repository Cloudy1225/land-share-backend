package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.config.Response;
import com.tencent.wxcloudrun.dto.LandFilterDto;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.vo.LandPostVO;
import com.tencent.wxcloudrun.service.LandPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController // REST形式，返回json等，不返回整个页面
@RequestMapping("/landPost")
public class LandPostController {
    private final LandPostService landPostService;
    private final Logger logger;

    @Autowired //自动注入Service对象
    public LandPostController(LandPostService landPostService) {
        this.landPostService = landPostService;
        this.logger = LoggerFactory.getLogger(LandPostController.class);
    }

    // 新建土地发布
    @PostMapping("/createLandPost")
    public Response createLandPost(@RequestBody LandPostVO landPostVO, @RequestHeader("X-WX-OPENID") String openid){
        logger.info("/landPost/createLandPost post request, 目的：新建土地发布，用户openid: {}", openid);

        LandPostPO landPostPO = new LandPostPO();
        BeanUtils.copyProperties(landPostVO, landPostPO);
        landPostPO.setOpenid(openid); // Body中不携带openid，要从Header中获取
        try {
            landPostService.createLandPost(landPostPO);
            return Response.buildSuccess("土地信息已上传，待审核");
        }catch (Exception e){
            return Response.buildFailed("10002", "土地信息不完整");
        }

    }

    // 获取我的土地发布
    @GetMapping("/getMyLandPosts")
    public Response getMyLandPosts(@RequestHeader("X-WX-OPENID") String openid){
        logger.info("/landPost/getMyLandPosts get request, 目的：获取我的土地发布，用户openid: {}", openid);

        ArrayList<LandPostVO> myLandPosts = landPostService.getMyLandPosts(openid);
        return Response.buildSuccess("我的土地发布获取成功", myLandPosts);
    }

    // 更新土地发布信息
    @PostMapping("/updateLandPost")
    public Response updateLandPost(@RequestBody LandPostVO landPostVO, @RequestHeader("X-WX-OPENID") String openid){
        logger.info("/landPost/updateLandPost post request, 目的：更新土地发布信息，用户openid: {}", openid);

        LandPostPO landPostPO = new LandPostPO();
        BeanUtils.copyProperties(landPostVO, landPostPO);
        // landPostPO.setOpenid(openid); // Body中不携带openid，要从Header中获取
        try {
            landPostService.updateLandPost(landPostPO);
            return Response.buildSuccess("土地信息已更新，待审核");
        }catch (Exception e){
            return Response.buildFailed("10002", "土地信息不完整");
        }
    }

    // 删除土地发布
    @GetMapping("/deleteLandPost")
    public Response getMyLandPosts(@RequestParam Integer lid, @RequestHeader("X-WX-OPENID") String openid){
        logger.info("/landPost/deleteMyLandPost get request, 目的：删除指定土地发布，用户openid: {}，土地lid: {}", openid, lid);

        int hasLandPost = landPostService.deleteLandPost(lid);
        if(hasLandPost == 0){
            return Response.buildSuccess("土地不存在");
        }
        return Response.buildSuccess("删除成功");
    }

    // 获取10条土地发布
    @GetMapping("/getLandPosts")
    public Response getLandPosts(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime submitTime){
        logger.info("/landPost/getLandPosts get request, 目的：获取10条土地发布");


        ArrayList<LandPostVO> landPosts = landPostService.get10LandPosts(submitTime);

        return Response.buildSuccess("土地发布获取成功", landPosts);
    }

    //
    @PostMapping("/getLandPosts")
    public Response getLandPosts(@RequestBody LandFilterDto landFilterDto){
        logger.info("/landPost/getLandPosts post request, 目的：按筛选条件获取10条土地发布");

        ArrayList<LandPostVO> landPosts = landPostService.getLandPostsByFilters(landFilterDto);

        return Response.buildSuccess("土地发布获取成功", landPosts);
    }


}

