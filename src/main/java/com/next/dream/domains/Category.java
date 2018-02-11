package com.next.dream.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Category implements Serializable{
        @Id
        @GeneratedValue
        private Integer id;
        // 类别名称
        private String name;
        // 父节点id
        private Integer parentId;
        // 父节点ids
        private String parentIds;
        // 状态（0 不可用，1可用）
        private Integer status;

}
