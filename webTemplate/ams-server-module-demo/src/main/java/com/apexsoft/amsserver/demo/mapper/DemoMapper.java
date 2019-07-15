package com.apexsoft.amsserver.demo.mapper;

import com.apexsoft.amsserver.demo.model.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:27 2018/2/6
 * @MODIFIED BY:
 */
@Mapper
public interface DemoMapper {

    @Select("select * from t_app")
    public List<Demo> getAll() throws Exception;

    @Select("SELECT * FROM t_app WHERE app_code = #{appCode}")
    public Demo getOne(@Param("appCode") String appCode) throws Exception;
}
