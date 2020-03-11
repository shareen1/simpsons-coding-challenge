package com.code.java.application.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class MyCache {
	private static MyCache myCache = null; 
	  	public HashMap<String, CharacterBean> getCharacterList() {
		return characterList;
	}
	public void setCharacterList(HashMap<String, CharacterBean> characterList) {
		this.characterList = characterList;
	}
	public Set<CharacterBean> getChlist() {
		return chlist;
	}
	public void setChlist(Set<CharacterBean> chlist) {
		this.chlist = chlist;
	}
		public static MyCache getInstance() 
    { 
        if (myCache == null) 
        	myCache = new MyCache(); 
  
        return myCache; 
    } 
	public HashMap<String, CharacterBean> characterList = new HashMap<>();
	public Set<CharacterBean> chlist = new HashSet<CharacterBean>();

	public  void addToList(String string, CharacterBean bean) {

		characterList.put(string, bean);
		setAllChar();
	}
	public Set<CharacterBean> setAllChar() {
		
		for (Entry<String, CharacterBean> chbean : MyCache.getInstance().characterList.entrySet()) {
			CharacterBean myVal = chbean.getValue();
			chlist.add(myVal);
		}
		
		return chlist;
	}

	public void deleteCharacter(String id) {
		characterList.remove(id);
	}

	public void updateCharacter(String id, CharacterBean bean) {
		characterList.put(id, bean);
		setAllChar();

	}

	public  CharacterBean getBeanById(String id) {
		return characterList.get(id);

	}

	public String getIdByName(String firstName, String lastName) {
		CharacterBean bean;
		for (Entry<String, CharacterBean> ch : characterList.entrySet()) {
			bean = ch.getValue();
			String fname = bean.getFirstName();
			String lname = bean.getLastName();
			if (null != firstName && firstName.equals(fname)) {
				return ch.getKey();
			} else if (null != lastName && lastName.equals(lname)) {
				return ch.getKey();
			}

		}
		return null;

	}

	public Set<String> getCommentById(String id) {
		CharacterBean mybean = getBeanById(id);
		return mybean.getComments();

	}

	public void addCommentById(String id, String comment) {
		CharacterBean mybean = getBeanById(id);
		Set<String> comentlist = mybean.getComments();
		comentlist.add(comment);
		mybean.setComments(comentlist, id);
	}

}
