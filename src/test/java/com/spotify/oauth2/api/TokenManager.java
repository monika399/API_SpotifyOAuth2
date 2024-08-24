package com.spotify.oauth2.api;

import java.time.Instant;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

import org.testng.annotations.Test;

import com.spotify.oauth2.utils.ConfigLoader;

import static io.restassured.RestAssured.given;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiry_time;
	
	//@Test - used to check whether it is genrating token or not, need to remove the return type for this
	public static String getToken() {
		try {
			if(access_token==null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing access token !!!!!!!!!!!!!");
				Response response=renewToken();
				access_token=response.path("access_token");
				System.out.println("token inside gettoken-----------------"+access_token);
				int expiryDurationInSeconds=response.path("expires_in");
				System.out.println("expiryDurationInSeconds "+expiryDurationInSeconds);
				expiry_time=Instant.now().plusSeconds(expiryDurationInSeconds-300);
			    System.out.println("expiry_time "+expiry_time);
			    
			}else {
				System.out.println("Token is good to use");
			}} 
		catch (Exception e) {
			throw new RuntimeErrorException(null, "ABORT!!! Failed to renew token");
		}
		return access_token;
	}
	
//@Test when i am changing the return type to void it is working and printing the access token in the console
private static Response renewToken() {
	
		HashMap<String, String>formParams=new HashMap<String, String>();
		formParams.put("grant_type", "refresh_token");
		formParams.put("refresh_token", "AQCH_qG4LI7im3QPjK4f882ikwZV27HEK897CIVAYL7JPDa4qD1X1PLiRs1O8vlhT3XbTUlUhRdaNXC1sm5SuZLfQhIzML0yaITUrNIEMYpGg_VAjIaeIQy-s7_3ioZo07E");
		formParams.put("client_id", "369351be8fa24da9b4b3fc56d96a0425");
		formParams.put("client_secret", "b7aa646e9909415ca802b164d5cfe38f");
		
		Response response_token=given()
		    .baseUri("https://accounts.spotify.com")
		    .contentType(ContentType.URLENC)
		    .formParams(formParams).log().all()
		   
		.when().post("/api/token")
		.then().spec(SpecBuilder.getResponseSpec())
		    .extract().response();
		
		if(response_token.statusCode()!=200) {
		throw new RuntimeErrorException(null, "ABORT!!! Renew token failed");
	}
		
		System.out.println("Path "+response_token.path("access_token"));
	return response_token;
		
	
	
}
@Test
private static void renewTokenWithProperties() {
	
	HashMap<String, String>formParams=new HashMap<String, String>();
	formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
	formParams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
	formParams.put("client_id",ConfigLoader.getInstance().getClientId());
	formParams.put("client_secret",ConfigLoader.getInstance().getClientSecret());
	
	Response response_token=given()
	    .baseUri("https://accounts.spotify.com")
	    .contentType(ContentType.URLENC)
	    .formParams(formParams).log().all()
	   
	.when().post("/api/token")
	.then().spec(SpecBuilder.getResponseSpec())
	    .extract().response();
	
	if(response_token.statusCode()!=200) {
	throw new RuntimeErrorException(null, "ABORT!!! Renew token failed");
}
	
	System.out.println("Path "+response_token.path("access_token"));
//return response_token;
	


}


}
