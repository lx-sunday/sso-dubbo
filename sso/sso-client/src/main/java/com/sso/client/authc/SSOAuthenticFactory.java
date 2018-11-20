package com.sso.client.authc;

import org.springframework.beans.factory.InitializingBean;

import com.sso.interfaces.service.SsoUserService;

public class SSOAuthenticFactory implements InitializingBean{

	private static SSOAuthenticFactory singleSSOAuthenticFactory;
	
	private SsoUserService ssoUserService;
	
	private AuthenticationCache authenticationCache;
	
	private AuthenticationConfig authenticationConfig;
	
	public static SSOAuthenticFactory getInstance(){
		if(singleSSOAuthenticFactory==null){
			singleSSOAuthenticFactory=new SSOAuthenticFactory();
		}
		
		return singleSSOAuthenticFactory;
	}

	public SsoUserService getSsoUserService() {
		return ssoUserService;
	}

	public void setSsoUserService(SsoUserService ssoUserService) {
		this.ssoUserService = ssoUserService;
	}

	public AuthenticationCache getAuthenticationCache() {
		return authenticationCache;
	}

	public void setAuthenticationCache(AuthenticationCache authenticationCache) {
		this.authenticationCache = authenticationCache;
	}
	
	public AuthenticationConfig getAuthenticationConfig() {
		return authenticationConfig;
	}

	public void setAuthenticationConfig(AuthenticationConfig authenticationConfig) {
		this.authenticationConfig = authenticationConfig;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(singleSSOAuthenticFactory==null){
			singleSSOAuthenticFactory=new SSOAuthenticFactory();
		}
		singleSSOAuthenticFactory.setSsoUserService(ssoUserService);
		singleSSOAuthenticFactory.setAuthenticationCache(authenticationCache);
		singleSSOAuthenticFactory.setAuthenticationConfig(authenticationConfig);
	}
}
