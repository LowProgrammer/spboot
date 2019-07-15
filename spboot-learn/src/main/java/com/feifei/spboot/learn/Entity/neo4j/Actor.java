package com.feifei.spboot.learn.Entity.neo4j;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @Classname Actor
 * @Description TODO
 * @Date 2019/7/12 18:40
 * @Created by ChenS
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)//防止数据查询时引发递归访问
@NodeEntity//节点实体
public class Actor {
    @GraphId Long id;
    private String name;
    private int born;

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

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }
}
