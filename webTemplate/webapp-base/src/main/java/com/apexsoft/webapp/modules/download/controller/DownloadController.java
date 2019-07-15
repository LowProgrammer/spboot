package com.apexsoft.webapp.modules.download.controller;

import com.alibaba.fastjson.JSONObject;
import com.apexsoft.aas.common.AuthResponse;
import com.apexsoft.webapp.modules.download.service.DownloadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/8/10.
 *
 * @author Sury
 */
@Controller
public class DownloadController {
    @Autowired
    private DownloadService downloadService;
    @ApiOperation(value = "下载文件入口页")
    @RequestMapping("/file")
    public String file(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "demo/file";
    }

    @RequestMapping("/download")
    public String download(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String fileId = request.getParameter("fileId");
        JSONObject params = new JSONObject();
        params.put("fileId",fileId);
        downloadService.download(params,response);
        return "";
    }
}
