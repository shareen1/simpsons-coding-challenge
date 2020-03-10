package com.code.java.application.bean;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MyCache {
	public static HashMap<String, CharacterBean> characterList = new HashMap<>();

	public static void addToList(String string, CharacterBean bean) {

		characterList.put(string, bean);
	}

	public void deleteCharacter(String id) {
		characterList.remove(id);
	}

	public void updateCharacter(String id, CharacterBean bean) {
		characterList.put(id, bean);

	}

	public static CharacterBean getBeanById(String id) {
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
		;

	}

}
