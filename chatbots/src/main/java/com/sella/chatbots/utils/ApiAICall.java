package com.sella.chatbots.utils;

import java.io.IOException;

import javax.naming.AuthenticationException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sella.chatbots.bo.ApiRequest;
import com.sella.chatbots.bo.ApiResponse;

public class ApiAICall {
	
	private static final String PROXY = "proxy.gbs.pre";
	private static final String USERNAME = "gbs03807";
	private static final String PASSWORD = "123456bB_";
	private static final String TOKEN = "Bearer 8e9cbdb0405f406ea35b06a04a5626e0";
	private static final String URL = "https://api.api.ai/v1/query";
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		new ApiAICall().getHttpResult("august to september month transaction");
	}
	
	public ApiResponse getHttpResult(final String query){
		String result = "Failed";
		CloseableHttpClient httpClient = null;
		try{
			httpClient = getProxyedHttpClient();

			HttpPost post = new HttpPost(URL);
			post.addHeader("Authorization",TOKEN);
			post.addHeader("Content-Type", "application/json");
			ObjectMapper mapper = new ObjectMapper();
			ApiRequest apiRequest = new ApiRequest();
			apiRequest.getQuery().add(query);
			String jsonInString = mapper.writeValueAsString(apiRequest);
			StringEntity input = new StringEntity(jsonInString);
																																																		// XML
			input.setContentType("application/json");
			post.setEntity(input);
			HttpResponse response = httpClient.execute(post);
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity());
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
				throw new AuthenticationException();
			} else {
				throw new IOException();
			}
		}catch(final Exception exception){
		}finally{
			closeHttpClient(httpClient);
		}
		System.out.println(result);
		ObjectMapper mapper = new ObjectMapper();
		ApiResponse obj = null;
		try {
			obj = mapper.readValue(result, ApiResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}

	private CloseableHttpClient getProxyedHttpClient(){
		final CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(PROXY, 8080), new UsernamePasswordCredentials(USERNAME, PASSWORD));
		final HttpHost myProxy = new HttpHost(PROXY, 8080);

		final RequestConfig requestBuilder = RequestConfig.custom()
				.setConnectTimeout(30 * 1000)
				.setConnectionRequestTimeout(30 * 1000)
				.setSocketTimeout(30 * 1000).build();

		final HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		clientBuilder.setProxy(myProxy).setDefaultRequestConfig(requestBuilder).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();

		return clientBuilder.build();

	}

	private void closeHttpClient(final CloseableHttpClient httpClient){
		if(httpClient != null){
			try {
				httpClient.close();
			} catch (final IOException exception) {
			}
		}
	}
}