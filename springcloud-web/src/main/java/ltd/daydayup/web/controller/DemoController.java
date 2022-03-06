package ltd.daydayup.web.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import ltd.daydayup.web.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;

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
        int i = 0;
        int maxVal = 1000;
        while (i < maxVal) {
            String redisKey = IdUtil.simpleUUID();
            Long randomInt = Convert.toLong(RandomUtil.randomInt(10, 100));
            redisService.set(redisKey, demo, randomInt);
            log.info(MessageFormat.format("key:{0} , expire:{1}", redisKey, randomInt));
            i++;
        }
        return demo;
    }
}
