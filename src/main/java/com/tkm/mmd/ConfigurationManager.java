package com.tkm.mmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class ConfigurationManager {

	private String configFilePath;
	private Properties properties = new Properties();
	private static ConfigurationManager cf;
	private File file;

	public static ConfigurationManager get() {
		if (cf == null) {
			try {
				cf = new ConfigurationManager();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cf;
	}

	private ConfigurationManager() throws IOException {
		this.configFilePath = configFilePath;
		InputStream fis = null;
		try {
			fis = ClassLoader.getSystemClassLoader().getResourceAsStream(
					"persistence.properties");
			properties.load(fis);
		} catch (FileNotFoundException ex) {
			setDefaults();
		} finally {
			if (fis != null)
				fis.close();

		}

	}

	private void setDefaults() {
		properties.put("host", "localhost");
		properties.put("port", "6379");
		properties.put("passs", "foobared");
		properties.put("persistencetype", "Redis");
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

}
