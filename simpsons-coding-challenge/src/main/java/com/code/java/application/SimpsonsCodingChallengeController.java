package com.code.java.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.java.application.bean.CharacterBean;
import com.code.java.applicationService.CharacterService;

@ComponentScan(basePackageClasses =CharacterBean.class)
@RestController
@Controller
public class SimpsonsCodingChallengeController {

	@Autowired
	CharacterService characterService;
	
	 @RequestMapping(value = "/character")   
	public CharacterBean findCharacterById(@RequestParam("id") String personId) {
		System.out.println("Searching by ID  : " + personId);
		return characterService.getCharacterById(personId);
	}
	
	 @RequestMapping(value = "/characterlist")
		public List<CharacterBean> findAllCharacter() {
			System.out.println("Searching all List " );		
			return characterService.findAllCharacter();

	}
	
}
