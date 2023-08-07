package com.yq.structure.test;

import com.alibaba.fastjson.JSON;
import com.yq.structure.trie.Trie;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 字典树测试
 * @author: Yuqing
 * @create: 2023-08-07 10:12
 **/
public class TrieTest {

    private Logger logger = LoggerFactory.getLogger(TrieTest.class);

    @Test
    public void test_Trie(){
        Trie trie = new Trie();
        // 存入
        trie.insert("bat");
        trie.insert("batch");
        trie.insert("bitch");
        trie.insert("battle");
        trie.insert("baby");
        trie.insert("basic");
        trie.insert("banana");
        trie.insert("beach");
        trie.insert("believe");
        logger.info(trie.toString());
        // 检索
        List<String> trieNodes = trie.searchPrefix("ba",5);
        logger.info("测试结果：{}", JSON.toJSONString(trieNodes));
    }
}
