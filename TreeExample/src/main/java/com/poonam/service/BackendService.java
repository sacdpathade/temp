package com.poonam.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class BackendService {
	private static final String PROP_SEP = ",";
	Map<ServerType, SortedSet<String>> allServerDetails = new TreeMap<>();

	Map<String, ServerType> serverTypeToName = new HashMap<>();

	public BackendService() {
		Properties properties = loadApplicationProperties();

		for (ServerType serverType : ServerType.values()) {
			String propValue = properties.getProperty(serverType.toString(), "");
			String[] propSplits = propValue.split(PROP_SEP);

			if (propSplits.length > 0) {
				serverTypeToName.put(propSplits[0], serverType);
				if (propSplits.length > 1) {
					SortedSet<String> serverNameSet = new TreeSet<>();
					for (int i = 1; i < propSplits.length; i++) {
						serverNameSet.add(propSplits[i].trim());
					}
					allServerDetails.put(serverType, serverNameSet);
				}
			}
		}
	}

	private Properties loadApplicationProperties() {
		Properties properties = new Properties();
		try {
			properties
					.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			System.err.println("Failed to load application properties");
		}
		return properties;
	}

	public ServerResponse getResponseForServer(String serverName) {
		String url = "";
		// Build URL using server name

		String response = HttpUtil.executeGet(url);

		return new ServerResponse(response);
	}

	public SortedMap<String, ServerResponse> getResponseFromMultipleServers(SortedSet<String> serverNameList) {

		SortedMap<String, ServerResponse> sortedMapResponse = new TreeMap<>();

		for (String serverName : serverNameList) {
			String url = "";
			// Build URL using server name
			String response = HttpUtil.executeGet(url);
			sortedMapResponse.put(serverName, new ServerResponse(response));

		}

		return sortedMapResponse;
	}

	public Map<ServerType, SortedSet<String>> getAllServerDetails() {
		return allServerDetails;
	}

	public Map<String, ServerType> getServerTypeToName() {
		return serverTypeToName;
	}

	
}
