package com.code.java.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.java.application.bean.CharacterBean;
import com.code.java.applicationService.CharacterService;

@ComponentScan(basePackageClasses =CharacterBean.class)
@RestController
public class SimpsonsCodingChallengeController {
	//CharacterBean chbean = (CharacterBean) context.getBean(CharacterBean.class);
	@Autowired
	CharacterService characterService;
	/*@RequestMapping("/character/{id}")
	public String hello(@RequestParam(name="name",defaultValue="Ajish") String name)
	{
		return "hello ............."+name;
	}*/
	 @RequestMapping(value = "/character")   
	public CharacterBean findCharacterById(@RequestParam("id") String personId) {
		System.out.println("Searching by ID  : " + personId);
		return characterService.getCharacterById(personId);
	}
	

}
