package com.sso.client.filter;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sso.client.authc.AuthenticContextUtil;
import com.sso.client.authc.AuthenticationConfig;
import com.sso.client.support.CookiesUtil;
import com.sso.client.support.WebContext;
import com.sso.interfaces.model.SsoUserVo;

/**
 * 登录拦截器
 * @author lx
 *
 */
public class WebAuthenticationHandler extends DefAuthenticationFilter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		WebContext.registry(request, response);
		String currentUrl = request.getRequestURI();
		if(!isExcludeUrl(currentUrl)){
			 String token = CookiesUtil.getCookieValue(request, ssoAuthenticFactory.getAuthenticationConfig().getCookieName());
			 if(token!=null && token!=""){
				 SsoUserVo ssoUserVo=ssoAuthenticFactory.getAuthenticationCache().getSession(token);
			    if(ssoUserVo!=null){
			    	return true;
			    }
			 }
			 response.sendRedirect("/");
			 return false;
		}
		return true;
	}

	/**
	 * 判断拦截器是否排除当前访问路径
	 * @param currentUrl
	 * @return true 排除   false不排除
	 */
	private boolean isExcludeUrl(String currentUrl) {
		// TODO Auto-generated method stub
		AuthenticationConfig authenticationConfig = this.initAuthenticationConfig();
		String excludeUrls = authenticationConfig.getExcludeUrls();
		 Pattern[] exclude = null;
		if(!StringUtils.isEmpty(excludeUrls)){
			String[] split = excludeUrls.split(",");
			exclude=new Pattern[split.length];
			for(int i=0 ; i<split.length; i++){
				exclude[i]=Pattern.compile(split[i]);
			}
			if(exclude==null || exclude.length<=0) return false;
			for(Pattern excludeUrl :exclude){
				if(excludeUrl.matcher(currentUrl).find()) return true;
			}
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
