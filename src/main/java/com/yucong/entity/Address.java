package com.yucong.entity;

public class Address {

	private int id;
	private int manId;
	private String province;
	private String city;

	@Override
	public String toString() {
		return id + "\t" + manId + "\t" + province + "\t" + city;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
