package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePO {

    private Integer aid;

    private String title;

    private String type;

    private String url;

    private String fileID;

    private String time;
}
