package com.tencent.wxcloudrun.service;


import com.tencent.wxcloudrun.model.po.ArticlePO;

import java.util.ArrayList;

public interface ArticleService {

    int insertArticle(ArticlePO articlePO);

    ArrayList<ArticlePO> get10ArticlesByTime(String time);

    ArrayList<ArticlePO> get10ArticlesByTypeAndTime(String type, String time);

    int updateArticle(ArticlePO articlePO);


}
