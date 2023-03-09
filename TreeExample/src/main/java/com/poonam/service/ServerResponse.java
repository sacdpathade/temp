package com.poonam.service;

import java.util.SortedSet;
import java.util.TreeSet;

public class ServerResponse {

	SortedSet<String> jarVersions;
	
	ServerResponse(String htmlResponse) {
		//Update jar versions using html response.
		this.jarVersions = new TreeSet<>();
	}
	
	public void updateResponseUsingHtmlResponse() {
		
	}

	public SortedSet<String> getJarVersions() {
		return jarVersions;
	}
}
