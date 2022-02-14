package ltd.daydayup.facade;

import ltd.daydayup.common.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DemoFacade
 *
 * @auther lipengcheng
 * @date 2022/2/6 18:17
 */
@RequestMapping("/api/demo")
@Validated
public interface DemoFacade {

    /**
     * 获取nacos的配置的具体值
     * @author lipengcheng
     * @date 2022/2/9 09:40
     * @return ltd.daydayup.common.result.Result<java.lang.String>
     */
    @GetMapping("/querynacosvalue")
    Result<String> queryNacosValue();
}
