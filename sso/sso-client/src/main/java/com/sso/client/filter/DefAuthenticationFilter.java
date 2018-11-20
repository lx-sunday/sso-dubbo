package com.sso.client.filter;

import com.sso.client.authc.AuthenticContextUtil;
import com.sso.client.authc.AuthenticationConfig;
import com.sso.client.authc.SSOAuthenticFactory;

public class DefAuthenticationFilter implements AuthenticationFilter{

    protected AuthenticContextUtil authenticContextUtil= AuthenticContextUtil.getInstance();

    protected SSOAuthenticFactory ssoAuthenticFactory = SSOAuthenticFactory.getInstance();

    @Override
    public AuthenticationConfig initAuthenticationConfig() {
        AuthenticationConfig authenticationConfig = SSOAuthenticFactory.getInstance().getAuthenticationConfig();
        return authenticationConfig;
    }
}
