package com.poonam.service;

import java.io.IOException;
import java.util.SortedMap;
import java.util.SortedSet;

public class UxService {
	BackendService backendService;

	public UxService() {
		backendService = new BackendService();
	}

	public String getPage(String clickedNode) {

		String pageStr = null;
		// If server type clicked
		if (backendService.getServerTypeToName().containsKey(clickedNode)) {
			SortedSet<String> serverSet = backendService.getAllServerDetails().get(clickedNode);
			SortedMap<String, ServerResponse> serverResponse = backendService.getResponseFromMultipleServers(serverSet);
			
			// TODO: Build page for server type
		} else {
			// Server name clicked
			try {
				ServerResponse serverResponse = backendService.getResponseForServer(clickedNode);
			} catch (IOException e) {
				System.err.println("Failed to get response for " + clickedNode);
			}
			
			// TODO: Build page for single server
		}

		return pageStr;
	}
}
