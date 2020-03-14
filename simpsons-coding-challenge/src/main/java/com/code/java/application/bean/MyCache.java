package com.code.java.application.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.context.annotation.Configuration;

import com.code.java.application.json.JsonRW;

@Configuration
public class MyCache {
	
	public HashMap<String, CharacterBean> characterList = new HashMap<>();
	public Set<CharacterBean> chlist = new HashSet<CharacterBean>();

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

	public HashMap<String, CharacterBean> addToList(String string, CharacterBean bean, boolean isappstart,
			HashMap<String, CharacterBean> mylist) {
		int imageName;
		if(bean.isImageUploaded()) 
		{
			 imageName = bean.getCounter();
		}else {
			imageName=0;
		}

		if (null == bean.getPictureURL()) {
			bean.setPictureURL("http://localhost:8083/" + "img_" + imageName + ".jpg");
			try {
				JsonRW.copyImageFile("img_" + imageName + ".jpg", "img_" + imageName+ ".jpg");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		characterList.put(string, bean);
		// mylist.put(string, bean);

		setAllChar(isappstart);
		bean.setImageUploaded(false);
		return characterList;
	}

	public Set<CharacterBean> setAllChar(boolean isAppstart) {
		if (isAppstart != true) {
			chlist = new HashSet<CharacterBean>();
		}
		// setCharacterList(characterList);
		for (Entry<String, CharacterBean> chbean : characterList.entrySet()) {
			CharacterBean myVal = chbean.getValue();
			chlist.add(myVal);
		}
		System.out.println("size of chlist :" + chlist.size());
		// setChlist(chlist);
		return chlist;
	}

	public Set<CharacterBean> deleteCharacter(String id) {
		characterList.remove(id);
		setAllChar(false);
		return getChlist();
	}

	public void updateCharacter(String id, CharacterBean bean) {
		Set<String> comentlist = bean.getComments();
		Set<String> latestComment = new HashSet<>();
		for (String mycomment : comentlist) {
			latestComment.add(mycomment.replaceAll("\"", ""));
		}
		bean.setComments(latestComment, id);
		characterList.put(id, bean);

		setAllChar(false);

	}

	public CharacterBean getBeanById(String id) {
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
