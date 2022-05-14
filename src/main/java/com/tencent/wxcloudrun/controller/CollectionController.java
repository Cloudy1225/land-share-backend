package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.config.Response;
import com.tencent.wxcloudrun.dto.CollectionDto;
import com.tencent.wxcloudrun.model.po.CollectionPO;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.vo.CollectionVO;
import com.tencent.wxcloudrun.service.CollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController // REST形式，返回json等，不返回整个页面
@RequestMapping("/collection")
public class CollectionController {

    private final CollectionService collectionService;
    private final Logger logger;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
        this.logger = LoggerFactory.getLogger(CollectionController.class);
    }

    // 获取我的收藏
    @GetMapping("/getMyCollection")
    public Response getMyCollection(@RequestHeader("X-WX-OPENID") String openid){
        logger.info("/collection/getMyCollection get request, 目的：获取我的收藏土地，用户openid: {}", openid);

        CollectionVO myCollection = collectionService.getMyCollection(openid);

        return Response.buildSuccess("我的收藏获取成功", myCollection);
    }

    // 添加某一土地至我的收藏
    @GetMapping("/addMyCollection")
    public Response addMyCollection(@RequestParam Integer lid, @RequestHeader("X-WX-OPENID") String openid){
        logger.info("/collection/addMyCollection get request, 目的：添加土地至我的收藏，用户openid: {}，土地lid: {}", openid, lid);

        CollectionPO collectionPO = new CollectionPO(null, openid, lid, null);

        try{
            collectionService.insertCollection(collectionPO);
            return Response.buildSuccess("成功收藏土地");
        }catch (Exception e){
            logger.info("土地收藏失败异常: {}", e.getLocalizedMessage());
            return Response.buildFailed("10005", "土地收藏失败");
        }
    }

    // 判断是否收藏了指定土地
    @GetMapping("/isCollected")
    public Response isCollected(@RequestParam Integer lid, @RequestHeader("X-WX-OPENID") String openid){
        logger.info("/collection/isCollected get request, 目的：判断是否收藏了该土地，用户openid: {}，土地lid: {}", openid, lid);

        CollectionDto collectionDto = new CollectionDto(openid, lid);
        CollectionPO collectionPO =  collectionService.selectByOpenidAndLid(collectionDto);

        try {
            if(collectionPO != null){
                return Response.buildSuccess("土地已收藏", true);
            }else {
                return Response.buildSuccess("土地未收藏", false);
            }
        }catch (Exception e){
            return Response.buildSuccess("土地不存在", false);
        }
    }

    // 从我的收藏中去除指定土地
    @GetMapping("/deleteMyCollection")
    public Response deleteMyCollection(@RequestParam(value = "cid", required = false) Integer cid, @RequestParam(value = "lid", required = false) Integer lid, @RequestHeader("X-WX-OPENID") String openid){
        if (cid != null) {
            logger.info("/collection/deleteMyCollection get request, 目的：从我的收藏中去除指定土地，用户openid: {}，收藏cid: {}", openid, cid);

            collectionService.deleteByCid(cid);
        }else {
            logger.info("/collection/deleteMyCollection get request, 目的：从我的收藏中去除指定土地，用户openid: {}，土地lid: {}", openid, lid);

            CollectionDto collectionDto = new CollectionDto(openid, lid);
            collectionService.deleteByOpenidAndLid(collectionDto);
        }

        return Response.buildSuccess("删除该收藏成功");
    }

}
