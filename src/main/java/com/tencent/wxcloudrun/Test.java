package com.tencent.wxcloudrun;


import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.dao.LandPostDao;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {

    public void getAdInfo(String location) {
        String key = "FL6BZ-ON76U-ELXV4-27XN6-GN72E-XAB4R";
        String uri = "https://apis.map.qq.com/ws/geocoder/v1/?location=" + location + "&key=" + key;
        RestTemplate restTemplate = new RestTemplate();

//        HashMap<String, String> response = restTemplate.getForObject(uri, HashMap.class);
        String response = restTemplate.getForObject(uri, String.class);
        System.out.println("1111");
        System.out.println(response);
        HashMap<String, Object> responseJson = new HashMap<>();
        try {
            responseJson = JSON.parseObject(response, HashMap.class);
            System.out.println("2222");
            System.out.println(responseJson.get("result").toString());
            String result = responseJson.get("result").toString();
            HashMap<String, Object> resultJson = JSON.parseObject(result, HashMap.class);
            String adInfo = resultJson.get("ad_info").toString();
            HashMap<String, String> adInfoJson = JSON.parseObject(adInfo, HashMap.class);
            String city = adInfoJson.get("city");
            String distict = adInfoJson.get("district");
            System.out.println(city);
            System.out.println(distict);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        Test test = new Test();

    }
}
