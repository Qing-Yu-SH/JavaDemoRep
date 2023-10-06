package com.yq.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-06 09:53
 **/
public class JsonUtil {

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public static  <T> T toObj(String str, Class<T> clz) {
        try {
            return jsonMapper.readValue(str, clz);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static <T> String toStr(T t) {
        try {
            return jsonMapper.writeValueAsString(t);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

}
