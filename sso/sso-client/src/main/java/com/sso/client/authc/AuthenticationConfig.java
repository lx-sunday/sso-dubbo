package com.sso.client.authc;

public class AuthenticationConfig {

	/**
	 * 登录url
	 */
	private String loginUrl;
	/**
	 * 拦截排除url
	 */
	private String excludeUrls;
	/**
	 * cookie名称
	 */
	private String cookieName;
	/**
	 * cookie二级域名
	 */
	private String cookieDomain;

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(String excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	public String getCookieDomain() {
		return cookieDomain;
	}

	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}
	
}
