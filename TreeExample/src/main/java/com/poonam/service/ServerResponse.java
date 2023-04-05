package com.poonam.service;

import java.util.SortedSet;
import java.util.TreeSet;

import org.jsoup.nodes.Document;

public class ServerResponse {

	SortedSet<String> jarVersions;
	
	ServerResponse(Document doc) {
		
		
		//Update jar versions using html response.
		this.jarVersions = new TreeSet<>();
		
		//Add jar versions by using JSoup document
	}

	public SortedSet<String> getJarVersions() {
		return jarVersions;
	}
}
