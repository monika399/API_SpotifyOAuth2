package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestReource;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;

public class PlaylistApi {
static String token=TokenManager.getToken();
	public static Response post(Playlist requestPlayList) {
		/*
		 * Response response = given(SpecBuilder.getRequestSpec())
		 * .body(requestPlayList) .when().post("/users/        /playlists")
		 * .then().spec(SpecBuilder.getResponseSpec()) .extract().response(); return
		 * response;
		 */
		
		return given(SpecBuilder.getRequestSpec())
				.body(requestPlayList).header("Authorization","Bearer "+token)
				.when().post("/users/        /playlists")
				.then().spec(SpecBuilder.getResponseSpec()) .extract().response();
		
	}
	//Post method written in RestResource class
	public static Response postRR(Playlist requestPlayList) {
		return RestReource.post("/users/        /playlists", token, requestPlayList);
	}
	
	public Response get(String playlistId) {
		return
				given(SpecBuilder.getRequestSpec())
        .when().get("/playlists/"+playlistId)
        .then().spec(SpecBuilder.getResponseSpec())
         .extract()
         .response();
	}
	
	public Response update(Playlist requestPlayList,String playlistId) {
		return
				given(SpecBuilder.getRequestSpec())
		          .body(requestPlayList)
		       .when().put("/playlists/"+playlistId)
	           .then().spec(SpecBuilder.getResponseSpec())
	           .extract()
	           .response();
	}	              
	              
}

