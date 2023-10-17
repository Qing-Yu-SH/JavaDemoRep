package com.yq;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-17 22:54
 **/
@RestController
public class IndexRest {

    /**
     * 没有添加相关的 xml 转换依赖，定义了 produces 会报错： No converter for [class com.yq.ResVo] with preset Content-Type 'null']
     * 添加 jackson-dataformat-xml 依赖后，可以正常返回 xml 格式的数据
     */
    @GetMapping(path = "/xml", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResVo<String> xml() {
        return new ResVo<>(0, "ok", "返回xml");
    }

    @GetMapping(path = "/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResVo<String> json() {
        return new ResVo<>(0, "ok", "返回json");
    }

    /**
     * 不添加 jackson-dataformat-xml 依赖时，默认返回 JSON 格式数据
     * 以下以不进行内容协商配置为前提：
     * 添加 jackson-dataformat-xml 依赖后，根据请求头 Accept：text/html,application/xhtml+xml,application/xml(可能存在不同) 中的格式进行匹配，本地返回 application/xhtml+xml
     * 请求头可以设置多种 MediaType，用英文逗号分割，后端接口会根据自己定义的 produce 与请求头希望的 mediaType 取交集，至于最终选择的顺序则以 accept 中出现的顺序为准
     * 配置内容协商后（WebConfigurer），默认传递 JSON
     * @return
     */
    @GetMapping(path = "/default")
    public ResVo<String> defaultMethod(){
        return new ResVo<>(0, "ok", "返回默认格式");
    }

}
