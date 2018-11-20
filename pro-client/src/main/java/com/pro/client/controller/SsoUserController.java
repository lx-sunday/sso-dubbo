package com.pro.client.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sso.client.authc.AuthenticContextUtil;
import com.sso.interfaces.model.SsoUserVo;
import com.sso.interfaces.service.SsoUserService;

@Controller
public class SsoUserController {
	
	@RequestMapping("/")
	public String goLoginPage(HttpServletRequest request, HttpServletResponse response){
		SsoUserVo ssoUserVo = AuthenticContextUtil.getInstance().getCurrentSSoUserVo();
		if(ssoUserVo!=null){
			return "redirect:/index";
		}else{
			return "login";
		}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(SsoUserVo vo,HttpServletRequest request,HttpServletResponse response){
		SsoUserVo ssoUserVo = AuthenticContextUtil.getInstance().login(vo);
		if(ssoUserVo==null || !ssoUserVo.getPassword().equals(vo.getPassword())){
			request.setAttribute("msg", "用户或者密码错误");
			return "login";
		}else{
			return "redirect:/index";
		}
	}
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request, HttpServletResponse response){
		SsoUserVo ssoUserVo = AuthenticContextUtil.getInstance().getCurrentSSoUserVo();
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		AuthenticContextUtil.getInstance().logout();
		return "redirect:/";
	}
}
