package com.tkm.mmd;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
public class CrudRedis implements Crud {

	private Jedis jedis;
	private Gson gs;
	private Device temp;

	public boolean Create(Device d) {

		try {

			jedis.hset("Device", d.getUdid(), gs.toJson(d));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Device Read(String UDID) {

		String tmp = null;
		// byte[] tmp = null;
		gs = new Gson();
		try {
			tmp = jedis.hget("Device", UDID);
			temp = gs.fromJson(tmp, Device.class);
		} catch (Exception e) {
		}
		System.out.print(temp.getUdid());
		return temp;

	}

	public boolean Delete(String UDID) {
		jedis.hdel("Device", UDID);
		return true;

	}

	public boolean update(Device d) {
		jedis.hset("Device", d.getUdid(), gs.toJson(d));
		return true;

	}

	public void connect() {

		if (jedis == null) {

			jedis = new Jedis("localhost");
			gs = new Gson();
			temp = new Device();
			jedis.select(0);
			jedis.connect();
		}

	}

}
