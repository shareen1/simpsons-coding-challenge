package com.code.java.application.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.bean.MyCache;

public class CharacterServiceImpl {

	public static CharacterBean getCharacterById(String id) {
		CharacterBean ch = MyCache.getInstance().getBeanById(id);
		return ch;
	}

	public static Set<CharacterBean> findAllCharacter() {
		
		return MyCache.getInstance().getChlist();
	}

	public static CharacterBean addCharacter(CharacterBean characterBean) {
	
		 MyCache.getInstance().addToList(characterBean.getChId(), characterBean);
		return characterBean;
	}

}
