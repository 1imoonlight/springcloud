package ltd.daydayup.web.service.biz;

import ltd.daydayup.web.repository.entity.NoviceGuideUserInfoDO;

import java.util.List;

/**
 * @author lipengcheng
 * @date 2022-03-04
 */
public interface NoviceGuideService {

    /**
     * 查询所有数据
     * @author lipengcheng
     * @date 2022/3/4 10:30
     * @return java.util.List<ltd.daydayup.web.repository.entity.NoviceGuideUserInfoDO>
     */
    List<NoviceGuideUserInfoDO> queryAll();
}
