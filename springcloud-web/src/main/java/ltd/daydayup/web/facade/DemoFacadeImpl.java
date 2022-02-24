package ltd.daydayup.web.facade;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import ltd.daydayup.common.constants.RedisConstants;
import ltd.daydayup.common.enums.BaseResultCodeEnum;
import ltd.daydayup.common.result.Result;
import ltd.daydayup.facade.DemoFacade;
import ltd.daydayup.web.service.biz.redis.RedissonDistributionLock;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * DemoFacadeImpl
 *
 * @auther lipengcheng
 * @date 2022/2/6 18:21
 */
@RestController
@Slf4j
public class DemoFacadeImpl implements DemoFacade {

    @NacosValue(value = "${demo}", autoRefreshed = true)
    private String demo;

    /**
     * 分布式锁
     */
    @Resource
    private RedissonDistributionLock redissonDistributionLock;

    @Override
    public Result<String> queryNacosValue() {
        try {
            return Result.buildSuccessResult(demo);
        } catch (Exception e) {
            return Result.buildErrorResult(BaseResultCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    @Override
    public Result<String> querySentinel() {
        try {
            return Result.buildSuccessResult(demo);
        } catch (Exception e) {
            return Result.buildErrorResult(BaseResultCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    @Override
    public Result<String> createLockDemo(String uid) {
        String lockKey = MessageFormat.format(RedisConstants.REDISKEY_DEMO_LOCKKEY, uid);
        try {
            if (redissonDistributionLock.tryLock(lockKey, TimeUnit.SECONDS, 10, 10)) {
                return Result.buildSuccessResult();
            }
            return Result.buildErrorResult(BaseResultCodeEnum.REPETITIVE_OPERATION.code(), BaseResultCodeEnum.REPETITIVE_OPERATION.getMsg());
        } catch (Exception e) {
            log.error("createLockDemo error:" + e.toString());
            return Result.buildErrorResult(e.getMessage());
        }
    }
}
