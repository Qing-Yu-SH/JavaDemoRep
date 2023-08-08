package com.yq.structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-07 13:13
 **/
public abstract class AbstractTree {

    public List<Integer> elementList = new ArrayList<>();

    public Node root;


    /**
     * 左旋
     * 1
     *   2
     *     3
     *  node 就是 1
     */
    protected Node rotateLeft(Node node){
        Node temp = node.right;
        temp.parent = node.parent;

        node.right = temp.left;
        if(node.right != null){
            node.right.parent = node;
        }

        temp.left = node;
        node.parent = temp;

        if(temp.parent == null){
            root = temp;
        }else{
            if(temp.parent.left == node){
                temp.parent.left = temp;
            }else{
                temp.parent.right = temp;
            }
        }

        return temp;
    }

    /**
     * 右旋
     *      1
     *    2
     *  3
     *  node 就是 1
     */
    protected Node rotateRight(Node node){
        Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.right;
        if(node.left != null){
            node.left.parent = node;
        }

        temp.right = node;
        node.parent = temp;

        if(temp.parent == null){
            root = temp;
        }else{
            if(temp.parent.left == node){
                temp.parent.left = temp;
            }else{
                temp.parent.right = temp;
            }
        }

        return temp;
    }



    protected String printSubTree(Node node) {
        StringBuilder tree = new StringBuilder();
        if (node.right != null) {
            printTree(node.right, true, "", tree);
        }
        printNodeValue(node, tree);
        if (node.left != null) {
            printTree(node.left, false, "", tree);
        }
        return tree.toString();
    }

    private void printTree(Node node, boolean isRight, String indent, StringBuilder tree) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "), tree);
        }
        tree.append(indent);
        if (isRight) {
            tree.append(" /");
        } else {
            tree.append(" \\");
        }
        tree.append("----- ");
        printNodeValue(node, tree);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "), tree);
        }
    }

    private void printNodeValue(Node node, StringBuilder tree) {
        if (null == node.value) {
            tree.append("<NIL>");
        }else{
            tree.append(node.value).append("(").append(node.height).append(")");
        }
        tree.append("\r\n");
    }

}
