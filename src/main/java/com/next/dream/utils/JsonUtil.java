package com.next.dream.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 *   工具类
 * @since  1.0.0
 * @author liyaohua
 * Create On 2018/1/23 下午5:54
 */
public class JsonUtil {

    /**
     *   <br>
     *  对象转成json
     * @param object
     * @return String
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午5:36
     */
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setDateFormat("yyyy-HH-dd HH:mm:ss");
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
    
    /**
     *   <br>
     *  json字符串转成相应的对象
     * @param jsonString
     * @return T
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午5:37
     */
    public static <T> T parseJson(String jsonString, Class<T> tagType){
        Gson gson = new Gson();
        T t = gson.fromJson(jsonString,tagType);
        return t;
    }
    
    /**
     *   <br>
     *  解析jsonArray
     * @return 
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午5:42
     */
    public static <T> List<T> parseJsonArray(String jsonArrayString){
        Gson gson = new Gson();

        List<T> list = gson.fromJson(jsonArrayString,new TypeToken<List<T>>(){}.getType());
        return list;
    }



}
