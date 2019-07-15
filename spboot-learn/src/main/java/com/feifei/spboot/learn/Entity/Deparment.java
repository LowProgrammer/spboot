package com.feifei.spboot.learn.Entity;

import javax.persistence.*;

/**
 * @Classname Deparment
 * @Description TODO
 * @Date 2019/7/12 10:35
 * @Created by ChenS
 */

@Entity
@Table(name = "deparment")//指定关联的数据库表名
public class Deparment {

    @Id//定义唯一标识
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动生成
    private Long id;
    private String name;

    public Deparment(){

    }
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
