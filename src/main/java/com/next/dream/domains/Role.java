package com.next.dream.domains;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@Entity
public class Role implements Serializable{
        @Id
        @GeneratedValue
        private Integer id;
        private String role;
        private String description;
        private String resourceIds;
        private Integer available;
        public Integer getId() {
            return id;
        }

}
