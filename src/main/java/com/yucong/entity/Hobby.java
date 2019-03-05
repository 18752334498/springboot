package com.yucong.entity;

import java.util.List;

public class Hobby {

	private int id;
	private String name;
	private String nickname;
	private List<Man> men;

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + nickname;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<Man> getMen() {
		return men;
	}

	public void setMen(List<Man> men) {
		this.men = men;
	}
}
