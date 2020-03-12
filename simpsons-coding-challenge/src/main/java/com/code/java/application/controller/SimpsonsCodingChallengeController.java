package com.code.java.application.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.service.CharacterService;
import com.code.java.application.service.ServiceResponse;

@ComponentScan(basePackageClasses = CharacterBean.class)
@RestController

public class SimpsonsCodingChallengeController {

	@Autowired
	CharacterService characterService;

	private int counter=0;
	
	 static String randomId;

	@RequestMapping(value = "/character", method = RequestMethod.GET, produces = { "application/json" })
	public CharacterBean findCharacterById(@RequestParam("id") String personId) {
		System.out.println("Searching by ID  : " + personId);
		return characterService.getCharacterById(personId);
	}

	@RequestMapping(value = "/characterlist", method = RequestMethod.GET, produces = { "application/json" })
	public Set<CharacterBean> findAllCharacter() {
		System.out.println("Searching all List ");
		return characterService.findAllCharacter();

	}

	@PostMapping("/postCharacter")
	public ResponseEntity<Object> postCharacter(@RequestBody CharacterBean characterBean) {
		System.out.println("Customer information saved successfully ::." + characterBean.getFirstName());
		characterBean.setCounter(counter);
		SecureRandom random = new SecureRandom();
		 randomId = new BigInteger(130, random).toString(32);
		if(null==characterBean.getChId() ) {
			characterBean.setChId(randomId);
			
		}
		CharacterBean savedCBean = characterService.addCharacter(characterBean);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCBean.getChId()).toUri();
		ServiceResponse<CharacterBean> svcs = new ServiceResponse<>("success", savedCBean);
        
		return new ResponseEntity<>(svcs, HttpStatus.OK);
	}

	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public ResponseEntity<Object> createImage(@RequestParam("image") MultipartFile image) {

		System.out.println("inside createImage..........");
		byte[] design =null ;
		try {
			design = image.getBytes();
           
			counter=counter+1;
			Path file = Files.write(Paths.get("img_"+counter+".jpg"), design);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("File Uploaded Successfully", HttpStatus.OK);
	}
}
