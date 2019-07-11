package com.example.forceconnector;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesforceAPIController {

	@RequestMapping("/home")
	public String getSalesforceObject() {
		return "home";
	}

	@RequestMapping("/account")
	public AccountResponse getSalesforceAccount() {
		SalesforceAPIService salesforceAPIService = new SalesforceAPIService();
		AuthenticationResponse authenticationResponse = salesforceAPIService.login();
		AccountResponse accountResponse = salesforceAPIService.getAccountData(authenticationResponse.getAccess_token(),
		authenticationResponse.getInstance_url());
		return accountResponse;
	}
}
