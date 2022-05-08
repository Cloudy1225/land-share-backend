package com.tencent.wxcloudrun.dao;


import com.tencent.wxcloudrun.model.po.ArticlePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface ArticleDao {

    // 增加一篇文章
    int insertArticle(ArticlePO articlePO);

    // 获取10文章
    ArrayList<ArticlePO> select10ByTime(String time);

    // 通过分类查找文章
    ArrayList<ArticlePO> select10ByTypeAndTime(@Param("type") String type, @Param("time") String time);

    // 更新文章信息
    int updateArticle(ArticlePO articlePO);

}
