package com.next.dream.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Resource implements Serializable{
        @Id
        @GeneratedValue
        private Integer id;
        private String name;
        private String type;
        private String url;
        private Integer parentId;
        private String parentIds;
        private String permission;
        private Integer available;
}
