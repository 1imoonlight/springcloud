package ltd.daydayup.web.facade;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import ltd.daydayup.common.enums.BaseResultCodeEnum;
import ltd.daydayup.common.result.Result;
import ltd.daydayup.facade.DemoFacade;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoFacadeImpl
 *
 * @auther lipengcheng
 * @date 2022/2/6 18:21
 */
@RestController
@Slf4j
public class DemoFacadeImpl implements DemoFacade {

    @NacosValue(value="${demo}",autoRefreshed = true)
    private String demo;

    @Override
    public Result<String> queryNacosValue() {
        try{
            return Result.buildSuccessResult(demo);
        }catch (Exception e){
            return Result.buildErrorResult(BaseResultCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    @Override
    public Result<String> querySentinel() {
        try{
            return Result.buildSuccessResult(demo);
        }catch (Exception e){
            return Result.buildErrorResult(BaseResultCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }
}
