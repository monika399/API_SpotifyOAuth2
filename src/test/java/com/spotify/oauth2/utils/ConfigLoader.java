
package com.spotify.oauth2.utils;
import java.util.Properties;

//Singleton class
public class ConfigLoader {
	
	private final Properties properties;
	private static ConfigLoader configLoader;
	
	/*Created a private constructor So that new object of the 
	 * class cannot be created outside of the class (Singleton class)*/
	
	private ConfigLoader() {
		properties =PropertyUtils.propertyLoader(System.getProperty("user.dir")+"/src/test/resources/config.properties");
	}
	
public  static ConfigLoader getInstance() {
	/*Ensuring only single object of this class is created
	 * */
	if(configLoader==null) {
		configLoader = new ConfigLoader();
		}
	return configLoader;
}

public  String getClientId() {
	String prop=properties.getProperty("client_id");
	if(prop!=null) 
		return prop;
	else throw new RuntimeException("Property client_id is not specified in the config.properties file");
}
public  String getClientSecret() {
	String prop=properties.getProperty("client_secret");
	if(prop!=null) 
		return prop;
	else throw new RuntimeException("Property client_secret is not specified in the config.properties file");
}
public  String getRefreshToken() {
	String prop=properties.getProperty("refresh_token");
	if(prop!=null) 
		return prop;
	else throw new RuntimeException("Property refresh_token is not specified in the config.properties file");
}
public String getGrantType() {
	String prop=properties.getProperty("grant_type");
	if(prop!=null) 
		return prop;
	else throw new RuntimeException("Property grant_type is not specified in the config.properties file");
}






}