/*
 * FileName: JsonUtils.java
 * Author:   16031081
 * Date:     2016年5月21日 上午9:52:07
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.greatconan.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author 13073388
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils() {

    }

    /**
     * 功能描述: <br>
     * JSON串转换为对象
     * 
     * @param jsonStr JSON串
     * @return 对象实例，若JSON串为空或类型不匹配等异常，则返回为null
     */
    public static <T> T parseJsonStrToBean(String jsonStr, Class<T> t) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        try {
            return (T) JSONObject.parseObject(jsonStr, t);
        } catch (Exception e) {

            LOGGER.error("parseJsonStrToBean exception", e);
            return null;
        }
    }

    /**
     * 功能描述: <br>
     * 对象转换为JSON串。
     * 
     * @param o 实例对象
     * @return JSON串，若实例对象为null或转换异常，则返回null
     */
    public static String formatBeanToJsonStr(Object o) {
        if (o == null) {
            return null;
        }
        try {
            if (o.getClass().isArray()) {
                return JSONArray.toJSONString(o);
            }
            return JSONObject.toJSONString(o);
        } catch (Exception e) {
            LOGGER.error("formatBeanToJsonStr exception", e);
            return null;
        }
    }

    /**
     * 功能描述: <br>
     * JSON传转换成LIST
     * 
     * @param <T>
     * 
     * @param o 实例对象
     * @return JSON串，若实例对象为null或转换异常，则返回null
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> c) {
        if (StringUtils.isEmpty(jsonStr)) {
            return new ArrayList<T>();
        }

        return (List<T>) JSONArray.parseArray(jsonStr, c);
    }

}
