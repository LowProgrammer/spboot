package com.apexsoft.webapp.modules.mysql.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apexsoft.webapp.modules.mysql.mapper.DemoMapper;
import com.apexsoft.webapp.modules.mysql.model.Demo;
import com.apexsoft.webapp.modules.oracle.mapper.Demo2Mapper;
import com.apexsoft.webapp.modules.oracle.model.CursorDemo;
import com.apexsoft.webapp.modules.oracle.model.Demo2;
import com.apexsoft.webapp.modules.impala.dao.ImpalaDao;
import com.apexsoft.webapp.modules.impala.model.ImpalaDemo;
import oracle.jdbc.driver.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DemoService {

    @Autowired
    private DemoMapper mysqlDao;

    @Autowired
    private Demo2Mapper oracleDao;

    @Autowired
    private ImpalaDao impalaDao;

    public JSONObject getAllApp() throws Exception {
        JSONArray records = new JSONArray();
        List<Demo> list = mysqlDao.getAll();
        for(Demo obj:list){
            JSONObject json = (JSONObject)JSON.toJSON(obj);
            records.add(json);
        }
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("note","success");
        result.put("records",records);
        return result;
    }

    public JSONObject getXtdm() throws Exception{
        List<Demo2> list = oracleDao.getXtdm("GT_FXCSNL");
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("note","success");
        result.put("object",JSON.toJSON(list));
        return result;
    }

    public JSONObject getOne(String appCode) throws Exception{
        Demo obj = mysqlDao.getOne(appCode);
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("note","success");
        result.put("object",JSON.toJSON(obj));
        return result;
    }

    public JSONObject impalaTest() throws Exception{
        List<ImpalaDemo> list = impalaDao.test();
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("note","success");
        result.put("object",JSON.toJSON(list));
        return result;
    }

    //存储过程调用demo
    public JSONObject procTestFzmx(){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("i_fzid", 1);
        params.put("o_result", OracleTypes.CURSOR);
        JSONObject result = new JSONObject();
        try{
            oracleDao.procTestFzmx(params);
            result.put("code", params.get("o_code"));
            result.put("note", "note:"+params.get("o_note"));
            List<CursorDemo> list = (List<CursorDemo>)params.get("o_result");
            if(list != null) {
                for (CursorDemo obj : list) {
                    System.out.println(JSON.toJSON(obj));
                }
            }else{
                result.put("flag", "o_result is null");
            }
            result.put("result", JSON.toJSON(list));
        }catch(Exception e){
            result.put("code", -1);
            result.put("note", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  事务回滚，注意多数据源时需设置 transactionManager，否则只有指定为primary的transactionManager事务才会有效
     * @param id
     * @return
     */
    @Transactional(value = "mainTransactionManager",rollbackFor = Exception.class)
    public JSONObject transactionalTest(int id){
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("note","success");
        mysqlDao.deleteLog(id);
        //下行代码执行sql时抛出异常，事务回滚
        //mysqlDao.deleteLogThrowException();
        //下面代码抛出空指针异常，事务回滚
        String nullStr = null;
        if(nullStr.equalsIgnoreCase("abc")){
            //...
        }
        return result;
    }

}
