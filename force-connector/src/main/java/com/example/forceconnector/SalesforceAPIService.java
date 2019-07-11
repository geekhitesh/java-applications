package com.example.forceconnector;

import java.util.Properties;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


public class SalesforceAPIService {

	public AuthenticationResponse login(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		 
		params.add("username","geek.hitesh@gmail.com");
		params.add("password", "Amdocs2@GkuIq3tyBnC4w7p6yHWqwHrrg" );
		params.add("client_secret", "61577794F466E5EF5B34FA34106C6CAB8C0233DFF2357C95EFB0BC27353B5DCB");
		params.add("client_id", "3MVG9ZL0ppGP5UrBY3NEFSR7B_yukrbwi30BovUXajawB4JiWX8pd8pH6ju7yEO8SKDGtyLN5Y4fRXO_M0QWi");
		params.add("grant_type","password");
		
//		Properties props = System.getProperties();
//		props.put("http.proxyHost", "genproxy");
//		props.put("http.proxyPort", "8080");
		 
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		 
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity response = restTemplate.postForEntity("https://login.salesforce.com/services/oauth2/token", request, AuthenticationResponse.class);
	
		System.out.println(response);
			return (AuthenticationResponse) response.getBody();
		}
	
	
	
	public AccountResponse getAccountData(String accessToken, String instanceUrl){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + accessToken);
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		 
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity salesforceTestData = restTemplate.exchange(instanceUrl+ "/services/data/v22.0/query?q=select Id, Name from Account ", HttpMethod.GET, request, AccountResponse.class);
//		log.info(salesforceTestData.getBody().getRecords().get(0).getName());
		return (AccountResponse) salesforceTestData.getBody();
		}
	
}
