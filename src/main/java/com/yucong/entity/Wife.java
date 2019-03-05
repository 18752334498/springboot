package com.yucong.entity;

public class Wife {

	private int id;
	private String name;
	private int age;
	private int manId;

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getManId() {
		return manId;
	}

	public void setManId(int manId) {
		this.manId = manId;
	}

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

}
