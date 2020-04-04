package com.lancl.demo03;

public class BinarySortTree {
    Node root;

    //插入节点
    public void add(Node node){
        //如果是一棵空树
        if (root==null){
            root = node;
        }else{
            root.add(node);
        }
    }

    public void middlePrint(){
        if (null!=this.root){
            root.middlePrint();
        }
    }

    public Node search(int i){
        if (this.root==null){
            return null;
        }
        return this.root.search(i);
    }

    /**
     * 删除节点
     */
    public void delete(int i){
        if (null!=this.root){
            //找到节点
            Node node = root.search(i);
            if(node!=null){
                //找到父节点
                Node parent = searchParent(i);
                //要删除的节点是叶子节点
                if (node.left==null && node.right==null){
                    //要删除节点是父节点的左子节点
                    if (parent.left.value==i){
                        parent.left = null;
                    }else//要删除节点是父节点的右子节点
                    if (parent.right.value==i){
                        parent.right = null;
                    }
                }
                //要删除的节点有两个子节点
                else if (node.left!=null && node.right!=null){
                    //删除右子树中值最小的节点，并获取该节点的值
                    int min = deleteMin(node.right);
                    //替换目标节点中的值
                    node.value = min;
                }
                //要删除的节点有一个子节点
                else {
                    //有左子节点或有一个右子节点
                    //先判断要删除的节点是父节点的左还是右子节点
                    //删除左子节点，替换父节点的左子节点为删除节点的非空子节点
                    if (parent.left.value==i){
                        parent.left = node.right==null?node.left:node.right;
                    }
                    //删除的是父节点的右子节点,将父节点的右节点替换为删除元素的非空子节点
                    else {
                        parent.right = node.right==null?node.left:node.right;
                    }
                }
            }
        }
    }

    /**
     * 删除一棵树中最小的节点
     * @param node
     * @return
     */
    private int deleteMin(Node node) {
        Node target = node;
        //递归向左找
        while (target.left!=null){
            target = target.left;
        }
        //删除最小的节点
        delete(target.value);
        return target.value;
    }

    /**
     * 查找父结点
     * @param i
     * @return
     */
    public Node searchParent(int i){
        if (this.root==null){
            return null;
        }
        return this.root.searchParent(i);
    }

    public int getHeight(){
        return getTreeHeight(this.root);
    }

    private int getTreeHeight(Node root){
        if(root==null){
            return 0;
        }
        return Math.max(getTreeHeight(root.left), getTreeHeight(root.right))+1;
    }

    /**
     * 节点所在的高度
     * @param node
     * @return
     */
    public int getHeight(Node node){
        int height = 0;
        while (node!=null) {
            height++;
            node = searchParent(node.value);
        }
        return height;
    }

}
