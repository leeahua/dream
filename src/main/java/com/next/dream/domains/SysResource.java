package com.next.dream.domains;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

@Data
public class SysResource implements Serializable{
        // 
        private Integer id;
        // 
        private String name;
        // 
        private String type;
        // 
        private String url;
        // 
        private Integer parentId;
        // 
        private String parentIds;
        // 
        private String permission;
        // 
        private Integer available;
}
