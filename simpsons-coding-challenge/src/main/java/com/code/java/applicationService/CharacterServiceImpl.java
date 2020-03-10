package com.code.java.applicationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.code.java.application.bean.CharacterBean;
import com.code.java.application.bean.MyCache;

public class CharacterServiceImpl {

	public static CharacterBean getCharacterById(String id) {
		CharacterBean ch =MyCache.getBeanById(id);
		return ch;
	}

	public static List<CharacterBean> findAllCharacter() {
		List<CharacterBean> chlist = new ArrayList<CharacterBean>();
		for (Entry<String, CharacterBean> chbean : MyCache.characterList.entrySet()) {
			CharacterBean myVal = chbean.getValue();
			chlist.add(myVal);
		}
		return chlist;
	}

}
