package com.tencent.wxcloudrun.tool.nlp;

/**
 * 命中一个模式串的处理方法
 * @param <V>
 */
public interface Hit<V> {
    /**
     * 命中一个模式串后的回调方法
     *
     * @param begin 模式串在母文本中的起始位置
     * @param end   模式串在母文本中的终止位置
     * @param value 模式串对应的值
     */
    void hit(int begin, int end, V value);
}
