package com.feifei.spboot.learn.demo;

import com.feifei.spboot.learn.Entity.Deparment;
import com.feifei.spboot.learn.Entity.Role;
import com.feifei.spboot.learn.Entity.User;
import com.feifei.spboot.learn.config.JpaConfiguration;
import com.feifei.spboot.learn.repository.DepartmentRepository;
import com.feifei.spboot.learn.repository.RoleRepository;
import com.feifei.spboot.learn.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;


import java.util.Date;
import java.util.List;

/**
 * @Classname MysqlTest
 * @Description TODO
 * @Date 2019/7/12 14:12
 * @Created by ChenS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MysqlTest {
    private static Logger logger= LoggerFactory.getLogger(MysqlTest.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Before
    public void initData(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Deparment deparment=new Deparment();
        deparment.setName("开发部");
        departmentRepository.save(deparment);
        Assert.notNull(deparment.getId());

        Role role=new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId());

        User user=new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDeparment(deparment);

        List<Role> roles=roleRepository.findAll();
        Assert.notNull(roles);
        user.setRoles(roles);
        userRepository.save(user);
        Assert.notNull(user.getId());
    }

    @Test
    public void findPage(){
        Pageable pageable= new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));

        Page<User> page=userRepository.findAll(pageable);

        for (User user:page.getContent()){
            logger.info("======user==========user.name:{},department name:{},role name:{}",user.getName(),user.getDeparment(),user.getRoles().get(0).getName());
        }
    }


}
