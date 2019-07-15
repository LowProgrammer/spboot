package com.feifei.spboot.learn.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Classname Role
 * @Description TODO
 * @Date 2019/7/12 11:03
 * @Created by ChenS
 */
@Entity
@Table(name = "user")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
