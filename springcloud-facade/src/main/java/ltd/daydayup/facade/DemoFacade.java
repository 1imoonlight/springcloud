package ltd.daydayup.facade;

import ltd.daydayup.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DemoFacade
 *
 * @auther lipengcheng
 * @date 2022/2/6 18:17
 */
@RequestMapping("/api/demo")
public interface DemoFacade {

    @GetMapping("/querynacosvalue")
    Result<String> queryNacosValue();
}
