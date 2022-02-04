package ltd.daydayup.web.controller;

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

    @RequestMapping
    @ResponseBody
    public String index() {
        return "hello world!";
    }
}
