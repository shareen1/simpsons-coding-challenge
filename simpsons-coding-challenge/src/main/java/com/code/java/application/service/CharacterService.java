package com.code.java.application.service;

import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.bean.MyCache;

@Configuration
public class CharacterService {

	@Cacheable("characterbean")
	@Bean
	public Set<CharacterBean> findAllCharacter(MyCache myCache) {
		Set<CharacterBean> charSet = CharacterServiceImpl.findAllCharacter(myCache);
		return charSet;

	}
	
	public CharacterBean getCharacterIdByName() {
		return null;

	}

	public CharacterBean getCharacterById(String id, MyCache myCache) {
		return CharacterServiceImpl.getCharacterById(id, myCache);

	}

	public CharacterBean addCharacter(CharacterBean characterBean, MyCache myCache) {
		return CharacterServiceImpl.addCharacter(characterBean, myCache);

	}

	/*public CharacterBean delete() {
		CharacterBean charbean = CharacterServiceImpl.deleteCharacter(characterBean, myCache);
		return charbean;

	}*/
}
