package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	File file = new File("./config/config.ini");
	private FileInputStream fileInputStream;
	Properties properties = new Properties();
	private FileOutputStream fileOutputStream;

	public Config() {
		load();
	}

	private void load() {
		new File(file.getParent()).mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			fileInputStream = new FileInputStream(file);
			fileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getValue(String key, String defaultValue) {

		return properties.getProperty(key, defaultValue);

	}

	public void setValue(String key, String value) {
		properties.setProperty(key, value);
	}

	public void save() {
		try {
			properties.store(fileOutputStream, "≈‰÷√Œƒº˛");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
