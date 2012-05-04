package com.facebooktrumps.client.entities;

import java.io.Serializable;

public class Trump implements Serializable {

	private static final long serialVersionUID = 466977742203679249L;
	
	private String 			personsName;
	private Integer			UID;
	private String 			photoUrl;
	private Integer 		age;
	private Integer			interests;
	private Integer 		wallcount;
	private Integer 		education;
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getEducation() {
		return education;
	}
	public void setEducation(Integer education) {
		this.education = education;
	}
	public Integer getWallcount() {
		return wallcount;
	}
	public void setWallcount(Integer wallcount) {
		this.wallcount = wallcount;
	}
	public Integer getInterests() {
		return interests;
	}
	public void setInterests(Integer interests) {
		this.interests = interests;
	}
	public String getPersonsName() {
		return personsName;
	}
	public void setPersonsName(String personsName) {
		this.personsName = personsName;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public Integer getUID() {
		return UID;
	}
	public void setUID(Integer uid) {
		UID = uid;
	}
	
}
