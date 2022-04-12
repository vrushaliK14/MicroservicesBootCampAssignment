package com.ibm.convertcurrency.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name="currency-conversion-factor", url="localhost:8081")
@FeignClient(name="currency-conversion-factor")
public interface CurrencyExchangeServiceProxy {
	

	@GetMapping(path = "/conversionFactor/get")
	public Double getConversionFactor(@RequestParam("countryCode") int countryCode);
}
