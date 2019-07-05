package com.gsta.app.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试日志级别controller . <br>
 * 
 * @author xh
 */
@RestController
public class LogController {

    /**
     * 日志对象
     */
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log")
    public String log() {
        log.debug("debug级别");
        log.info("info级别");
        log.error("error级别");
        return "这是一个字符串";
    }

}
