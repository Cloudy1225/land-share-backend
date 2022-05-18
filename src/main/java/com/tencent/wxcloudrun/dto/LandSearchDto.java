package com.tencent.wxcloudrun.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandSearchDto {

    private ArrayList<String> adInfo;

    private ArrayList<String> landType;

    private ArrayList<String> transferType;

    private ArrayList<Double> transferTime;

    private ArrayList<Double> area;

    private ArrayList<Double> price;

    private ArrayList<String> submitTime;

}
