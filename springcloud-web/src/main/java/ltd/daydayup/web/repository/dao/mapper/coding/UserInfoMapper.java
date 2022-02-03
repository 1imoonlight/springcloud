package ltd.daydayup.web.repository.dao.mapper.coding;

import ltd.daydayup.codingweb.repository.dao.entity.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther lipengcheng
 * @date 2021-12-10
 */
@Mapper
public interface UserInfoMapper {
    List<UserInfoDO> getAll();
    UserInfoDO getById();
}
