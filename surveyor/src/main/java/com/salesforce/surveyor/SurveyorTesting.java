package com.salesforce.surveyor;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyorTesting {

    static final String USERNAME     = "geek.hitesh@gmail.com";
    static final String PASSWORD     = "Amdocs2@GkuIq3tyBnC4w7p6yHWqwHrrg";
    static final String LOGINURL     = "https://login.salesforce.com";
    static final String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
    static final String CLIENTID     = "3MVG9ZL0ppGP5UrBY3NEFSR7B_yukrbwi30BovUXajawB4JiWX8pd8pH6ju7yEO8SKDGtyLN5Y4fRXO_M0QWi";
    static final String CLIENTSECRET = "61577794F466E5EF5B34FA34106C6CAB8C0233DFF2357C95EFB0BC27353B5DCB";
	
	@GetMapping("/test")
	public String test() {
		

	    @SuppressWarnings("deprecation")
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
//		HttpClient httpclient = new HttpClient();

        // Assemble the login request URL
        String loginURL = LOGINURL + 
                          GRANTSERVICE + 
                          "&client_id=" + CLIENTID + 
                          "&client_secret=" + CLIENTSECRET +
                          "&username=" + USERNAME +
                          "&password=" + PASSWORD;

        // Login requests must be POSTs
        HttpPost httpPost = new HttpPost(loginURL);
        HttpResponse response = null;

        try {
            // Execute the login POST request
            response = httpclient.execute(httpPost);
        } catch (ClientProtocolException cpException) {
            // Handle protocol exception
        } catch (IOException ioException) {
            // Handle system IO exception
        }

        // verify response is HTTP OK
        final int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            System.out.println("Error authenticating to Force.com: "+statusCode);
            // Error is in EntityUtils.toString(response.getEntity()) 
            return "unauthorized";
        }

        String getResult = null;
        try {
            getResult = EntityUtils.toString(response.getEntity());
        } catch (IOException ioException) {
            // Handle system IO exception
        }
        JSONObject jsonObject = null;
        String loginAccessToken = null;
        String loginInstanceUrl = null;
        try {
            jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
            loginAccessToken = jsonObject.getString("access_token");
            loginInstanceUrl = jsonObject.getString("instance_url");
        } catch (JSONException jsonException) {
            // Handle JSON exception
        }
        System.out.println(response.getStatusLine());
        System.out.println("Successful login");
        System.out.println("  instance URL: "+loginInstanceUrl);
        System.out.println("  access token/session ID: "+loginAccessToken);

        // release connection
        httpPost.releaseConnection();
		
		return "hello";
	}
}
