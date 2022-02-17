package ltd.daydayup.web.jobhandler;

import cn.hutool.core.date.DateUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lipengcheng
 * @date 2021-12-29
 */
@Component
@Slf4j
public class DemoJob {

    @XxlJob("DemoJobHandler")
    public ReturnT<String> DemoJobHandler(String param) throws Exception {
        try {
            XxlJobHelper.log("DemoJob, Started:" + DateUtil.formatDateTime(new Date()));
            Thread.sleep(2000);
            XxlJobHelper.log("DemoJob, End:" + DateUtil.formatDateTime(new Date()));
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            log.error("DemoJob error:" + e);
            XxlJobHelper.log("DemoJob error:" + e.toString());
        }
        return ReturnT.FAIL;
    }
}
