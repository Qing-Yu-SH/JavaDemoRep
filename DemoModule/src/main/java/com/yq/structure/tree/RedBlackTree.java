package com.yq.structure.tree;

/**
 * @program: JavaDemoRep
 * @description: 红黑树
 * @author: Yuqing
 * @create: 2023-08-09 13:05
 **/
public class RedBlackTree extends AbstractTree implements Tree{

    private int size = 0;

    @Override
    public Node insert(int e) {
        Node newNode = null;
        // 缓存
        elementList.add(e);
        if (null == root) {
            root = new Node(this.getClass(), e, null, null, null);
            newNode = root;
        }else{
            // 索引出待插入元素位置，也就是插入到哪个父元素下
            Node parent = root;
            Node search = root;

            while (search != null && search.value != null) {
                parent = search;
                if (e < search.value) {
                    search = search.left;
                } else {
                    search = search.right;
                }
            }

            // 插入元素
            newNode = new Node(this.getClass(), e, parent, null, null);
            if (parent.value > newNode.value) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }

        size++;

        // 设置叶子节点
        newNode.left = nilNode;
        newNode.right = nilNode;

        root.parent = nilNode;
        fixInsertBalance(newNode);
        return newNode;
    }

    /**
     * 通过染色、左旋、右旋，调衡树高
     */
    private void fixInsertBalance(Node current) {
        while (current.parent != root && current.parent.color == Node.Color.RED) {
            // 父亲节点
            Node parent = current.parent;
            // 爷爷节点
            Node grandParent = parent.parent;

            // ↙左倾结构；当前节点的父节点，是当前节点爷爷节点的左孩子
            if (parent == grandParent.left) {
                Node uncle = grandParent.right;
                // 染色
                if (uncle.color == Node.Color.RED) {
                    parent.color = Node.Color.BLACK;
                    uncle.color = Node.Color.BLACK;
                    grandParent.color = Node.Color.RED;
                    current = grandParent;
                }
                // 旋转
                else {
                    // 偏右↘，先左旋一次调衡
                    if (current == parent.right) {
                        current = parent;
                        super.rotateLeft(current);
                        parent = current.parent;
                    }
                    parent.color = Node.Color.BLACK;
                    grandParent.color = Node.Color.RED;
                    super.rotateRight(grandParent);
                }
            }
            // ↘右倾结构；当前节点的父节点，是当前节点爷爷节点的右孩子
            if (parent == grandParent.right) {
                Node uncle = grandParent.left;
                // 染色
                if (uncle.color == Node.Color.RED) {
                    parent.color = Node.Color.BLACK;
                    uncle.color = Node.Color.BLACK;
                    grandParent.color = Node.Color.RED;
                    current = grandParent;
                }
                // 旋转
                else {
                    // 偏左↙，先右旋一次调衡
                    if (current == parent.left) {
                        current = parent;
                        super.rotateRight(current);
                        parent = current.parent;
                    }
                    parent.color = Node.Color.BLACK;
                    grandParent.color = Node.Color.RED;
                    super.rotateLeft(grandParent);
                }
            }
        }
        root.color = Node.Color.BLACK;
    }

    @Override
    public Node delete(int e) {
        return null;
    }

    @Override
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
