package com.yq.util;

import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-09 19:44
 **/
public class TestFileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        String path = TestFileUtil.class.getResource("/").getPath();
        try {
            path = URLDecoder.decode(path,"utf-8");
        } catch (UnsupportedEncodingException e) {
            return path;
        }
        return path;
    }

    public static TestPathBuild pathBuild() {
        return new TestPathBuild();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }

    /**
     * build to test file path
     **/
    public static class TestPathBuild {
        private TestPathBuild() {
            subPath = new ArrayList<>();
        }

        private final List<String> subPath;

        public TestPathBuild sub(String dirOrFile) {
            subPath.add(dirOrFile);
            return this;
        }

        public String getPath() {
            if (CollectionUtils.isEmpty(subPath)) {
                return TestFileUtil.getPath();
            }
            if (subPath.size() == 1) {
                return TestFileUtil.getPath() + subPath.get(0);
            }
            StringBuilder path = new StringBuilder(TestFileUtil.getPath());
            path.append(subPath.get(0));
            for (int i = 1; i < subPath.size(); i++) {
                path.append(File.separator).append(subPath.get(i));
            }
            return path.toString();
        }

    }

}
