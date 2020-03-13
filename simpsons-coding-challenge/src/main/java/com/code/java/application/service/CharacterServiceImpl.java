package com.code.java.application.service;

import java.util.HashMap;
import java.util.Set;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.bean.MyCache;

public class CharacterServiceImpl {

	private static HashMap<String, CharacterBean> myList= new HashMap<>();;

	public static CharacterBean getCharacterById(String id, MyCache mycahe) {

		CharacterBean ch = mycahe.getBeanById(id);
		return ch;
	}

	public static Set<CharacterBean> findAllCharacter(MyCache mycahe) {
		mycahe.setAllChar(false);
		return mycahe.getChlist();
	}

	public static CharacterBean addCharacter(CharacterBean characterBean, MyCache mycahe) {

		mycahe.addToList(characterBean.getChId(), characterBean,false,myList);
		return characterBean;
	}

	/*public static CharacterBean deleteCharacter(CharacterBean characterBean) {
		deleteCharacter(characterBean.getChId());
		return characterBean;
	}*/
}
