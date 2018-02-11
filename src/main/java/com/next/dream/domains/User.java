package com.next.dream.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class User implements Serializable{
        // 主键ID
        @Id
        @GeneratedValue
        private Integer id;
        // 用户名
        private String username;
        // 密码
        private String password;
        // 加密种子
        private String salt;
        // 角色
        private String roleIds;
        // 状态
        private Integer status;
        // 邮箱
        private String email;

}