package ltd.daydayup.common.enums;

/**
 * @author lipengcheng
 */
public interface EnumBase {
    /**
     * 获取枚举名(建议与enumCode保持一致)
     *
     */
    public String code();
    /**
     * 获取枚举消息
     *
     */
    public String msg();
}
