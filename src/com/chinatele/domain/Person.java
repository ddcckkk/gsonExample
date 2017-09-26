package com.chinatele.domain;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

public class Person {
	@SerializedName(value = "name", alternate = {"Ãû×Ö","ÐÕÃû"})
	private String name;
	private int age;
	private String[] hobby;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", hobby=" + Arrays.toString(hobby) + "]";
	}
	
}
