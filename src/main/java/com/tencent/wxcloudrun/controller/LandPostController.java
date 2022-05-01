package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.config.Response;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.vo.LandPostVO;
import com.tencent.wxcloudrun.service.LandPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
