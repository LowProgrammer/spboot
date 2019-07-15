package com.apexsoft.webapp.modules.mysql.mapper;

import com.apexsoft.webapp.modules.mysql.model.Demo;
import org.apache.ibatis.annotations.*;

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
    List<Demo> getAll() throws Exception;

    @Select("SELECT * FROM t_app WHERE app_code = #{appCode}")
    Demo getOne(@Param("appCode")String appCode) throws Exception;

    @Delete("delete from t_log where id=#{id}")
    void deleteLog(int id);

    @Delete("delete from t_log where id=asd")
    void deleteLogThrowException();
}
