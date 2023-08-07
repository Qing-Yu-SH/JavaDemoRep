package com.yq.structure.trie;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 字典树
 * @author: Yuqing
 * @create: 2023-08-07 09:42
 **/
public class Trie {

    private final static TrieNode root = new TrieNode();

    /**
     * 插入
     */
    public void insert(String word){
        TrieNode cur = root;
        char[] chars = word.toCharArray();
        for(char c: chars){
            int idx = c - 'a';
            if(cur.children[idx] == null){
                cur.children[idx] = new TrieNode();
            }
            cur = cur.children[idx];
            cur.c = c;
        }
        cur.isWord = true;
    }

    public List<String> searchPrefix(String prefix,int resultLimit){
        TrieNode cur = root;
        StringBuilder cache = new StringBuilder();
        char[] chars = prefix.toCharArray();
        // 精准匹配：根据前置精准查找
        for(char c: chars){
            int idx = c - 'a';
            // 匹配为空
            if(idx>cur.children.length || idx<0 || cur.children[idx]==null){
                return Collections.emptyList();
            }
            cache.append(c);
            cur = cur.children[idx];
        }

        // 模糊匹配：根据前缀的最后一个单词，递归遍历所有的单词
        List<String> list = new ArrayList<>();
        if(cur.isWord) list.add(cache.toString());
        for(int i=0;i<cur.children.length;i++){
            if(cur.children[i] != null){
                char c = (char) (i+'a');
                collect(cur.children[i],String.valueOf(cache) + c,list,resultLimit);
                if(list.size() >= resultLimit){
                    return list;
                }
            }
        }
        return list;
    }

    private void collect(TrieNode cur,String pre,List<String> list,int resultLimit){
        // 找到单词
        if(cur.isWord){
            list.add(pre);
            if(list.size() >= resultLimit){
                return;
            }
        }

        // 递归调用，查找单词
        for(int i=0;i<cur.children.length;i++){
            if(cur.children[i] != null){
                char c = (char) (i+'a');
                collect(cur.children[i],pre + c,list,resultLimit);
                if(list.size() >= resultLimit){
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Trie：" + JSON.toJSONString(root);
    }



    static class TrieNode{

        /** 字母 */
        public char c;

        /** 形成一个链 */
        public TrieNode[] children = new TrieNode[26];

        /** 单词：数量 > 0 表示一个单词 */
        public boolean isWord;
    }

}
