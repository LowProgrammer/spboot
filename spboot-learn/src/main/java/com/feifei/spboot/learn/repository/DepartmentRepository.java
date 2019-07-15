package com.feifei.spboot.learn.repository;

import com.feifei.spboot.learn.Entity.Deparment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Classname DepartmentRepository
 * @Description TODO
 * @Date 2019/7/12 14:40
 * @Created by ChenS
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Deparment,Long> {
}
