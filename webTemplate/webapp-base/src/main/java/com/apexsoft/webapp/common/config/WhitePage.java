package com.apexsoft.webapp.common.config;

import com.apexsoft.aas.common.session.SessionWhitePage;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/5/11.
 */
@Component
public class WhitePage extends SessionWhitePage {
    //设置不用登录就可以访问的页面
    @Override
    protected void buildPage() {
        pageList.add("*");
    }

    //设置不用登录就可以访问的功能码
    @Override
    protected void buildFunc() {
        funcList.add("*");
    }
}
