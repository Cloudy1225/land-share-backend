package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.LandPostDao;
import com.tencent.wxcloudrun.dto.LandSearchDto;
import com.tencent.wxcloudrun.model.po.LandPostPO;
import com.tencent.wxcloudrun.model.vo.LandPostVO;
import com.tencent.wxcloudrun.service.SearchService;
import com.tencent.wxcloudrun.tool.nlp.Nature;
import com.tencent.wxcloudrun.tool.nlp.Tokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 模糊搜索服务类
 *
 * @author Cloudy
 */
@Service
public class SearchServiceImpl extends LandPostPOtoVO implements SearchService {

    private final LandPostDao landPostDao;

    @Autowired //自动注入Dao对象
    public SearchServiceImpl(LandPostDao landPostDao){
        this.landPostDao = landPostDao; // 实例化DAO对象，以操作数据库
    }

    @Override
    public ArrayList<LandPostVO> searchLandPosts(String input) {
        LandSearchDto landSearchDto = this.parseInput(input);
//        System.out.println(landSearchDto);
        ArrayList<LandPostPO> landPostPOS = this.landPostDao.selectBySearch(landSearchDto);

        return this.poToVO(landPostPOS);
    }

    /**
     * 解析用户输入
     * @param input 用户输入
     * @return 解析结果
     */
    private LandSearchDto parseInput(String input){
        ArrayList<String> adInfo = new ArrayList<>();
        ArrayList<String> landType = new ArrayList<>();
        ArrayList<String> transferType = new ArrayList<>();
        ArrayList<Double> transferTime = new ArrayList<>();
        ArrayList<Double> area = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<String> submitTime = new ArrayList<>();

        // 使用分词器分词
        Tokenizer tokenizer = Tokenizer.getInstance();
        final HashMap<Nature, ArrayList<String>> rawRes = tokenizer.segment(input);
//        System.out.println(rawRes);

        // 格式化分词结果

        // 行政区划优先级：县 > 市 > 省
        ArrayList<String> county = rawRes.get(Nature.COUNTY);
        if(county.size() == 0){
            ArrayList<String> city = rawRes.get(Nature.CITY);
            if(city.size() == 0){
                ArrayList<String> province = rawRes.get(Nature.PROVINCE);
                if(province.size() > 0){
                    adInfo.addAll(province);
                }
            }else {
                adInfo.addAll(city);
            }
        }else {
            adInfo.addAll(county);
        } // 这样实现会有bug，如："南京市玄武区与苏州市"，只会保留玄武区，而实际还应该保留苏州市

        // 土地类型
        landType.addAll(rawRes.get(Nature.LANDTYPE_PARENT));
        landType.addAll(rawRes.get(Nature.LANDTYPE_CHILD));

        // 流转类型
        transferType.addAll(rawRes.get(Nature.TRANSFERTYPE));

        // 流转年限
        for (String item: rawRes.get(Nature.TRANSFERTIME)){
            transferTime.add(Double.valueOf(item));
        }

        // 土地面积
        for (String item: rawRes.get(Nature.AREA)){
            area.add(Double.valueOf(item));
        }

        // 流转单价
        for (String item: rawRes.get(Nature.PRICE)){
            price.add(Double.valueOf(item));
        }

        // 发布时间
        submitTime.addAll(rawRes.get(Nature.Date));

        return new LandSearchDto(adInfo, landType, transferType, transferTime, area, price, submitTime);
    }

    private HashMap<String, ArrayList<String>> parseInputToMap(String input) {
        HashMap<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>(){{
            put("adInfo", new ArrayList<>());
            put("landType", new ArrayList<>());
            put("transferType", new ArrayList<>());
            put("transferTime", new ArrayList<>());
            put("area", new ArrayList<>());
            put("price", new ArrayList<>());
            put("submitTime", new ArrayList<>());
        }};

        Tokenizer tokenizer = Tokenizer.getInstance();
        final HashMap<Nature, ArrayList<String>> rawRes = tokenizer.segment(input);

        // 行政区划优先级：县 > 市 > 省
        ArrayList<String> county = rawRes.get(Nature.COUNTY);
        if(county.size() == 0){
            ArrayList<String> city = rawRes.get(Nature.CITY);
            if(city.size() == 0){
                ArrayList<String> province = rawRes.get(Nature.PROVINCE);
                if(province.size() > 0){
                    res.get("afInfo").addAll(province);
                }
            }else {
                res.get("afInfo").addAll(city);
            }
        }else {
            res.get("adInfo").addAll(county);
        } // 这样实现会有bug，如："南京市玄武区与苏州市"，只会保留玄武区，而实际还应该保留苏州市

        // 土地类型
        ArrayList<String> landType = res.get("landType");
        landType.addAll(rawRes.get(Nature.LANDTYPE_PARENT));
        landType.addAll(rawRes.get(Nature.LANDTYPE_CHILD));

        // 流转类型
        res.get("transferType").addAll(rawRes.get(Nature.TRANSFERTYPE));

        // 流转年限
        ArrayList<String> transferTime = res.get("transferTime");
        transferTime.addAll(rawRes.get(Nature.TRANSFERTIME));

        // 土地面积
        ArrayList<String> area = res.get("area");
        area.addAll(rawRes.get(Nature.AREA));

        // 流转单价
        ArrayList<String> price = res.get("price");
        price.addAll(rawRes.get(Nature.PRICE));

        // 发布时间
        ArrayList<String> submitTime = res.get("submitTime");
        submitTime.addAll(rawRes.get(Nature.Date));

        return res;
    }

}
