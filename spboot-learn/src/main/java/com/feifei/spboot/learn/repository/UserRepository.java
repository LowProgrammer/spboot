package com.feifei.spboot.learn.repository;

import com.feifei.spboot.learn.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Classname UserRepository
 * @Description TODO
 * @Date 2019/7/12 11:28
 * @Created by ChenS
 */
@Repository//定义为一个资源库
public interface UserRepository extends JpaRepository<User,Long> {

}
