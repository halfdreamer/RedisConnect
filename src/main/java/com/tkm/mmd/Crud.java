package com.tkm.mmd;

public interface Crud {

	boolean Create(Device d);

	Device Read(String UDID);

	boolean Delete(String UDID);

	boolean update(Device d);

	void connect();

}
