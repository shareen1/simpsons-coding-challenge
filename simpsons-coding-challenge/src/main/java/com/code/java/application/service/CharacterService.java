package com.code.java.application.service;

import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.code.java.application.bean.CharacterBean;

@Configuration
public class CharacterService {
	@Cacheable("characterbean")
	@Bean
	public Set<CharacterBean> findAllCharacter(){
		return CharacterServiceImpl.findAllCharacter();
		
	}

	public CharacterBean getCharacterIdByName(){
		
		return null;
		
	}

	public CharacterBean getCharacterById(String id){
		
		return CharacterServiceImpl.getCharacterById(id);
		
	}
	
public CharacterBean addCharacter(CharacterBean characterBean){
		
		return CharacterServiceImpl.addCharacter(characterBean);
		
	}

public CharacterBean delete(CharacterBean characterBean) {
	return CharacterServiceImpl.deleteCharacter(characterBean);
	
}
}
