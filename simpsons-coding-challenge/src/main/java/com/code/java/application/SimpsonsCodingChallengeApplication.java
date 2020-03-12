package com.code.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.bean.MyCache;
import com.code.java.application.json.JsonRW;

@SpringBootApplication
@ComponentScan ({"com.code.java.application.bean", "com.code.java.application.service","com.code.java.application","com.code.java.application.controller","com.code.java.application.json"})
public class SimpsonsCodingChallengeApplication {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(CharacterBean.class);
		context.register(MyCache.class);
		context.refresh();
		JsonRW.loadChacheOnStart();
		SpringApplication.run(SimpsonsCodingChallengeApplication.class, args);
		context.close();
	}

}
