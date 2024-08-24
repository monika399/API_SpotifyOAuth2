package com.spotify.oauth2.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
	
	
	public static Properties propertyLoader(String filePath) {
		Properties properties=new Properties();
		BufferedReader reader = null;
		try {
			reader=new BufferedReader(new FileReader(filePath));
			properties.load(reader);
			reader.close();
			}
		catch(FileNotFoundException e1) {
			e1.printStackTrace();
			throw new RuntimeException("Properties file not fount at "+filePath);
		}
		catch(IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to load properties file "+filePath);
			}
		finally {
            if (reader != null) {
                try {
                    reader.close(); // Ensure the reader is closed in the finally block
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }}
		
		
		return properties;
	}
	
}