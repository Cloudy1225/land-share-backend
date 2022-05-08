package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.config.Response;
import com.tencent.wxcloudrun.model.po.ArticlePO;
import com.tencent.wxcloudrun.model.vo.ArticleVO;
import com.tencent.wxcloudrun.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController // REST形式，返回json等，不返回整个页面
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final Logger logger;


    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
        this.logger = LoggerFactory.getLogger(ArticleController.class);
    }

    // 插入一篇文章
    @PostMapping("/insertArticle")
    public Response insertArticle(@RequestBody ArticleVO articleVO){
        logger.info("/article/insertArticle post request, 目的：插入一篇文章");

        String type = articleVO.getType();
        if(!(type.equals("policy") || type.equals("news") || type.equals("question") || type.equals("reference"))){
            return Response.buildFailed("10004", "文章类型错误");
        }
        ArticlePO articlePO = new ArticlePO();
        BeanUtils.copyProperties(articleVO, articlePO);
        try{
            articleService.insertArticle(articlePO);
            return Response.buildSuccess("文章上传成功");
        }catch (Exception e){
            return Response.buildFailed("10003", "文章信息不完整");
        }
    }

    // 获取10篇文章
    @GetMapping("/getArticles")
    public Response getArticles(@RequestParam(value = "time", required = true) String time, @RequestParam(value = "type", required = false) String type){

        ArrayList<ArticlePO> articles;
        if(type == null){
            logger.info("/article/getArticles get request, 目的：获取文章");
            articles = articleService.get10ArticlesByTime(time);
        }else{
            logger.info("/article/getArticles get request, 目的：获取文章，文章类型：{}", type);
            if(type.equals("policy") || type.equals("news") || type.equals("question") || type.equals("reference")){
                articles = articleService.get10ArticlesByTypeAndTime(type, time);
            }else {
                return Response.buildFailed("10004", "文章类型错误");
            }
        }

        return Response.buildSuccess("文章获取成功", articles);
    }

    // 更新文章信息
    @PostMapping("/updateArticle")
    public Response updateArticle(@RequestBody ArticleVO articleVO){
        logger.info("/article/updateArticle post request, 目的：更新文章信息");

        String type = articleVO.getType();
        if(!(type.equals("policy") || type.equals("news") || type.equals("question") || type.equals("reference"))){
            return Response.buildFailed("10004", "文章类型错误");
        }
        ArticlePO articlePO = new ArticlePO();
        BeanUtils.copyProperties(articleVO, articlePO);
        try{
            articleService.updateArticle(articlePO);
            return Response.buildSuccess("文章更新成功");
        }catch (Exception e){
            return Response.buildFailed("10003", "文章信息不完整");
        }

    }

}
