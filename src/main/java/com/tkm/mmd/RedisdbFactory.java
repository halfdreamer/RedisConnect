package com.tkm.mmd;

public class RedisdbFactory implements DbFactory {

	public Crud createInstance() {

		return new CrudRedis();

	}

}
