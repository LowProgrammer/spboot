package com.feifei.spboot.learn.Entity.neo4j;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Movie
 * @Description TODO
 * @Date 2019/7/15 9:28
 * @Created by ChenS
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity
public class Movie {
    @GraphId Long id;
    String title;
    String year;
    String tagline;

    @Relationship(type = "ACTS_IN",direction = Relationship.INCOMING)//关系列表
    List<Role>  roles=new ArrayList<>();

    public Role addRole(Actor actor,String name){
        Role role=new Role(actor,this,name);
        this.roles.add(role);
        return role;
    }

}
