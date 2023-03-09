package com.poonam.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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

	public ServerResponse getResponseForServer(String serverName) throws IOException {
		String url = "https://jhy.io";
		// TODO: Build URL using serverName

		Document doc = Jsoup.connect(url).get();

		return new ServerResponse(doc);
	}

	public SortedMap<String, ServerResponse> getResponseFromMultipleServers(SortedSet<String> serverNameList) {

		SortedMap<String, ServerResponse> sortedMapResponse = new TreeMap<>();

		for (String serverName : serverNameList) {
			// TODO: Build URL using serverName
			String url = "";
			try {
				Document doc = Jsoup.connect(url).get();
				sortedMapResponse.put(serverName, new ServerResponse(doc));
			} catch (IOException e) {
				System.err.println("Failed to get response from " + serverName);
			}
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
