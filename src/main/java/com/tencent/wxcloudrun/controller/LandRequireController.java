package com.tencent.wxcloudrun.controller;
import com.tencent.wxcloudrun.config.Response;
import com.tencent.wxcloudrun.dto.LandFilterDto;
import com.tencent.wxcloudrun.model.po.LandRequirePO;
import com.tencent.wxcloudrun.model.vo.LandPostVO;
import com.tencent.wxcloudrun.model.vo.LandRequireVO;
import com.tencent.wxcloudrun.service.LandRequireService;
import com.tencent.wxcloudrun.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/landRequire")
class LandRequireController {
    private final LandRequireService landRequireService;
    private final SearchService searchService;
    private final Logger logger;

    @Autowired
    public LandRequireController(LandRequireService landRequireService, SearchService searchService) {
        this.landRequireService = landRequireService;
        this.searchService = searchService;
        this.logger = LoggerFactory.getLogger(LandRequireController.class);
    }

    // 新建土地需求
    @PostMapping("/createLandRequire")
    public Response createLandRequire(@RequestBody LandRequireVO landRequireVO, @RequestHeader("X-WX-OPENID") String openid)
    {
        logger.info("/landRequire/createLandRequire post request, 目的：新建土地需求，用户openid: {}", openid);

        LandRequirePO landRequirePO = new LandRequirePO();
        BeanUtils.copyProperties(landRequireVO, landRequirePO);
        landRequirePO.setOpenid(openid); // Body中不携带openid，要从Header中获取
        try {
            landRequireService.createLandRequire(landRequirePO);
            return Response.buildSuccess("土地需求信息已上传");
        }catch (Exception e){
            logger.info(String.valueOf(e));
            return Response.buildFailed("10002", "土地信息不完整");
        }
    }

    // 获取我的土地需求
    @GetMapping("/getMyLandRequires")
    public Response getMyLandRequires(@RequestHeader("X-WX-OPENID") String openid){
        logger.info("/landRequire/getMyLandRequires get request, 目的：获取我的土地需求，用户openid: {}", openid);

        ArrayList<LandRequireVO> myLandRequires = landRequireService.getMyLandRequires(openid);
        return Response.buildSuccess("我的土地需求获取成功", myLandRequires);
    }

    // 更新土地需求信息
    @PostMapping("/updateLandRequire")
    public Response updateLandRequire(@RequestBody LandRequireVO landRequireVO, @RequestHeader("X-WX-OPENID") String openid){
        logger.info("/landRequire/updateLandRequire post request, 目的：更新土地需求信息，用户openid: {}", openid);

        LandRequirePO landRequirePO = new LandRequirePO();
        BeanUtils.copyProperties(landRequireVO, landRequirePO);
        // landPostPO.setOpenid(openid); // Body中不携带openid，要从Header中获取
        try {
            landRequireService.updateLandRequire(landRequirePO);
            return Response.buildSuccess("土地需求信息已更新");
        }catch (Exception e){
            return Response.buildFailed("10002", "土地信息不完整");
        }
    }

    // 删除土地需求
    @GetMapping("/deleteLandRequire")
    public Response delLandRequires(@RequestParam Integer lrid, @RequestHeader("X-WX-OPENID") String openid){
        logger.info("/landRequire/deleteLandRequire get request, 目的：删除指定土地需求，用户openid: {}，土地lid: {}", openid, lrid);
        int hasLandRequire = landRequireService.deleteLandRequire(lrid);

        if(hasLandRequire == 0){
            return Response.buildSuccess("土地需求不存在");
        }
        return Response.buildSuccess("删除成功");
    }

    // 获取10条土地需求
    @GetMapping("/getLandRequires")
    public Response getLandRequires(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime submitTime){
        logger.info("/landRequires/getLandRequires get request, 目的：获取10条土地需求");
        ArrayList<LandRequireVO> landRequires = landRequireService.get10LandRequire(submitTime);
        return Response.buildSuccess("土地需求获取成功", landRequires);
    }

    // 按筛选条件获取10条土地需求
    @PostMapping("/getLandRequires")
    public Response getLandRequires(@RequestBody LandFilterDto landFilterDto){
        logger.info("/landRequire/getLandRequires post request, 目的：按筛选条件获取10条土地需求");

        ArrayList<LandRequireVO> landRequires = landRequireService.getLandRequiresByFilters(landFilterDto);

        return Response.buildSuccess("土地需求获取成功", landRequires);
    }

    // 根据用户输入搜索土地
    @PostMapping("/searchLandRequires")
    public Response searchLandPosts(@RequestBody String input){
        logger.info("/landRequire/searchLandRequires post request, 目的：根据用户输入搜索土地需求");

        ArrayList<LandPostVO> landPosts = searchService.searchLandPosts(input);

        return Response.buildSuccess("搜索土地需求成功", landPosts);
    }


}