package com.yq.service;

import com.yq.LogRecord;
import com.yq.common.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-08 12:04
 **/
@Slf4j
@Service
public class UserService {

    @Value("Hello,#{100+2}")
    private String val;

    @LogRecord(success = "用户：#{user.id} 信息变更，"+
                         "变更内容：密码",
               type = CommonConstants.LOG_RECORD_TYPE_USER,
               actionType = CommonConstants.LOG_ACTION_TYPE_UPDATE
              )
    public Boolean updatePassword(User user) {
        log.info("修改密码成功!");
        return true;
    }


}
