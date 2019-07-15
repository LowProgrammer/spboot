package com.apexsoft.amsserver.demo.controller;

import com.alibaba.fastjson.JSON;
import com.apexsoft.amsserver.BusinessConfig;
import com.apexsoft.amsserver.demo.service.AasDemoConsumer;
import com.apexsoft.amsserver.demo.service.EsbAmsConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:04 2018/3/28
 * @MODIFIED BY:
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private EsbAmsConsumer esbSvc;

    @Autowired
    private AasDemoConsumer aasSvc;

    @Autowired
    private BusinessConfig bCfg;

    @RequestMapping("/amsTest")
    public String amsTest() throws Exception{

        return esbSvc.doService().toJSONString();
    }

    @RequestMapping("/amsStreamTest")
    public String amsStreamTest() throws Exception{

        return esbSvc.doStreamService().toJSONString();
    }

    @RequestMapping("/configTest")
    public String configTest() throws Exception{

        return JSON.toJSON(bCfg).toString();
    }

    @RequestMapping("/aasAmsDemo")
    public String aasAmsDemo() throws Exception{

        return aasSvc.hello().toJSONString();
    }
}
