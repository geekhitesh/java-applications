package com.daarks.rest.webservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="netflix-zuul-api-gateway-server")
//@FeignClient(name="currency-exchange-service")
//@FeignClient(name="currency-exchange-service",url="http://localhost:8000/")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	
	
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, 
			                                            @PathVariable("to") String to);

}
