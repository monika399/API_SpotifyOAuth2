package com.spotify.oauth2.tests;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import com.spotify.oauth2.pojo.ErrorMsg;
import com.spotify.oauth2.pojo.Playlist;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.api.Route;

public class PlayListTests {

	
    @Test
	public void createPlaylistPayLoadAsString() {
		//********Payload as a String ********************
		String payload="{\n" +
	            " \"name\" : \"Monika New\", \n" +
	            " \"description\" : \"Monika New\", \n" +
	            " \"public\" :  false\n" +
				 "}";
		given(SpecBuilder.getRequestSpec())
	          .body(payload)
	          .when().post("/users/31mtrcdt2ullgnhf3x2ok7533xwe/playlists")
              .then().spec(SpecBuilder.getResponseSpec())
              .assertThat()
              .statusCode(201)
              .body("name", equalTo("Monika New"));
	}
    @Test
	public void createPlaylistPayLoadAsPOJO() {
		
		//********Payload as a POJO ********************
		Playlist reqPlaylist=new Playlist();
		reqPlaylist.setName("New Playlist");
		reqPlaylist.setDescription("Creating new Playlist using POJO class to pass JSON");
		reqPlaylist.setPublic(false);
	
		Playlist responsePlaylist= given()
	          .body(reqPlaylist)
	          .when().post("/users/31mtrcdt2ullgnhf3x2ok7533xwe/playlists")
              .then().spec(SpecBuilder.getResponseSpec())
              .assertThat()
              .statusCode(201)
              .extract()
              .as(Playlist.class);
		
		assertThat(responsePlaylist.getName(), equalTo(reqPlaylist.getName()));
		assertThat(responsePlaylist.getDescription(), equalTo(reqPlaylist.getDescription()));
		assertThat(responsePlaylist.getPublic(), equalTo(reqPlaylist.getPublic()));

	}
	
	
	
	@Test
	public void getPlaylist() {
		given(SpecBuilder.getRequestSpec())
	          .when().get(Route.PLAYLISTS+"/1N1Pmu4FE8gUR1GdINVOiU")
              .then().spec(SpecBuilder.getResponseSpec())
              .assertThat()
              .statusCode(200);
	}
	
	 @Test
	public void updatePlaylist() { //to do
		String payload="{\n" +
	            " \"name\" : \"Monika New\", \n" +
	            " \"description\" : \"Monika New\", \n" +
	            " \"public\" :  false\n" +
				 "}";
	
		given(SpecBuilder.getRequestSpec())
	          .body(payload)
	          .when().post("/users/31mtrcdt2ullgnhf3x2ok7533xwe/playlists")
              .then().spec(SpecBuilder.getResponseSpec())
              .assertThat()
              .statusCode(201)
              .body("name", equalTo("Monika New"));
	}
	
	 @Test
	public void ShouldNotAbleToCreatePlaylistWithName() {
		Playlist requestPlaylist=new Playlist();
		requestPlaylist.setName(" ");
		requestPlaylist.setDescription("");
		requestPlaylist.setPublic(false);
		
		ErrorMsg errorMsg=given(requestSpecification)
				.body(requestPlaylist)
				.when().post("/users/dsfdsf/playlists")
				.then().spec(responseSpecification)
				.assertThat()
				.statusCode(400)
				.extract()
				.as(ErrorMsg.class);
	}
				
				
	//assertThat(errorMsg.getErrorMsg().getStatus,equalto(400));
	  //body("error.status",equalto(400), "error.message",equalTo("Missing required field: name"));
				
				
		      @Test
				public void createPlaylistPayLoadEnhanced() {
					
					/* Here we are using our customised post method written in PlayListApi class
					 * and Playlist pojo class
					 */
					Playlist reqPlaylist=new Playlist();
					reqPlaylist.setName("New Playlist");
					reqPlaylist.setDescription("");
					reqPlaylist.setPublic(false);
				
					Response response=PlaylistApi.post(reqPlaylist);
					assertThat(response.statusCode(),equalTo(201));
					
					Playlist responsePlaylist=response.as(Playlist.class);
					assertThat(responsePlaylist.getName(), equalTo(reqPlaylist.getName()));
					assertThat(responsePlaylist.getDescription(), equalTo(reqPlaylist.getDescription()));
					assertThat(responsePlaylist.getPublic(), equalTo(reqPlaylist.getPublic()));

				}	
//variation 4 with RestResource comman API customize class
 @Test
public void createPlaylistPayLoadEnhancedRR() {
					
					/* Here we are using our customised post method of Rest Resource class itself
					 * and Playlist pojo class
					 */
					Playlist reqPlaylist=new Playlist();
					reqPlaylist.setName("New Playlist");
					reqPlaylist.setDescription("");
					reqPlaylist.setPublic(false);
				
					Response response=PlaylistApi.post(reqPlaylist);
					assertThat(response.statusCode(),equalTo(201));
					
					Playlist responsePlaylist=response.as(Playlist.class);
					assertThat(responsePlaylist.getName(), equalTo(reqPlaylist.getName()));
					assertThat(responsePlaylist.getDescription(), equalTo(reqPlaylist.getDescription()));
					assertThat(responsePlaylist.getPublic(), equalTo(reqPlaylist.getPublic()));

}}













