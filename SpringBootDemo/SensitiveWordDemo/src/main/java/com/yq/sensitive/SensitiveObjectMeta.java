package com.yq.sensitive;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @program: JavaDemoRep
 * @description:  敏感词相关配置
 * @author: Yuqing
 * @create: 2023-11-06 22:48
 **/
@Data
public class SensitiveObjectMeta {

    private static final String JAVA_LANG_OBJECT = "java.lang.object";

    /**
     * 是否存在被注解 SensitiveField 修饰的属性
     */
    private Boolean enabledSensitiveReplace;

    private String className;

    /**
     * 标注 SensitiveField 的成员
     */
    private List<SensitiveFieldMeta> sensitiveFieldMetaList;

    public static Optional<SensitiveObjectMeta> buildSensitiveObjectMeta(Object param){
        if(isNull(param)){
            return Optional.empty();
        }

        Class<?> clazz = param.getClass();
        SensitiveObjectMeta sensitiveObjectMeta = new SensitiveObjectMeta();
        sensitiveObjectMeta.setClassName(clazz.getName());

        List<SensitiveFieldMeta> sensitiveFieldMetaList = new ArrayList<>();
        sensitiveObjectMeta.setSensitiveFieldMetaList(sensitiveFieldMetaList);
        boolean sensitiveField = parseAllSensitiveFields(clazz, sensitiveFieldMetaList);
        sensitiveObjectMeta.setEnabledSensitiveReplace(sensitiveField);

        // 用于返回一个包含了特定值的 Optional 对象
        return Optional.of(sensitiveObjectMeta);
    }

    private static boolean parseAllSensitiveFields(Class<?> clazz, List<SensitiveFieldMeta> sensitiveFieldMetaList) {
        Class<?> tempClazz = clazz;
        boolean hasSensitiveField = false;

        // 循环遍历 clazz 及其父类；获取所有属性，并将带有注解的属性封装为 SensitiveFieldMeta，保存到 List
        while (nonNull(tempClazz) && !JAVA_LANG_OBJECT.equalsIgnoreCase(tempClazz.getName())) {
            for(Field field: tempClazz.getDeclaredFields()){
                SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);
                if(nonNull(sensitiveField)){
                    SensitiveFieldMeta sensitiveFieldMeta = new SensitiveFieldMeta();
                    sensitiveFieldMeta.setName(field.getName());
                    sensitiveFieldMeta.setBindField(sensitiveField.bind());
                    sensitiveFieldMetaList.add(sensitiveFieldMeta);
                    hasSensitiveField = true;
                }
            }
            tempClazz = tempClazz.getSuperclass();
        }
        return hasSensitiveField;
    }


    @Data
    public static class SensitiveFieldMeta {
        /**
         * 默认根据字段名，找db中同名的字段
         */
        private String name;

        /**
         * 绑定的数据库字段别名
         */
        private String bindField;
    }

}
