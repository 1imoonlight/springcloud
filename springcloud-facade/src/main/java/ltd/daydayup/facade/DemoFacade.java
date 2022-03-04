package ltd.daydayup.facade;

import ltd.daydayup.common.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
     *
     * @return ltd.daydayup.common.result.Result<java.lang.String>
     * @author lipengcheng
     * @date 2022/2/9 09:40
     */
    @GetMapping("/querynacosvalue")
    Result<String> queryNacosValue();

    /**
     * sentinel限流
     *
     * @return ltd.daydayup.common.result.Result<java.lang.String>
     * @author lipengcheng
     * @date 2022/2/19 21:57
     **/
    @GetMapping("/sentinel")
    Result<String> querySentinel();

    /**
     * 分布式锁
     *
     * @return ltd.daydayup.common.result.Result<java.lang.String>
     * @author lipengcheng
     * @date 2022/2/24 16:34
     */
    @PostMapping("/lockdemo")
    Result<String> createLockDemo(@NotBlank(message = "用户id不能为空") String uid);

    /**
     * 分页查询
     *
     * @param pageindex
     * @param pagesize
     * @return ltd.daydayup.common.result.Result<java.lang.Object>
     * @author lipengcheng
     * @date 2022/3/4 10:38
     */
    @GetMapping("/querytopage")
    Result<Object> queryToPage(@NotNull(message = "pageindex不能为空") Integer pageindex,@NotNull(message = "pagesize不能为空")  Integer pagesize);
}
