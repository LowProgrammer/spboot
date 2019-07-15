package com.apexsoft.webapp.modules.mysql.controller;

import com.alibaba.fastjson.JSONObject;
import com.apexsoft.webapp.BusinessConfig;
import com.apexsoft.webapp.modules.mysql.dao.RedisDao;
import com.apexsoft.webapp.modules.mysql.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:27 2018/2/6
 * @MODIFIED BY:
 */
@Controller
@RefreshScope
public class JspDemoController {

    @Autowired
    private DemoService demoSvc;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private BusinessConfig bCfg;

    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, ModelMap model){
        return "demo/welcome";
    }

    //跳转到jsp页面的demo
    @RequestMapping("/jspTest")
    public String jspTest(HttpServletRequest request, ModelMap model) {

        JSONObject result;
        try{
            //查询数据库
            result = demoSvc.procTestFzmx();
        }catch(Exception e){
            result = new JSONObject();
            result.put("code",1);
            result.put("note","error:"+e.getMessage());
            e.printStackTrace();
        }
        result.put("project-name",bCfg.getProjectName());
        result.put("version",bCfg.getVersion());
        request.setAttribute("data",result.toJSONString());
        return "demo/mysql";

    }

    @RequestMapping("/jspAllApp")
    public String jspAllApp(HttpServletRequest request, ModelMap model) {
        JSONObject result;
        try{
            result = demoSvc.getAllApp();
        }catch(Exception e){
            result = new JSONObject();
            result.put("code",1);
            result.put("note","error:"+e.getMessage());
            e.printStackTrace();
        }
        request.setAttribute("data",result);
        return "demo/demo2";
    }
}
