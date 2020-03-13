package com.code.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.bean.MyCache;
import com.code.java.application.json.JsonRW;

@SpringBootApplication
@ComponentScan(basePackageClasses = {CharacterBean.class,MyCache.class})
@ComponentScan ({"com.code.java.application.bean", "com.code.java.application.service","com.code.java.application","com.code.java.application.controller","com.code.java.application.json"})
public class SimpsonsCodingChallengeApplication {

	public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

	public static void main(String[] args) {
		context.register(CharacterBean.class);
		context.register(MyCache.class);
		context.refresh();
		MyCache mcache=context.getBean(MyCache.class);
		CharacterBean chbean=context.getBean(CharacterBean.class);
		new JsonRW().loadChacheOnStart(mcache,chbean);
		SpringApplication.run(SimpsonsCodingChallengeApplication.class, args);
		//context.close();
	}

}
