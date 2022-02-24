package ltd.daydayup.web.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import ltd.daydayup.web.service.biz.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * DemoController
 *
 * @auther lipengcheng
 * @date 2022/2/4 17:16
 */
@RequestMapping
@Controller
@Slf4j
public class DemoController {

    @NacosValue(value = "${demo}", autoRefreshed = true)
    private String demo;

    @Autowired
    private RedisService redisService;

    @RequestMapping
    @ResponseBody
    public String index() {
        return demo;
    }

    @RequestMapping("/redis")
    @ResponseBody
    public String redis() {
        redisService.set("name", demo);
        return (String) redisService.get("name");
    }
}
