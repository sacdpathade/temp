package com.poonam.service;

import java.util.SortedMap;
import java.util.SortedSet;

public class UxService {
	BackendService backendService;

	UxService() {
		backendService = new BackendService();
	}

	public String getPage(String clickedNode) {

		String pageStr = null;
		// If server type clicked
		if (backendService.getServerTypeToName().containsKey(clickedNode)) {
			SortedSet<String> serverSet = backendService.getAllServerDetails().get(clickedNode);
			SortedMap<String, ServerResponse> serverResponse = backendService.getResponseFromMultipleServers(serverSet);
			// Build page for server type
		} else {
			// Server name clicked
			ServerResponse serverResponse = backendService.getResponseForServer(clickedNode);
			// Build page for single server
		}

		return pageStr;
	}
}
