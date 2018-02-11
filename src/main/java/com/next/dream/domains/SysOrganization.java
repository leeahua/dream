package com.next.dream.domains;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

@Data
public class SysOrganization implements Serializable {
    //
    private Integer id;
    //
    private String name;
    //
    private Integer parentId;
    //
    private String parentIds;
    //
    private Integer available;

}
