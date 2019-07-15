package com.apexsoft.webapp.modules.oracle.mapper;


import com.apexsoft.webapp.modules.oracle.model.Demo2;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Demo2Mapper {

    public List<Demo2> getXtdm(@Param("fldm")String fldm) throws Exception;

    public void procTestFzmx(Map<String,Object> param) throws Exception;
}
