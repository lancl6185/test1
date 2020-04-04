package com.lancl.demo03;

public class TestBinarySortTree {
    public static void main(String[] args) {
        int[] arr = {4,2,6,1,3,5,7,8,9};
        //创建一棵二叉排序树
        BinarySortTree bst = new BinarySortTree();
        //循环添加
        for (int i: arr) {
            Node node = new Node(i);
            bst.add(node);
            System.out.println("树的根节点"+bst.root.value+",当前插入节点"+i+"的高度：" + bst.getHeight(node));
        }
        System.out.println("树的根节点"+bst.root.value+" 树的高度：" + bst.getHeight());
        bst.middlePrint();
        bst.hashCode();
    }
}
