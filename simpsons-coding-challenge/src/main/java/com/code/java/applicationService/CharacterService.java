package com.code.java.applicationService;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.code.java.application.bean.CharacterBean;

@Configuration
public class CharacterService {
	@Cacheable("characterbean")
	@Bean
	public List<CharacterBean> findAllCharacter(){
		return CharacterServiceImpl.findAllCharacter();
		
	}

	public CharacterBean getCharacterIdByName(){
		
		return null;
		
	}

	public CharacterBean getCharacterById(String id){
		
		return CharacterServiceImpl.getCharacterById(id);
		
	}
	
}
