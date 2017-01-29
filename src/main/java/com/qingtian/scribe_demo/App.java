package com.qingtian.scribe_demo;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.github.scribejava.apis.GitHubApi;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuth20Service;
/**
 * Hello world!
 */
public class App 
{
	private static final String PROTECTED_RESOURCE_URL = "https://api.github.com/user";
    public static void main( String[] args ) throws IOException, InterruptedException, ExecutionException
    {
    	final OAuth20Service service = new ServiceBuilder()
                .apiKey("d1d43c80af61fd0ef528")
                .apiSecret("4e28c9589c60d04fcf39246e2e24883d0ddc1157")
                .callback("http://localhost:8080/oauth/callback")
                .build(GitHubApi.instance());
//    	final String url = service.getAuthorizationUrl();
//    	System.out.println(url);
    	String code="c06165578c414b1ef8ae";
    	OAuth2AccessToken accessToken = service.getAccessToken(code);
    	System.out.println(accessToken);
        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(accessToken, request);
        final Response response = service.execute(request);
        System.out.println("Got it! Lets see what we found...");
        System.out.println(response.getCode());
        System.out.println(response.getBody());
    }
}
