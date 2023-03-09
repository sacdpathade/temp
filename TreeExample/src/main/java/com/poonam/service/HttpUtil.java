package com.poonam.service;

import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

/**
 * Not being used for now.
 */
@Deprecated
public class HttpUtil {
	public static String executeGet(final String getUrl) {

		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(getUrl);

			// Execute and get the response.
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					return IOUtils.toString(instream, "UTF-8");
				} finally {
					IOUtils.closeQuietly(instream);
				}
			}
		} catch (Exception e) {
			System.err.println("Failed to execute GET url");
		}
		return null;
	}

}
