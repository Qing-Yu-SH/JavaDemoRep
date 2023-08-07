package com.yq.structure.tree;

/**
 * @program: JavaDemoRep
 * @description: 二叉搜索树
 * @author: Yuqing
 * @create: 2023-08-07 11:18
 **/
public class BinarySearchTree extends AbstractTree{


    protected int size = 0;

    /** 插入节点 */
    public Node insert(int e){
        // 缓存
        elementList.add(e);

        if(root == null){
            root = new Node(this.getClass(),e,null,null,null);
            size++;
            return root;
        }

        Node parent = root;
        Node search = root;
        while (search!=null && search.value!=null){
            parent = search;
            if(search.value < e){
                search = search.right;
            }else{
                search = search.left;
            }
        }

        Node newNode = new Node(this.getClass(),e,null,null,null);
        if(parent.value < newNode.value){
            parent.right = newNode;
        }else{
            parent.left = newNode;
        }

        size++;
        return newNode;
    }

    /** 删除节点 */
    public Node delete(int e) {
        // 缓存
        elementList.add(e);

        root = delete(root,e);

        return root;
    }

    private Node delete(Node node, int e){
        if(node == null) return null;
        if(node.value == e){
            // 删除节点无左右孩子，直接返回 null
            if(node.left==null && node.right==null) return null;
            // 删除节点只存在一个孩子，返回非空的孩子
            if(node.left==null || node.right==null){
                return node.left==null ? node.right:node.left;
            }

            // 删除节点的左右孩子都存在
            Node right = node.right;
            Node pre = node;
            // 找到右子树上的最左节点
            while (right.left != null){
                pre = right;
                right = right.left;
            }
            // 交换节点
            Node tempRight = right.right;
            right.left = node.left;
            right.right = node.right;
            pre.left = node;
            node.left = null;
            node.right = tempRight;
            node = right;
            // 删除节点
            node.right = delete(node.right,e);

            return node;
        }else if(node.value < e){
            node.right = delete(node.right,e);
        }else{
            node.left = delete(node.left,e);
        }
        return node;
    }

    /** 查找节点 */
    public Node search(int e) {
        Node node = root;
        while (node != null && node.value != null && node.value != e) {
            if (e < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    protected Node getMiniNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public String toString() {
        String str = elementList.toString();
        str = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]")).replace(" ", "");
        int nullIdx = str.indexOf("null");
        if (nullIdx > 0) {
            str = str.substring(0, str.indexOf("null"));
            str = str.substring(0, str.lastIndexOf(","));
        }
        System.out.println(this.getClass().getSimpleName() + "，输入节点：" + str + "\r\n");
        return printSubTree(root);
    }




}