package com.spotify.oauth2.api;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import com.spotify.oauth2.api.Route;


public class SpecBuilder {

	static RequestSpecification requestSpec;
	static ResponseSpecification responseSpec;
	//static String access_token=TokenManager.getToken();
	
	public static RequestSpecification getRequestSpec() {
		RequestSpecBuilder reqBuilder= new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath(Route.BASE_PATH)
               // .addHeader("Authorization","Bearer "+access_token)
               .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
   requestSpec = reqBuilder.build();
   return requestSpec;
	}
	
	public static ResponseSpecification getResponseSpec() {
		 ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder()
                // .expectContentType(ContentType.JSON)
                 .log(LogDetail.ALL);
   responseSpec = responseBuilder.build();
   return responseSpec;
	}
	
}
