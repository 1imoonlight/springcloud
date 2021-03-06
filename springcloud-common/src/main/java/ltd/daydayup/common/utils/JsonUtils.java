package ltd.daydayup.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author lipengcheng
 * @date 2022/2/9 09:44
 */
@Slf4j
public class JsonUtils {
    private JsonUtils() {
    }

    /***
     *  字符串转对象
     *
     * @param jsonString
     * @param t
     * @param <T>
     * @return
     */
    @SneakyThrows
    public static <T> T toBean(String jsonString, Class<T> t) {
        if (Strings.isNullOrEmpty(jsonString)) {
            return null;
        }
        return JSONObject.parseObject(jsonString, t);
    }

    /***
     *  字符串转Bean List
     *
     * @param jsonArrayString
     * @param t
     * @param <T>
     * @return
     */
    @SneakyThrows
    public static <T> List<T> toBeanList(String jsonArrayString, Class<T> t) {
        if (Strings.isNullOrEmpty(jsonArrayString)) {
            return Collections.emptyList();
        }
        return JSONObject.parseArray(jsonArrayString, t);
    }

    /**
     * @param list
     * @param clzz
     * @return java.util.List<T>
     * @author lipengcheng
     * @date 2021-11-30 10:55
     */
    public static <E, T> List<T> copyList(List<E> list, Class<T> clzz) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        return JSONObject.parseArray(JSON.toJSONString(list), clzz);
    }

    /**
     * @param jsonStr
     * @param propertyName
     * @return java.lang.String
     * @author lipengcheng
     * @date 2021-11-30 10:55
     */
    public static String getValue(String jsonStr, String propertyName) {
        String value = "";
        if (Strings.isNullOrEmpty(jsonStr)) {
            return null;
        }
        JSONObject obj = JsonUtils.toBean(jsonStr, JSONObject.class);
        if (obj != null) {
            value = obj.getString(propertyName);
        }
        return value;
    }
}

