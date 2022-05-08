package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {

    private Integer aid;

    private String title;

    private String type;

    private String url;

    private String fileID;

    private String time;
}
