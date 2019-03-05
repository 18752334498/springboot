package com.yucong.entity;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class Man {

	private int id;
	private String name;
	private int age;
	private List<Address> addresses;
	private Wife wife;
	private List<Hobby> hobbies;

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + age + "\t" + addresses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Wife getWife() {
		return wife;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
	}

	public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

}
