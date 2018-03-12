package com.next.dream.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述：〈个人类别或者标签〉
 *
 * @author liyaohua
 * create on 2018/3/12
 * @version 1.0
 */
@Entity
@Data
public class Tags implements Serializable{
    private static final long serialVersionUID = 8928214405010572884L;
    @Id
    @GeneratedValue
    private Integer id;

    private Integer userId;

    private String tagName;

    private Integer status;

    private Date updateTime;
}
