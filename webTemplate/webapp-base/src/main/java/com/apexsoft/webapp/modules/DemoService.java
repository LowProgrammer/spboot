package com.apexsoft.webapp.modules;

import com.alibaba.fastjson.JSONObject;
import com.apexsoft.aas.common.BusinessApi;
import com.apexsoft.aas.common.JSONRequest;
import com.apexsoft.aas.common.JSONResponse;
import com.apexsoft.aas.common.condition.SwaggerDemoEnabledCondition;
import com.apexsoft.aas.common.swagger.ApiService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Conditional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/3/2.
 *
 * @author Sury
 */

public class DemoService {

    @ApiService(value = "demo1.add", tags = "示例", name = "用例", request = TestRequest.class, response = JSONResponse.class)
    @Conditional(SwaggerDemoEnabledCondition.class)
    public static class method extends BusinessApi<JSONRequest, JSONResponse> {

        @Override
        public JSONResponse exec(String func, String version, JSONObject jsonData, HttpServletRequest request, HttpServletResponse response) {
            return new JSONResponse(JSONResponse.CODE_SUCCESS, "测试");
        }
    }

    @ApiModel
    public static class TestRequest extends JSONRequest {
        @ApiModelProperty(position = 0, notes = "测试数据")
        @NotEmpty(message = "不能为空")
        private String test;

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }
    }

    @ApiModel
    public static class TestResponse extends JSONResponse {
        @ApiModelProperty(position = 0, notes = "测试数据")
        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

}
