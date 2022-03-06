package ltd.daydayup.web.repository.mapper.springcloud;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import ltd.daydayup.web.repository.entity.NoviceGuideUserInfoDO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoviceGuideUserInfoMapper {

    /**
     * 查询所有数据
     * @author lipengcheng
     * @date 2022/3/4 10:27
     * @return java.util.List<ltd.daydayup.web.repository.entity.NoviceGuideUserInfoDO>
     */
    List<NoviceGuideUserInfoDO> queryAll();
}