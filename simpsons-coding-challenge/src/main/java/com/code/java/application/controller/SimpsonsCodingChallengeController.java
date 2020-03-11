package com.code.java.application.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.service.CharacterService;
import com.code.java.application.service.ServiceResponse;

@ComponentScan(basePackageClasses =CharacterBean.class)
@RestController

public class SimpsonsCodingChallengeController {

	@Autowired
	CharacterService characterService;
	
	 @RequestMapping(value = "/character", method = RequestMethod.GET, produces={ "application/json"})   
	public CharacterBean findCharacterById(@RequestParam("id") String personId) {
		System.out.println("Searching by ID  : " + personId);
		return characterService.getCharacterById(personId);
	}
	
	 @RequestMapping(value = "/characterlist", method = RequestMethod.GET, produces={ "application/json"})
		public Set<CharacterBean> findAllCharacter() {
			System.out.println("Searching all List " );		
			return characterService.findAllCharacter();

	}
	 @PostMapping("/postCharacter")
	    public ResponseEntity<Object>  postCharacter(@RequestBody CharacterBean characterBean) {
	       System.out.println( "Customer information saved successfully ::." +characterBean.getFirstName());
	       CharacterBean savedCBean = characterService.addCharacter(characterBean);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	   			.buildAndExpand(savedCBean.getChId()).toUri();
		ServiceResponse<CharacterBean> svcs=new ServiceResponse<>("success", savedCBean);

	   	return new ResponseEntity<>(svcs , 
	   	      HttpStatus.OK);
	    }
	 
	
}
