package com.yucong.redis;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class fff {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1");
		String string = jedis.hget("name", "zhao");
		Map<String, String> hgetAll = jedis.hgetAll("name");
		Boolean hexists = jedis.hexists("name", "qian");

		System.out.println(hexists);
		System.out.println(string);
		System.out.println(hgetAll);
		jedis.close();
	}
}
