package com.tencent.wxcloudrun.tool.nlp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;

/**
 * 词典，单例模式，提高效率
 *
 * @author Cloudy
 */
public class Dictionary {

    /**
     * 私有实例，类初始化就加载
     */
    private static Dictionary instance = new Dictionary();

    /**
     * 私有构造方法
     */
    private Dictionary() {
        this.init();
    }

    /**
     * 公共的、静态的获取实例方法
     * @return 唯一词典实例
     */
    public static Dictionary getInstance() {
        return instance;
    }

    /**
     * 基于 TreeMap 的词典
     */
    private TreeMap<String, Nature> dictMap;

    /**
     * 基于字典树的词典
     */
    private BinTrie<Nature> dictTrie;


    /**
     * 词典初始化
     */
    private void init(){
//        String[] pathArray = new String[]{"data/PROVINCE.txt", "data/CITY.txt", "data/COUNTY.txt"};
        String[] pathArray = new String[]{
                "static/dictionaries/PROVINCE.txt",
                "static/dictionaries/CITY.txt",
                "static/dictionaries/COUNTY.txt"
        };
//        System.out.println("11111装载词典");
        this.loadDictionary(pathArray);
    }

    private InputStreamReader resourceLoader (String path) throws IOException{

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        if(is == null)
            throw new IOException("路径填写错误");
        else {
//            System.out.println(is);
            return new InputStreamReader(is, StandardCharsets.UTF_8);
        }

    }

    /**
     * 加载词典，词典必须遵守格式（一个词占一行）
     *
     * @param pathArray 词典路径，可以有任意个。每个路径中含有词性，如 "dictionary/PROVINCE.txt"
     */
    private void loadDictionary(String... pathArray) {

        this.initDictMap();

        if(pathArray != null && pathArray.length!=0){
            for(String path: pathArray){
                int begin = path.lastIndexOf("/");
                int end = path.lastIndexOf(".");
                Nature nature = Nature.valueOf(path.substring(begin+1, end));
                String word; // 一行一个词
//                try {
//                    File file = new File(path);
//                    FileReader fr = new FileReader(file);
//                    BufferedReader bufr = new BufferedReader(fr);
//                    while ((word = bufr.readLine())!= null){
//                        this.dictMap.put(word, nature);
//                    }
//                    bufr.close();
//                    fr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                try {
                    InputStreamReader isr = this.resourceLoader(path);
                    BufferedReader bufr = new BufferedReader(isr);
                    while ((word = bufr.readLine())!= null){
//                        System.out.println(word);
                        this.dictMap.put(word, nature);
                    }
                    bufr.close();
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.dictTrie = new BinTrie<>(this.dictMap);
    }

    /**
     * 初始化 dictMap
     */
    private void initDictMap(){

        this.dictMap = new TreeMap<String, Nature>(){{
            // 流转方式
            put("转让", Nature.TRANSFERTYPE);
            put("出租", Nature.TRANSFERTYPE);
            put("转包", Nature.TRANSFERTYPE);
            put("合作", Nature.TRANSFERTYPE);
            put("入股", Nature.TRANSFERTYPE);
            put("互换", Nature.TRANSFERTYPE);

            //土地类型
            put("耕地", Nature.LANDTYPE_PARENT);
                put("盐碱地", Nature.LANDTYPE_CHILD);
                put("水田", Nature.LANDTYPE_CHILD);
                put("水浇地", Nature.LANDTYPE_CHILD);
                put("旱地", Nature.LANDTYPE_CHILD);
                put("其它耕地", Nature.LANDTYPE_CHILD);
            put("林地", Nature.LANDTYPE_PARENT);
                put("有林地", Nature.LANDTYPE_CHILD);
                put("灌木林地", Nature.LANDTYPE_CHILD);
                put("荒山", Nature.LANDTYPE_CHILD);
                put("其它林地", Nature.LANDTYPE_CHILD);
            put("园地", Nature.LANDTYPE_PARENT);
                put("果园", Nature.LANDTYPE_CHILD);
                put("茶园", Nature.LANDTYPE_CHILD);
                put("菜园", Nature.LANDTYPE_CHILD);
                put("其它园地", Nature.LANDTYPE_CHILD);
            put("草地", Nature.LANDTYPE_PARENT);
                put("天然牧草地", Nature.LANDTYPE_CHILD);
                put("人工草地", Nature.LANDTYPE_CHILD);
                put("其它草地", Nature.LANDTYPE_CHILD);
            put("水域", Nature.LANDTYPE_PARENT);
                put("坑塘", Nature.LANDTYPE_CHILD);
                put("河流", Nature.LANDTYPE_CHILD);
                put("湖泊", Nature.LANDTYPE_CHILD);
                put("水库", Nature.LANDTYPE_CHILD);
                put("滩涂", Nature.LANDTYPE_CHILD);
                put("其它水域", Nature.LANDTYPE_CHILD);
        }};

    }

    /**
     * 基于字典数的最长匹配
     *
     * @param text      文本
     * @param processor 处理器
     */
    public void parseLongestText(String text, Hit<Nature> processor){
        this.dictTrie.parseLongestText(text, processor);
    }


}
