package com.tencent.wxcloudrun.tool.nlp;



import java.util.*;

/**
 * 首字直接分配内存，之后二分动态数组的Trie树，能够平衡时间和空间
 *
 * @param <V> 值
 * @author Cloudy
 */
public class BinTrie<V> extends BaseNode<V> {
    /**
     * 词典的词条数目
     */
    private int size;

    public BinTrie()
    {
        child = new BaseNode[65535 + 1];    // (int)Character.MAX_VALUE
        size = 0;
        status = Status.NOT_WORD_1;
    }

    /**
     * 根据词典构造一个字典树
     *
     * @param map 词典
     */
    public BinTrie(Map<String, V> map)
    {
        this();
        for (Map.Entry<String, V> entry : map.entrySet())
        {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 词典中增加新的词
     *
     * @param keyValueMap 新词
     */
    public void build(TreeMap<String, V> keyValueMap)
    {
        for (Map.Entry<String, V> entry : keyValueMap.entrySet())
        {
            put(entry.getKey(), entry.getValue());
        }
    }

    public int size()
    {
        return size;
    }

    @Override
    protected char getChar()
    {
        return 0;   // 根节点没有char
    }

    @Override
    public BaseNode<V> getChild(char c)
    {
        return child[c]; // char对应的整值即为下标
    }

    /**
     * 插入一个词
     *
     * @param key 词的内容
     * @param value 词性
     */
    public void put(String key, V value)
    {
        if (key.length() == 0) return;  // 安全起见
        BaseNode<V> branch = this;
        char[] chars = key.toCharArray();
        for (int i = 0; i < chars.length - 1; ++i)
        {
            // 除了最后一个字外，都是继续
            branch.addChild(new Node<V>(chars[i], Status.NOT_WORD_1, null));
            branch = branch.getChild(chars[i]);
        }
        // 最后一个字加入时属性为end
        if (branch.addChild(new Node<V>(chars[chars.length - 1], Status.WORD_END_3, value)))
        {
            ++size; // 维护size：最后一个字且为新增节点，说明词典中词数+1
        }
    }

    /**
     * 插入一个词
     *
     * @param key 词的内容
     * @param value 词性
     */
    public void put(char[] key, V value)
    {
        BaseNode<V> branch = this;
        for (int i = 0; i < key.length - 1; ++i)
        {
            // 除了最后一个字外，都是继续
            branch.addChild(new Node<V>(key[i], Status.NOT_WORD_1, null));
            branch = branch.getChild(key[i]);
        }
        // 最后一个字加入时属性为end
        if (branch.addChild(new Node<V>(key[key.length - 1], Status.WORD_END_3, value)))
        {
            ++size; // 维护size
        }
    }


    /**
     * 设置键值对，当键不存在的时候会自动插入
     * @param key 词的内容
     * @param value 词性
     */
    public void set(String key, V value)
    {
        put(key.toCharArray(), value);
    }

    /**
     * 删除一个词
     *
     * @param key 词的内容
     */
    public void remove(String key)
    {
        BaseNode<V> branch = this;
        char[] chars = key.toCharArray();
        for (int i = 0; i < chars.length - 1; ++i)
        {
            if (branch == null) return;
            branch = branch.getChild(chars[i]);
        }
        if (branch == null) return;
        // 最后一个字设为undefined
        if (branch.addChild(new Node<V>(chars[chars.length - 1], Status.UNDEFINED_0, value)))
        {
            --size;
        }
    }

    /**
     * 判断词典中是否有该词
     * @param key 词
     * @return true or false
     */
    public boolean containsKey(String key)
    {
        BaseNode<V> branch = this;
        char[] chars = key.toCharArray();
        for (char aChar : chars)
        {
            if (branch == null) return false;
            branch = branch.getChild(aChar); // BFS
        }

        return branch != null && (branch.status == Status.WORD_END_3 || branch.status == Status.WORD_MIDDLE_2);
    }

    /**
     * 获取词对应的值
     * @param key 词的内容
     * @return 值（词性，Nature）
     */
    public V get(String key)
    {
        BaseNode<V> branch = this;
        char[] chars = key.toCharArray();
        for (char aChar : chars)
        {
            if (branch == null) return null;
            branch = branch.getChild(aChar);
        }

        if (branch == null) return null;
        // 下面这句可以保证只有成词的节点被返回
        if (!(branch.status == Status.WORD_END_3 || branch.status == Status.WORD_MIDDLE_2)) return null;
        return branch.getValue();
    }

    /**
     * 获取词对应的值
     * @param key 词的内容
     * @return 值（词性，Nature）
     */
    public V get(char[] key)
    {
        BaseNode<V> branch = this;
        for (char aChar : key)
        {
            if (branch == null) return null;
            branch = branch.getChild(aChar);
        }

        if (branch == null) return null;
        // 下面这句可以保证只有成词的节点被返回
        if (!(branch.status == Status.WORD_END_3 || branch.status == Status.WORD_MIDDLE_2)) return null;
        return branch.getValue();
    }

    /**
     * 获取键值对集合
     *
     * @return 词典转为set
     */
    public Set<Map.Entry<String, V>> entrySet()
    {
        Set<Map.Entry<String, V>> entrySet = new TreeSet<>();
        StringBuilder sb = new StringBuilder();
        for (BaseNode<V> node : this.child) // 遍历65536个根节点
        {
            if (node == null) continue;
            node.walk(new StringBuilder(sb.toString()), entrySet);
        }
        return entrySet;
    }

    /**
     * 键集合
     * @return 键
     */
    public Set<String> keySet()
    {
        TreeSet<String> keySet = new TreeSet<>();
        for (Map.Entry<String, V> entry : entrySet())
        {
            keySet.add(entry.getKey());
        }

        return keySet;
    }

    /**
     * 前缀查询
     *
     * @param key 查询串
     * @return 键值对
     */
    public Set<Map.Entry<String, V>> prefixSearch(String key)
    {
        Set<Map.Entry<String, V>> entrySet = new TreeSet<Map.Entry<String, V>>();
        StringBuilder sb = new StringBuilder(key.substring(0, key.length() - 1));
        BaseNode<V> branch = this;
        char[] chars = key.toCharArray();
        for (char aChar : chars)
        {
            if (branch == null) return entrySet;
            branch = branch.getChild(aChar);
        }

        if (branch == null) return entrySet;
        branch.walk(sb, entrySet);
        return entrySet;
    }

    /**
     * 前缀查询，包含值
     *
     * @param key 键
     * @return 键值对列表
     */
    public LinkedList<Map.Entry<String, V>> commonPrefixSearchWithValue(String key)
    {
        char[] chars = key.toCharArray();
        return commonPrefixSearchWithValue(chars, 0);
    }

    /**
     * 前缀查询，通过字符数组来表示字符串可以优化运行速度
     *
     * @param chars 字符串的字符数组
     * @param begin 开始的下标
     * @return
     */
    public LinkedList<Map.Entry<String, V>> commonPrefixSearchWithValue(char[] chars, int begin)
    {
        LinkedList<Map.Entry<String, V>> result = new LinkedList<Map.Entry<String, V>>();
        StringBuilder sb = new StringBuilder();
        BaseNode<V> branch = this;
        for (int i = begin; i < chars.length; ++i)
        {
            char aChar = chars[i];
            branch = branch.getChild(aChar);
            if (branch == null || branch.status == Status.UNDEFINED_0) return result;
            sb.append(aChar);
            if (branch.status == Status.WORD_MIDDLE_2 || branch.status == Status.WORD_END_3)
            {
                result.add(new AbstractMap.SimpleEntry<String, V>(sb.toString(), branch.value));
            }
        }

        return result;
    }

    @Override
    protected boolean addChild(BaseNode<V> node)
    {
        boolean add = false;
        char c = node.getChar();
        BaseNode<V> target = getChild(c);
        if (target == null)
        {
            child[c] = node;
            add = true;
        }
        else
        {
            switch (node.status)
            {
                case UNDEFINED_0:
                    if (target.status != Status.NOT_WORD_1)
                    {
                        target.status = Status.NOT_WORD_1;
                        add = true;
                    }
                    break;
                case NOT_WORD_1:
                    if (target.status == Status.WORD_END_3)
                    {
                        target.status = Status.WORD_MIDDLE_2;
                    }
                    break;
                case WORD_END_3:
                    if (target.status == Status.NOT_WORD_1)
                    {
                        target.status = Status.WORD_MIDDLE_2;
                    }
                    if (target.getValue() == null)
                    {
                        add = true;
                    }
                    target.setValue(node.getValue());
                    break;
            }
        }
        return add;
    }

    /**
     * 最长匹配
     *
     * @param text      文本
     * @param processor 处理器
     */
    public void parseLongestText(String text, Hit<V> processor)
    {
        int length = text.length();
        for (int i = 0; i < length; ++i)
        {
            BaseNode<V> state = transition(text.charAt(i));
            if (state != null)
            {
                int to = i + 1;
                int end = to;
                V value = state.getValue();
                for (; to < length; ++to)
                {
                    state = state.transition(text.charAt(to));
                    if (state == null) break;
                    if (state.getValue() != null)
                    {
                        value = state.getValue();
                        end = to + 1;
                    }
                }
                if (value != null)
                {
                    processor.hit(i, end, value);
                    i = end - 1;
                }
            }
        }
    }

    /**
     * 最长匹配
     *
     * @param text      文本
     * @param processor 处理器
     */
    public void parseLongestText(char[] text, Hit<V> processor)
    {
        int length = text.length;
        for (int i = 0; i < length; ++i)
        {
            BaseNode<V> state = transition(text[i]);
            if (state != null)
            {
                int to = i + 1;
                int end = to;
                V value = state.getValue();
                for (; to < length; ++to)
                {
                    state = state.transition(text[to]);
                    if (state == null) break;
                    if (state.getValue() != null)
                    {
                        value = state.getValue();
                        end = to + 1;
                    }
                }
                if (value != null)
                {
                    processor.hit(i, end, value);
                    i = end - 1;
                }
            }
        }
    }

    /**
     * 匹配文本
     *
     * @param text      文本
     * @param processor 处理器
     */
    public void parseText(String text, Hit<V> processor)
    {
        int length = text.length();
        int begin = 0;
        BaseNode<V> state = this;

        for (int i = begin; i < length; ++i)
        {
            state = state.transition(text.charAt(i));
            if (state != null)
            {
                V value = state.getValue();
                if (value != null)
                {
                    processor.hit(begin, i + 1, value);
                }
            }
            else
            {
                i = begin;
                ++begin;
                state = this;
            }
        }
    }

    /**
     * 匹配文本
     *
     * @param text      文本
     * @param processor 处理器
     */
    public void parseText(char[] text, Hit<V> processor)
    {
        int length = text.length;
        int begin = 0;
        BaseNode<V> state = this;

        for (int i = begin; i < length; ++i)
        {
            state = state.transition(text[i]);
            if (state != null)
            {
                V value = state.getValue();
                if (value != null)
                {
                    processor.hit(begin, i + 1, value);
                }
            }
            else
            {
                i = begin;
                ++begin;
                state = this;
            }
        }
    }
}
