package com.feifei.spboot.learn.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Classname User
 * @Description TODO
 * @Date 2019/7/12 10:45
 * @Created by ChenS
 */
@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//格式化日期
    private Date createdate;

    @ManyToOne//定义与部门的多对一关系
    @JoinColumn(name = "did")//用字段did表明的id
    @JsonBackReference//防止对象的递归访问
    private Deparment deparment;

    @ManyToMany(cascade = {},fetch = FetchType.EAGER)//定义与角色的多对多关系
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id")})//用中间表存储各自的ID
    private List<Role> roles;

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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Deparment getDeparment() {
        return deparment;
    }

    public void setDeparment(Deparment deparment) {
        this.deparment = deparment;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
