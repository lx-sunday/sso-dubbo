package com.sso.client.authc;

import java.util.UUID;

import javax.servlet.http.Cookie;

import com.sso.client.support.CookiesUtil;
import com.sso.client.support.WebContext;
import com.sso.interfaces.model.SsoUserVo;

public class AuthenticContextUtil {
	
	private static AuthenticContextUtil singleAuthenticContextUtil;
	
	
	public static AuthenticContextUtil getInstance(){
		if(singleAuthenticContextUtil==null){
			singleAuthenticContextUtil=new AuthenticContextUtil();
		}
		return singleAuthenticContextUtil;
	}
	
	public static SSOAuthenticFactory getSSOAuthenticFactory(){
		
		return SSOAuthenticFactory.getInstance();
	}
	
	
	public SsoUserVo login(SsoUserVo vo){
		SsoUserVo ssoUserVo =AuthenticContextUtil.getSSOAuthenticFactory().getSsoUserService().
		  getSsoUserByName(vo.getUsername());
		
		if(ssoUserVo!=null && ssoUserVo.getPassword().equals(vo.getPassword())){ 
			//在cookie中写入登录信息
			String token=UUID.randomUUID().toString();
			writeCookie(token);
			//在redis中写入登录信息
			AuthenticContextUtil.getSSOAuthenticFactory().getAuthenticationCache().putSession(token, ssoUserVo);
			
		}
		return ssoUserVo;
	}
	
	public void logout(){
		//清除redis中的登录信息
		String token = CookiesUtil.getCookieValue(WebContext.currentRequest(), AuthenticContextUtil.getSSOAuthenticFactory().getAuthenticationConfig().getCookieName());
	    AuthenticContextUtil.getSSOAuthenticFactory().getAuthenticationCache().delSession(token);
		//清除cookie中登录信息
		deleteCookie();
	}
	
	public SsoUserVo getCurrentSSoUserVo(){
		String token = CookiesUtil.getCookieValue(WebContext.currentRequest(), AuthenticContextUtil.getSSOAuthenticFactory().getAuthenticationConfig().getCookieName());
		return AuthenticContextUtil.getSSOAuthenticFactory().getAuthenticationCache().getSession(token);
	}
	
	private void writeCookie(String token) {
		// TODO Auto-generated method stub
		Cookie cookie=new Cookie(AuthenticContextUtil.getSSOAuthenticFactory().getAuthenticationConfig().getCookieName(), token);
		cookie.setPath("/");
		cookie.setDomain(AuthenticContextUtil.getSSOAuthenticFactory().getAuthenticationConfig().getCookieDomain());
		cookie.setMaxAge(2*60*60);
		CookiesUtil.writeCookie(WebContext.currentResponse(), cookie);
	}
	
	private void deleteCookie(){
		Cookie cookie=new Cookie(AuthenticContextUtil.getSSOAuthenticFactory().getAuthenticationConfig().getCookieName(),"");
		cookie.setPath("/");
		cookie.setDomain(AuthenticContextUtil.getSSOAuthenticFactory().getAuthenticationConfig().getCookieDomain());
		cookie.setMaxAge(0);
		CookiesUtil.writeCookie(WebContext.currentResponse(), cookie);
	}
}
