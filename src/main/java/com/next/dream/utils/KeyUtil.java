/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: KeyUtil
 * Author:   liyaohua
 * Date:     2018/1/26 上午9:33
 * Description: 生成code工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.next.dream.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 〈生成code工具类〉
 *
 * @author liyaohua
 * create 2018/1/26
 * @since 1.0.0
 */
public class KeyUtil {

    /**
     * @Description: <br>
     *  生成随机字符串
     * @param isDelFlag 是否删除横线
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/3/12 下午4:23
     */
    public static String  getUUID(boolean isDelFlag){
        String uuid = UUID.randomUUID().toString();
        if(isDelFlag) {
            uuid = uuid.replace("-", "");
        }
        return uuid;

    }
    
    /**
     *   <br>
     *  生成唯一主键
     * @return 
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/26 上午9:36
     */
    public static String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
    /**
     *   <生成六位注册码>
     *
     * @return String
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/2/24 下午3:40
     */
    public static String randomCode(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return String.valueOf(number);
    }
    
    
}
