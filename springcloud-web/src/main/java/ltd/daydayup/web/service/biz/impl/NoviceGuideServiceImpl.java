package ltd.daydayup.web.service.biz.impl;

import ltd.daydayup.web.repository.entity.NoviceGuideUserInfoDO;
import ltd.daydayup.web.repository.mapper.springcloud.NoviceGuideUserInfoMapper;
import ltd.daydayup.web.service.biz.NoviceGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lipengcheng
 * @date 2022-03-04
 */
@Service
public class NoviceGuideServiceImpl implements NoviceGuideService {

    @Autowired
    private NoviceGuideUserInfoMapper noviceGuideUserInfoMapper;

    /**
     * 查询所有数据
     * @author lipengcheng
     * @date 2022/3/4 10:30
     * @return java.util.List<ltd.daydayup.web.repository.entity.NoviceGuideUserInfoDO>
     */
    @Override
    public List<NoviceGuideUserInfoDO> queryAll() {
        return noviceGuideUserInfoMapper.queryAll();
    }
}
