package ltd.daydayup.web.repository.entity;

import java.util.Date;
import lombok.Data;

@Data
public class NoviceGuideUserInfoDO {
    /**
    * 主键Id
    */
    private Integer id;

    /**
    * uid
    */
    private String userid;

    /**
    * ????
    */
    private String usercode;

    /**
    * ???
    */
    private String qrcode;

    /**
    * ????
    */
    private Date createtime;
}