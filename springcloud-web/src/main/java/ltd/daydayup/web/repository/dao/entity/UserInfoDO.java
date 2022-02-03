package ltd.daydayup.web.repository.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @auther lipengcheng
 * @date 2021-12-10
 */
@Data
public class UserInfoDO {
    public Integer id;
    public String userName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date createTime;
}
