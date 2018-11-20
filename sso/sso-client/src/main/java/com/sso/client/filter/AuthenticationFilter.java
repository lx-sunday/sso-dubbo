package com.sso.client.filter;


import com.sso.client.authc.AuthenticationConfig;

/**
 * 拦截器接口
 */
public interface AuthenticationFilter{

    AuthenticationConfig initAuthenticationConfig();

}
