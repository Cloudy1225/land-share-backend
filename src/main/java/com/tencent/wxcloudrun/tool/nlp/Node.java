package com.tencent.wxcloudrun.tool.nlp;



/**
 * 深度大于等于2的子节点
 *
 * @author Cloudy
 */
public class Node<V> extends BaseNode<V>{

    @Override
    protected boolean addChild(BaseNode<V> node){
        boolean add = false;
        if(this.child == null){
            this.child = new BaseNode[0];
        }
        int index = ArrayTool.binarySearch(this.child, node);
        if(index >= 0){
            BaseNode<V> target = this.child[index];
            switch (node.status){
                case UNDEFINED_0: { // 用于删除词条
                    if (target.status != Status.NOT_WORD_1) {
                        target.status = Status.NOT_WORD_1; // 将状态由一个词条变为不是一个词条
                        target.value = null;
                        add = true; // 删除词条
                    }
                    break;
                }
                case NOT_WORD_1: {
                    if(target.status == Status.WORD_END_3){
                        target.status = Status.WORD_MIDDLE_2;
                    }
                    break;
                }
                case WORD_END_3: {
                    if(target.status != Status.WORD_END_3){
                        target.status = Status.WORD_MIDDLE_2;
                    }
                    if(target.getValue() == null){
                        add = true;
                    }
                    target.setValue(node.getValue());
                    break;
                }
            }

        }
        else {
            BaseNode<V>[] newChild = new BaseNode[child.length + 1];
            int insert = -(index + 1);
            System.arraycopy(child, 0, newChild, 0, insert);
            System.arraycopy(child, insert, newChild, insert + 1, child.length - insert);
            newChild[insert] = node;
            child = newChild;
            add = true;
        }
        return add;
    }

    /**
     * @param c      节点的字符
     * @param status 节点状态
     * @param value  值
     */
    public Node(char c, Status status, V value)
    {
        this.c = c;
        this.status = status;
        this.value = value;
    }
    public Node(){}

    public BaseNode<V> getChild(char c)
    {
        if (child == null) return null;
        int index = ArrayTool.binarySearch(child, c);
        if (index < 0) return null;

        return child[index];
    }

}
