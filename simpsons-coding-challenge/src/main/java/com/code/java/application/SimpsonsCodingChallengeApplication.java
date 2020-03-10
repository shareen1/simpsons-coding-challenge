package com.code.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.code.java.application.bean.CharacterBean;
import com.code.java.json.JsonRW;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableCaching

@ComponentScan ({"com.code.java.application.bean", "com.code.java.applicationService","com.code.java.application"})
public class SimpsonsCodingChallengeApplication {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(CharacterBean.class);
		context.refresh();
		JsonRW.loadChacheOnStart();
		SpringApplication.run(SimpsonsCodingChallengeApplication.class, args);
	}

}
