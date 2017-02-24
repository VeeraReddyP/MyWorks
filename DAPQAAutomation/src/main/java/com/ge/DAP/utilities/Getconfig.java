package com.ge.DAP.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Getconfig {
	
	
	public static String get_properties(String key) {
	Properties p = new Properties();
	String FilePath = System.getProperty("user.dir")+"/src/main/resources/config.properties";
	FileInputStream fi=null;
	try {
		fi = new FileInputStream(FilePath);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		p.load(fi);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	return (String)p.getProperty(key);
}
}
