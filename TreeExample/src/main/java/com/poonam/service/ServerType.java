package com.poonam.service;

public enum ServerType {
	//TODO: Update jar names as expected
	CACHE_SERVER("cache-1.0.jar"), INTEGRATION_SERVER("int-1.1.jar"), RECONCILATION_SERVER("recon-0.7.jar");

	String jarNameInitial;
	
	ServerType(String jarNameInitial) {
		this.jarNameInitial = jarNameInitial;
	}
	
	public String getJarNameInitial() {
		return jarNameInitial;
	}
}
