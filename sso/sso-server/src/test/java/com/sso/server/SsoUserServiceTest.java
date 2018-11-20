package com.sso.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sso.interfaces.model.SsoUserVo;
import com.sso.interfaces.service.SsoUserService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={SsoApp.class})
public class SsoUserServiceTest {

	@Autowired
	private SsoUserService service;
	
	@Test
	public void getSsoUserByUserName(){
		
		SsoUserVo vo = this.service.getSsoUserByName("lx");
		
		System.out.println(vo==null?null:vo.toString());
	}
}
