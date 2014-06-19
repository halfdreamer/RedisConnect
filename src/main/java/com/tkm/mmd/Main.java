package com.tkm.mmd;

import org.apache.log4j.Logger;

enum persistence {
	Redis, Cassandra, MongoDB;
}

class Client {
	private ConfigurationManager cm;
	private DbFactory factory;
	private persistence per;

	public Client() {
		cm = ConfigurationManager.get();
		per = persistence.valueOf(cm.getProperty("persistencetype"));
		switch (per) {
		case Redis:
			factory = new RedisdbFactory();
			break;
		default:
			break;
		}
		Crud op = factory.createInstance();
		Input in = new Input();
		in.fileinput(op);
	}
}

public class Main {

	static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		new Client();

	}
}
