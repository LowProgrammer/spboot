package com.apexsoft.webapp.common;

import com.apexsoft.aas.common.AuthResponse;
import com.apexsoft.aas.common.JSONResponse;
import com.apexsoft.aas.common.exception.AuthException;
import com.apexsoft.aas.common.session.UserSession;
import com.apexsoft.aas.modules.index.model.AuthData;
import com.apexsoft.aas.modules.index.model.AuthUser;
import com.apexsoft.aas.modules.index.service.IAuthService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/11.
 */
@Component
public class DemoAuthService extends IAuthService{
    @Override
    public AuthResponse auth(AuthData authData, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthException {
        AuthUser authUser = new AuthUser();
        authUser.setAccessToken(UserSession.getAccessToken());
        authUser.setClientId(authData.getClientId());
        return new AuthResponse(JSONResponse.CODE_SUCCESS, "这是一个测试的认证", authUser);

    }
}
