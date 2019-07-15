package com.feifei.spboot.learn.Entity.neo4j;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @Classname Role
 * @Description TODO
 * @Date 2019/7/15 9:33
 * @Created by ChenS
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "ACTS_IN")
public class Role {
    @GraphId Long id;
    String role;
    @StartNode
    Actor actor;
    @EndNode
    Movie movie;
    public Role(){}

    public Role(Actor actor,Movie movie,String name){
        this.actor=actor;
        this.movie=movie;
        this.role=name;

    }
}
