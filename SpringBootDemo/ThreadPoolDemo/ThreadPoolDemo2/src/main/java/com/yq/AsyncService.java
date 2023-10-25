package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-24 19:16
 **/
@Slf4j
@Service
public class AsyncService {

    @Autowired
    private AsyncTask asyncTask;

    public void asyncTask(){
        try {
            log.info("service start..");
            for(int i=0;i<20;i++){
                asyncTask.task();
            }
            log.info("service end..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}





