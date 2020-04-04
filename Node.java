package com.lancl.demo03;

public class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //中序遍历
    public void middlePrint(){
        if (this.left!=null){
            this.left.middlePrint();
        }
        System.out.print(value+" ");
        if (this.right!=null){
            this.right.middlePrint();
        }
    }

    /**
     * 获取左子树的高度
     * @return
     */
    public int leftHeight(){
        if (left==null){
            return 0;
        }
        return left.getHeight();
    }

    /**
     * 获取右子树的高度
     * @return
     */
    public int rightHeight(){
        if (right==null){
            return 0;
        }
        return right.getHeight();
    }

    /**
     * 向子树添加节点
     * @param node
     */
    public void add(Node node) {
        if (node==null){
            return;
        }
        //判断是否比节点值大
        if (node.value<this.value){//添加的节点的值比当前节点值更小
            //判断左子节点是否为空
            if (this.left==null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            //判断右子节点是否为空
            if (this.right==null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
        //查询是否平衡
        //进行右旋转
        if (leftHeight()-rightHeight()>1){
            System.out.println("当前节点"+value+",左子树高度："+leftHeight()+
                    ",右子树高度："+rightHeight()+",右旋转");
            //双旋转 先左再右
            if (left!=null && left.leftHeight()<left.rightHeight()){
                left.leftRotate();
                System.out.println("先左再右双旋转");
            }
            rightRotate();
        }
        if(rightHeight()-leftHeight()>1){
            //左旋转
            System.out.println("当前节点"+value+",左子树高度："+leftHeight()+
                    ",右子树高度："+rightHeight()+",左旋转");
            //双旋转 先右再左
            if (right!=null && right.rightHeight()<right.rightHeight()){
                right.rightRotate();
                System.out.println("先右再左双旋转");
            }
            leftRotate();
        }
    }

    /**
     * 左旋转
     * ①将A的右孩子B提升为新的根结点；②将原来的根结点A降为B的左孩子；③各子树按大小关系连接(AL和BR不变，BL调整为A的右子树)。
     */
    private void leftRotate() {
        //将当前节点的右子节点提升为根节点，原根节点降为提升后根节点的左子节点
        //右子节点如果有，赋值给原根节点的右子节点
        //创建一个新的节点 值等于当前节点（导致树不平衡的节点即左右子树层差超过1的节点）的值
        Node newLeft = new Node(value);
        //把新节点的左子树等于当前节点的左子树
        newLeft.left=left;
        //把新节点的右子树设置为当前节点的右子树的左子树
        newLeft.right = right.left;
        //当前节点的值换为右子节点的值
        this.value = right.value;
        //把当前节点的右子树设置为右子树的右子树
        right = right.right;
        //把当前节点的左子树设置为新节点
        left = newLeft;
        System.out.println("左旋转完成，当前节点："+value+",树高度："+getHeight());
    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        //创建一个新的节点 值等于当前节点的值
        Node newRight = new Node(value);
        //把新节点的右子树等于当前节点的右子树
        newRight.right = right;
        //把新节点的左子树设置为当前节点的左子树的右子树
        newRight.left = left.right;
        //当前节点的值换为左子节点的值
        this.value = left.value;
        //把当前节点的左子树设置为左子树的左子树
        left = left.left;
        //把当前节点的右子树设置为新节点
        right = newRight;
        System.out.println("右旋转完成，当前节点："+value+",树高度："+getHeight());
    }

    public Node search(int i) {
        if (this.value==i){
            return this;
        }else if (this.value>i){
            //查找值比当前值小，查找左子树
            if (this.left!=null){
                return this.left.search(i);
            }else{
                return null;
            }
        }else{
            //查找值比当前值大，查找右子树
            if (this.right!=null){
                return this.right.search(i);
            }else{
                return null;
            }
        }
    }

    public Node searchParent(int i) {
        if ((this.left!=null && this.left.value==i
            || (this.right!=null && this.right.value==i)
        )){
            return this;
        }else{
            if (this.value>i && this.left!=null){
                return this.left.searchParent(i);
            }else if(this.value<i && this.right!=null){
                return this.right.searchParent(i);
            }
            return null;
        }
    }

    /**
     * 返回当前节点的高度
     * @return
     */
    public int getHeight(){
        return Math.max(this.left==null?0:this.left.getHeight(),
                this.right==null?0:this.right.getHeight())+1;
    }
}
