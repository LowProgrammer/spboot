package com.apexsoft.webapp.modules.mysql.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apexsoft.webapp.BusinessConfig;
import com.apexsoft.webapp.modules.mysql.dao.RedisDao;
import com.apexsoft.webapp.modules.mysql.service.DemoService;
import com.apexsoft.aas.common.esb.ESB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:27 2018/2/6
 * @MODIFIED BY:
 */
@RestController
@RefreshScope
public class RestDemoController {

    @Autowired
    private DemoService demoSvc;

    @Autowired
    private RedisDao redisDao;

    @Autowired(required=false)
    private ESB esb;

    @Autowired
    private BusinessConfig bCfg;

    @Autowired
    private Environment env;

    @Value("${application.name:webapp-demo-progrem}")
    private String pname;

    @RequestMapping("/bCfg")
    public String bCfg(HttpServletRequest request, HttpServletResponse response) {
        return JSON.toJSON(bCfg).toString();
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        return JSON.toJSON(pname+":"+env.getProperty("application.name")).toString();
    }
    //http rest的demo
    @RequestMapping("/restAllApp")
    public String restAllApp(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        try{
            //查询数据库
            result = demoSvc.getAllApp();

            //spring Session(使用redis)
            request.getSession().setAttribute("userid","dinglei");
            request.getSession().getAttribute("userid");

            //redis操作demo
            redisDao.setKey("name","dinglei");
        }catch(Exception e){
            result = new JSONObject();
            result.put("code",-1);
            result.put("note",e.getMessage());
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    //http rest的demo
    @RequestMapping("/restXtdm")
    public String restXtdm(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        try{
            //查询数据库
            result = demoSvc.getXtdm();
        }catch(Exception e){
            result = new JSONObject();
            result.put("code",-1);
            result.put("note",e.getMessage());
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    //调用oracle过程demo
    @RequestMapping("/procTest")
    public String procTest(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        try{
            result = demoSvc.procTestFzmx();
        }catch(Exception e){
            result = new JSONObject();
            result.put("code",-1);
            result.put("note",e.getMessage());
            e.printStackTrace();
        }
        return result.toJSONString();
    }

    //调用esb的demo
    @RequestMapping("/esbTest")
    public String esbTest(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        try{
            if(esb!=null) {
                JSONObject in = new JSONObject();
                //esb普通服务调用
                in.put("serviceId", "esb.ygt.cxgj");
                in.put("gjdm", 156);
                result = esb.service(in);
            }else{
                result = new JSONObject();
                result.put("code",-1);
                result.put("note","esb.enabled is false");
            }
        }catch(Exception e){
            result = new JSONObject();
            result.put("code",-1);
            result.put("note",e.getMessage());
            e.printStackTrace();
        }
        return result.toJSONString();
    }

    //impala数据源调用demo
    @RequestMapping("/impalaTest")
    public String impalaTest(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        try{
            result = demoSvc.impalaTest();
        }catch(Exception e){
            result = new JSONObject();
            result.put("code",-1);
            result.put("note",e.getMessage());
            e.printStackTrace();
        }
        return result.toJSONString();
    }


    //事务
    @RequestMapping("/transactionalTest")
    public String transactionalTest(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        try{
            String id = request.getParameter("id");
            result = demoSvc.transactionalTest(Integer.parseInt(id));
        }catch(Exception e){
            result = new JSONObject();
            result.put("code",-1);
            if(null!=e.getMessage()){
                result.put("note",e.getMessage());
            }else{
                result.put("note",e.toString());
            }

            e.printStackTrace();
        }
        return result.toJSONString();
    }

}
