package com.code.java.application.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.code.java.application.SimpsonsCodingChallengeApplication;
import com.code.java.application.bean.CharacterBean;
import com.code.java.application.bean.MyCache;
import com.code.java.application.service.CharacterService;
import com.code.java.application.service.ServiceResponse;

@ComponentScan(basePackageClasses = { CharacterBean.class, MyCache.class })
@RestController

public class SimpsonsCodingChallengeController {

	@Autowired
	CharacterService characterService;

	private int counter = 0;

	static String randomId;
	boolean isimageUploaded = false;

	private AnnotationConfigApplicationContext getContext() {
		return SimpsonsCodingChallengeApplication.context;

	}

	private MyCache getMycahe() {
		AnnotationConfigApplicationContext context = getContext();
		return context.getBean(MyCache.class);

	}

	@RequestMapping(value = "/character", method = RequestMethod.GET, produces = { "application/json" })
	public CharacterBean findCharacterById(@RequestParam("id") String personId) {
		System.out.println("Searching by ID  : " + personId);
		return characterService.getCharacterById(personId, getMycahe());
	}

	@RequestMapping(value = "/characterlist", method = RequestMethod.GET, produces = { "application/json" })
	public Set<CharacterBean> findAllCharacter() {
		System.out.println("Searching all List " + getMycahe().getChlist().size());

		// Set<CharacterBean> characters = characterService.findAllCharacter(
		// getMycahe());
		return getMycahe().getChlist();

	}

	@PostMapping("/postCharacter")
	public ResponseEntity<Object> postCharacter(@RequestBody CharacterBean characterBean) {
		System.out.println("Customer information saved successfully ::." + characterBean.getFirstName());
		characterBean.setCounter(counter);
		SecureRandom random = new SecureRandom();
		randomId = new BigInteger(130, random).toString(32);
		// if (null == characterBean.getChId()) {
		characterBean.setChId(randomId);
		characterBean.setImageUploaded(isimageUploaded);
		isimageUploaded = false;
		// }
		CharacterBean savedCBean = characterService.addCharacter(characterBean, getMycahe());
		ServiceResponse<CharacterBean> svcs = new ServiceResponse<>("success", savedCBean);

		return new ResponseEntity<>(svcs, HttpStatus.OK);
	}

	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public ResponseEntity<Object> createImage(@RequestParam("image") MultipartFile image) {

		System.out.println("inside createImage..........");
		byte[] design = null;
		try {
			design = image.getBytes();
			isimageUploaded = true;
			counter = counter + 1;
			Files.write(Paths.get("img_" + counter + ".jpg"), design);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("File Uploaded Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deleteChracter")
	public ResponseEntity<Object> deleteChacter(@RequestBody CharacterBean characterBean) {
		System.out.println("inside deleteChacter..........");
		getMycahe().deleteCharacter(characterBean.getChId());

		return new ResponseEntity<>("File Uploaded Successfully", HttpStatus.OK);
	}

	@PutMapping("/putChracter")
	public ResponseEntity<Object> putChracter(@RequestBody CharacterBean characterBean) {
		System.out.println("inside putChracter..........");
		getMycahe().updateCharacter(characterBean.getChId(), characterBean);
		ServiceResponse<CharacterBean> svcs = new ServiceResponse<>("success", characterBean);

		return new ResponseEntity<>(svcs, HttpStatus.OK);

	}
}
