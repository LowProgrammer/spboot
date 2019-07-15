package com.feifei.spboot.learn.repository;

import com.feifei.spboot.learn.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Classname RoleRepository
 * @Description TODO
 * @Date 2019/7/12 14:41
 * @Created by ChenS
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
