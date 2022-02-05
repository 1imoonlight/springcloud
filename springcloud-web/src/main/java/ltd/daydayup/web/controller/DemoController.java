package ltd.daydayup.web.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @NacosValue(value="${demo}",autoRefreshed = true)
    private String demo;

    @RequestMapping
    @ResponseBody
    public String index() {
        return demo;
    }
}
