package com.spotify.oauth2.api;

import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.pojo.Playlist;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;

public class RestReource {

	static String token=TokenManager.getToken();
	
	public static Response post(String path,String token,Object requestPlayList) {
		 return given(SpecBuilder.getRequestSpec())
				.body(requestPlayList).header("Authorization","Bearer "+token)
				.when().post(path)
				.then().spec(SpecBuilder.getResponseSpec()) .extract().response();
		
	}
	
	public Response get(String path,String token) {
		return
				given(SpecBuilder.getRequestSpec()).header("Authorization","Bearer "+token)
        .when().get(path)
        .then().spec(SpecBuilder.getResponseSpec())
         .extract()
         .response();
	}
	
	public Response update(String path,String token,Object requestPlayList) {
		return
				given(SpecBuilder.getRequestSpec())
		          .body(requestPlayList).header("Authorization","Bearer "+token)
		       .when().put(path)
	           .then().spec(SpecBuilder.getResponseSpec())
	           .extract()
	           .response();
	}	              
	              
}

