package com.yucong.model;

public class Phone {

	private long id;
	private String name;
	private int amount;
	private int version;

	public Phone() {
	}

	public Phone(long id, String name, int amount, int version) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.version = version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + amount + "\t" + version;
	}

}
