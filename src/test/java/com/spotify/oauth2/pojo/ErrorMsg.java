package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "message"
})

public class ErrorMsg {

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("message")
    public String message;
    
    @JsonProperty("status")
    private Integer getStatus() {
    	return status;
    }
    
    @JsonProperty("status")
    private void setStatus(Integer status) {
    	this.status=status;
    }
    
    @JsonProperty("message")
    private String getMessage() {
    	return message;
    } 
    
    @JsonProperty("message")
    private void setMessage(String message) {
    	this.message= message;
    } 
    }

