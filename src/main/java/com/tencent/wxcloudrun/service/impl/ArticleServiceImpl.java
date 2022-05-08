package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.ArticleDao;
import com.tencent.wxcloudrun.model.po.ArticlePO;
import com.tencent.wxcloudrun.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }


    @Override
    public int insertArticle(ArticlePO articlePO) {
        return articleDao.insertArticle(articlePO);
    }

    @Override
    public ArrayList<ArticlePO> get10ArticlesByTime(String time) {
        return articleDao.select10ByTime(time);
    }

    @Override
    public ArrayList<ArticlePO> get10ArticlesByTypeAndTime(String type, String time) {
        return articleDao.select10ByTypeAndTime(type, time);
    }

    @Override
    public int updateArticle(ArticlePO articlePO) {
        return articleDao.updateArticle(articlePO);
    }
}
