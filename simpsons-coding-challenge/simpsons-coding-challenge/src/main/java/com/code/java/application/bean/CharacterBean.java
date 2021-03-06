package com.code.java.application.bean;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CharacterBean {
	private String Id;
	private String firstName;
	private String lastName;
	private String pictureURL;
	private Set<String> comments;
	Long age;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age2) {
		this.age = age2;
	}

	public Set<String> getComments() {
		return comments;
	}

	public void setComments(Set<String> comentlist,String id) {
		this.comments = comentlist;
	}

	

}
