package com.yq.structure.tree;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-07 22:33
 **/
public class AVLTree extends AbstractTree implements Tree{

    private int size = 0;

    @Override
    public Node insert(int e) {
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
        newNode.parent = parent;

        size++;
        rebalance(newNode);
        return newNode;
    }



    @Override
    public Node delete(int e) {
        // 缓存
        elementList.add(e);
        Node delNode = search(e);

        if(delNode != null){
            // delNodeSuccess 替换后的节点
            Node delNodeSuccess = delete(delNode);
            if(delNodeSuccess.right != null){
                Node min = getMiniNode(delNodeSuccess.right);
                recomputeHeight(min);
                rebalance(min);
            }else{
                recomputeHeight(delNodeSuccess);
                rebalance(delNodeSuccess);
            }
        }

        return delNode;
    }

    private Node delete(Node delNode){
        if(delNode == null) return null;
        Node result = null;
        Node parent = delNode.parent;
        if(delNode.left == null){
            result = transplant(delNode,delNode.right);
        }else if(delNode.right == null){
            result = transplant(delNode,delNode.left);
        }else{
            // 找到右子树上的最左侧节点，即最小节点
            Node miniNode = getMiniNode(delNode.right);
            // 通过最小节点替换待删节点
            if(miniNode.parent != delNode){
                // 如果 miniNode 不是 delNode 右孩子，则需要进行处理
                // 拿 miniNode 的右孩子替换 miniNode
                transplant(miniNode,miniNode.right);
                // 将 delNode 的右孩子移动到 miniNode 的右孩子处
                miniNode.right = delNode.right;
                miniNode.right.parent = miniNode;
            }
            // 交换位置
            transplant(delNode,miniNode);
            miniNode.left = delNode.left;
            miniNode.left.parent = miniNode;
            result = miniNode;
        }
        size--;
        return result==null ? parent:result;
    }

    protected Node getMiniNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 节点替换
     * @param delNode 删除节点
     * @param addNode 替换节点
     * @return
     */
    protected Node transplant(Node delNode, Node addNode){
        // 修改父节点
        if(delNode.parent == null){
            root = addNode;
        }else if(delNode.parent.left == delNode){
            delNode.parent.left = addNode;
        }else{
            delNode.parent.right = addNode;
        }
        if(null != addNode){
            addNode.parent = delNode.parent;
        }

        return addNode;
    }


    private void recomputeHeight(Node node) {
        while (node != null) {
            node.height = maxHeight(node.left, node.right) + 1;
            node = node.parent;
        }
    }

    private int maxHeight(Node node1, Node node2) {
        if (node1 != null && node2 != null) {
            return node1.height > node2.height ? node1.height : node2.height;
        } else if (node1 == null) {
            return node2 != null ? node2.height : -1;
        } else if (node2 == null) {
            return node1 != null ? node1.height : -1;
        }
        return -1;
    }

    @Override
    public Node search(int e) {
        Node node = root;
        while (node != null && node.value != null && node.value != e) {
            node = e < node.value ? node.left : node.right;
        }
        return node;
    }


    private void rebalance(Node node){
        while (node != null){
            Node parent = node.parent;

            int leftHeight = (node.left == null) ? -1 : (node.left).height;
            int rightHeight = (node.right == null) ? -1 : (node.right).height;
            int factor = leftHeight - rightHeight;

            switch (factor){
                case 2:
                    // 需调整的地方在左子树
                    if(factor(node.left) >= 0){
                        // 右旋
                        Node temp = super.rotateRight(node);
                        refreshHeight(temp.right);
                        refreshHeight(temp);
                    }else{
                        // 左旋 + 右旋
                        Node temp = super.rotateLeft(node.left);
                        refreshHeight(temp.left);
                        refreshHeight(temp);
                        node.left = temp;

                        temp = super.rotateRight(node);
                        refreshHeight(temp.right);
                        refreshHeight(temp);
                    }
                    break;
                case -2:
                    // 需要调整的地方在右子树
                    if (factor(node.right) <= 0) {
                        // 左旋
                        Node temp = super.rotateLeft(node);
                        refreshHeight(temp.left);
                        refreshHeight(temp);
                    } else {
                        // 右旋 + 左旋
                        Node temp = super.rotateRight(node.right);
                        refreshHeight(temp.right);
                        refreshHeight(temp);
                        node.right = temp;

                        temp = super.rotateLeft(node);
                        refreshHeight(temp.left);
                        refreshHeight(temp);
                    }
                    break;
                default:
                    refreshHeight(node);
            }
            node = parent;
        }
    }


    private void refreshHeight(Node node) {
        int leftHeight = (node.left == null) ? -1 : (node.left).height;
        int rightHeight = (node.right == null) ? -1 : (node.right).height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    private int factor(Node node) {
        int leftHeight = (node.left == null) ? -1 : (node.left).height;
        int rightHeight = (node.right == null) ? -1 : (node.right).height;
        return leftHeight - rightHeight;
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
