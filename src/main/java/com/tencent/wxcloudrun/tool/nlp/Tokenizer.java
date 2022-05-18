package com.tencent.wxcloudrun.tool.nlp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分词器，单例模式，提高效率
 *
 * @author Cloudy
 */
public class Tokenizer {


    /**
     * 私有实例，类初始化就加载
     */
    private static Tokenizer instance = new Tokenizer();

    /**
     * 私有构造方法
     */
    private Tokenizer() {
        this.dict = Dictionary.getInstance();
    }

    /**
     * 公共的、静态的获取实例方法
     * @return 唯一词典实例
     */
    public static Tokenizer getInstance() {
        return instance;
    }

    /**
     * 全局唯一的词典
     */
    private final Dictionary dict;


    /**
     * 分词
     *
     * @param rawText 未处理的文本
     * @return 处理结果
     */
    public HashMap<Nature, ArrayList<String>> segment(String rawText){
        // 初始化结果容器
        HashMap<Nature, ArrayList<String>> wordMap = new HashMap<Nature, ArrayList<String>>(){{
            put(Nature.PROVINCE, new ArrayList<>());
            put(Nature.CITY, new ArrayList<>());
            put(Nature.COUNTY, new ArrayList<>());
            put(Nature.LANDTYPE_PARENT, new ArrayList<>());
            put(Nature.LANDTYPE_CHILD, new ArrayList<>());
            put(Nature.TRANSFERTYPE, new ArrayList<>());
            put(Nature.TRANSFERTIME, new ArrayList<>());
            put(Nature.AREA, new ArrayList<>());
            put(Nature.PRICE, new ArrayList<>());
            put(Nature.Date, new ArrayList<>());
        }};

        String formattedText = this.formatText(rawText); // 第一次格式化，只保留数字与汉字
        if(!formattedText.equals("")){
            formattedText = this.segmentByRegex(formattedText, wordMap); // 通过正则分词，再次格式化
            this.segmentForwardLongest(formattedText, wordMap); // 通过字典分词
        }

        return wordMap;
    }

    /**
     * 格式化未处理文本，只保留数字与汉字
     * @param rawText 未处理的文本
     * @return 格式化后的待匹配文本
     */
    private String formatText(String rawText){
        return rawText.replaceAll("[^0-9\u4E00-\u9FA5]", "");
    }

    /**
     * 通过正则提取信息，注意处理数字，不考虑小数
     *
     * @param formattedText 格式化后文本
     * @param wordMap 分词结果容器
     * @return 再一次格式化后文本
     */
    private String segmentByRegex(String formattedText, HashMap<Nature, ArrayList<String>> wordMap){
        if (formattedText == null || formattedText.equals("")){
            return null;
        }

        // eg：5年，2022年
        Pattern patternYear = Pattern.compile("(?<year>\\d+)年");
        Matcher matcherYear = patternYear.matcher(formattedText);
        while (matcherYear.find()){
            String year = matcherYear.group("year");
            if(Integer.parseInt(year) >= 2022){ // 属于发布时间
                wordMap.get(Nature.Date).add(year);
            }else { // 属于流转年限
                wordMap.get(Nature.TRANSFERTIME).add(year);
            }
        }
        formattedText = formattedText.replaceAll("\\d+年", "");

        // eg：50亩
        Pattern patternArea = Pattern.compile("(?<area>\\d+)亩");
        Matcher matcherArea = patternArea.matcher(formattedText);
        while (matcherArea.find()){
            String area = matcherArea.group("area");
            wordMap.get(Nature.AREA).add(area);
        }
        formattedText = formattedText.replaceAll("\\d+亩", "");

        // eg：10000元
        Pattern patternPrice = Pattern.compile("(?<price>\\d+)元");
        Matcher matcherPrice = patternPrice.matcher(formattedText);
        while (matcherPrice.find()){
            String price = matcherPrice.group("price");
            wordMap.get(Nature.PRICE).add(price);
        }
        formattedText = formattedText.replaceAll("\\d+元", "");

        // eg：1千元
        Pattern patternPriceQian = Pattern.compile("(?<price>\\d+)千元");
        Matcher matcherPriceQian = patternPriceQian.matcher(formattedText);
        while (matcherPriceQian.find()){
            String price = matcherPriceQian.group("price");
            price = String.valueOf(Integer.parseInt(price) * 1000);
            wordMap.get(Nature.PRICE).add(price);
        }
        formattedText = formattedText.replaceAll("\\d+千元", "");

        // eg：10000元
        Pattern patternPriceWan = Pattern.compile("(?<price>\\d+)万元");
        Matcher matcherPriceWan = patternPriceWan.matcher(formattedText);
        while (matcherPriceWan.find()){
            String price = matcherPriceWan.group("price");
            price = String.valueOf(Integer.parseInt(price) * 10000);
            wordMap.get(Nature.PRICE).add(price);
        }
        formattedText = formattedText.replaceAll("\\d+万元", "");

        // 不属于上面的数字
        Pattern patternNumber = Pattern.compile("(?<number>\\d+)");
        Matcher matcherNumber = patternNumber.matcher(formattedText);
        while (matcherNumber.find()){
            String number = matcherNumber.group("number");
            int numValue = Integer.parseInt(number);
            if(numValue >= 2022){
                wordMap.get(Nature.Date).add(number);
            }else {
                wordMap.get(Nature.TRANSFERTIME).add(number);
            }
            wordMap.get(Nature.AREA).add(number);
            wordMap.get(Nature.PRICE).add(number);
        }
        formattedText = formattedText.replaceAll("\\d+", "");

        return formattedText;
    }


    /**
     * 基于字典树的正向最长匹配
     * @param formattedText 格式化后文本
     * @param wordMap 分词结果容器
     */
    private void segmentForwardLongest(String formattedText,  HashMap<Nature, ArrayList<String>> wordMap){
        if(formattedText == null || formattedText.equals("")) return;
        this.dict.parseLongestText(formattedText, (begin, end, value) -> {
            String word = formattedText.substring(begin, end);
            wordMap.get(value).add(word);
        });
    }

}
