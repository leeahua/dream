package com.next.dream.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Organization implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer parentId;
    private String parentIds;
    private Integer available;
}
