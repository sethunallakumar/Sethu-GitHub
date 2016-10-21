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
import org.apache.log4j.Logger;

public class ApiAICall {
	
	private static final String PROXY = "proxy.gbs.pre";
	private static final String USERNAME = "gbs03713";
	private static final String PASSWORD = "12345678";
	private static final Logger LOG = Logger.getLogger(ApiAICall.class);
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		new ApiAICall().getHttpResult(null);
	}
	
	public String getHttpResult(final String urlToRead){
		String result = "Failed";
		CloseableHttpClient httpClient = null;
		LOG.debug("Requested URL ["+urlToRead+"]");
		try{
			httpClient = getProxyedHttpClient();

			HttpPost post = new HttpPost("https://api.api.ai/v1/query");
			post.addHeader("Authorization",
					"Bearer 8e9cbdb0405f406ea35b06a04a5626e0");
			post.addHeader("Content-Type", "application/json");
			StringEntity input = new StringEntity(
					"{ \"query\": [ \"august to september month transaction\" ], \"contexts\": [{ \"name\": \"SellaItEngTest\", \"lifespan\": 4 }], \"lang\": \"en\", \"sessionId\": \"1234567890\"}"); // here
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
			LOG.debug("Requested URL Result "+result);
		}catch(final Exception exception){
			LOG.error(exception.getMessage(),exception);
		}finally{
			closeHttpClient(httpClient);
		}

		return result.toString();
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
				LOG.debug(exception.getMessage(),exception);
			}
		}
	}
}