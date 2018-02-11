package com.next.dream.domains;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable{
        // 主键
        private Integer id;
        // 角色名称
        private String role;
        // 描述
        private String description;
        // 对应的资源id
        private String resourceIds;
        private Integer available;
        public Integer getId() {
            return id;
        }

}
