package com.next.dream.vo;
import com.next.dream.utils.KeyUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 自定义ResidSession解决集群会话共享
 * @author alone
 */
@SuppressWarnings("serial")
public class RedisSession implements Serializable {

    private String id;
    private SessionType type;
    private long createTime;
    private long destroyTime;
    private Map<String, Object> attrs;


    /**
     * 会话类型，不同类型的会话其有效期不同
     * @author alone
     */
    public enum SessionType {

        /**
         * 移动端，类型为1，会话有效期为一周
         */
        MOBILE(1, 1000 * 60 * 60 * 24 * 7),
        /**
         * 网页端，类型为2，会话有效期为半小时
         */
        BROWSER(2, 1000 * 60 * 30);

        private int type;
        private int value;

        private SessionType(int type, int value) {
            this.type = type;
            this.value = value;
        }

        public int getType() {
            return type;
        }

        public int getValue() {
            return value;
        }

        public static SessionType getSessionType(int type) {
            for (SessionType st : SessionType.values()) {
                if (st.type == type) {
                    return st;
                }
            }
            return null;
        }
    }

    public RedisSession(int sessionType) {
        this.id = KeyUtil.getUUID(true);
        this.type = SessionType.getSessionType(sessionType);
        this.createTime = System.currentTimeMillis();
        this.destroyTime = this.createTime + this.type.value;
        this.attrs = new HashMap<>();
    }

    public Object getAttribute(String key) {
        return attrs.get(key);
    }

    public void setAttribute(String key, Object value) {
        attrs.put(key, value);
        //将redis的处理放到外面
        //ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        //set后会将生存时间清零，需要重新设置有效期
        //operations.set(key, value, (this.getDestroyTime() - System.currentTimeMillis())/1000, TimeUnit.SECONDS);
    }

    public void removeAttribute(String key) {
        attrs.remove(key);
       // Cache cache = Redis.use();
       // cache.set(this.getId(), this);
    }

    public String getId() {
        return id;
    }

    public SessionType getType() {
        return type;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getDestroyTime() {
        return destroyTime;
    }

    public void setDestroyTime(long destroyTime) {
        this.destroyTime = destroyTime;
    }
}