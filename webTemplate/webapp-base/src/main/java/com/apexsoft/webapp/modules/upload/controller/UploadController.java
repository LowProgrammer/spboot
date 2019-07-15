package com.apexsoft.webapp.modules.upload.controller;

import com.alibaba.fastjson.JSONObject;
import com.apexsoft.webapp.modules.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2018/8/14.
 *
 * @author Sury
 */
@Controller
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        try {
            uploadService.upload(file.getOriginalFilename(),file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "demo/file";
    }
}
