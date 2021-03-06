package com.oa.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
public class OaApp {

	public static void main(String[] args){
		SpringApplication.run(OaApp.class, args);
	}
}
