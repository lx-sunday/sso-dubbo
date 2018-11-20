package com.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:dubbo/*.xml"})
public class SsoApp {

	public static void main(String[] args){
		SpringApplication.run(SsoApp.class, args);
	}
}
