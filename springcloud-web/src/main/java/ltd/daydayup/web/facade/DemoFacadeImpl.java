package ltd.daydayup.web.facade;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import ltd.daydayup.common.constants.RedisConstants;
import ltd.daydayup.common.enums.BaseResultCodeEnum;
import ltd.daydayup.common.result.Result;
import ltd.daydayup.facade.DemoFacade;
import ltd.daydayup.web.repository.entity.NoviceGuideUserInfoDO;
import ltd.daydayup.web.service.NoviceGuideService;
import ltd.daydayup.web.service.redis.RedissonDistributionLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;
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

    @Autowired
    private NoviceGuideService noviceGuideService;

    /**
     * 分布式锁
     */
    @Resource
    private RedissonDistributionLock redissonDistributionLock;

    /**
     * 获取nacos的配置的具体值
     *
     * @return ltd.daydayup.common.result.Result<java.lang.String>
     * @author lipengcheng
     * @date 2022/2/9 09:40
     */
    @Override
    public Result<String> queryNacosValue() {
        try {
            return Result.buildSuccessResult(demo);
        } catch (Exception e) {
            return Result.buildErrorResult(BaseResultCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * sentinel限流
     *
     * @return ltd.daydayup.common.result.Result<java.lang.String>
     * @author lipengcheng
     * @date 2022/2/19 21:57
     **/
    @Override
    public Result<String> querySentinel() {
        try {
            return Result.buildSuccessResult(demo);
        } catch (Exception e) {
            return Result.buildErrorResult(BaseResultCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * 分布式锁
     *
     * @return ltd.daydayup.common.result.Result<java.lang.String>
     * @author lipengcheng
     * @date 2022/2/24 16:34
     */
    @Override
    public Result<String> createLockDemo(String uid) {
        String lockKey = MessageFormat.format(RedisConstants.REDISKEY_DEMO_LOCKKEY, uid);
        try {
            if (redissonDistributionLock.tryLock(lockKey, TimeUnit.SECONDS, 10, 10)) {
                return Result.buildSuccessResult(demo);
            }
            return Result.buildErrorResult(BaseResultCodeEnum.REPETITIVE_OPERATION.code(), BaseResultCodeEnum.REPETITIVE_OPERATION.getMsg());
        } catch (Exception e) {
            log.error("createLockDemo error:" + e.toString());
            return Result.buildErrorResult(e.getMessage());
        } finally {
            redissonDistributionLock.unlock(lockKey);
        }
    }

    /**
     * 分页查询
     *
     * @param pageindex
     * @param pagesize
     * @return ltd.daydayup.common.result.Result<java.lang.Object>
     * @author lipengcheng
     * @date 2022/3/4 10:38
     */
    @Override
    public Result<Object> queryToPage(Integer pageindex, Integer pagesize) {
        try {
            //设置开始页码及每页数量
            PageHelper.startPage(pageindex, pagesize);
            List<NoviceGuideUserInfoDO> noviceGuideUserInfoDOS = noviceGuideService.queryAll();
            PageInfo<NoviceGuideUserInfoDO> pageInfo = new PageInfo<>(noviceGuideUserInfoDOS);
            return Result.buildSuccessResult(pageInfo);
        } catch (Exception e) {
            log.error("queryToPage error:" + e.getMessage());
            return Result.buildErrorResult(BaseResultCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }
}
