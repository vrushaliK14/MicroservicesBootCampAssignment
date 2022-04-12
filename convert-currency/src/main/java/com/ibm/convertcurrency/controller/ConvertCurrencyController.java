package com.ibm.convertcurrency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.convertcurrency.services.CurrencyExchangeServiceProxy;

import brave.sampler.Sampler;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class ConvertCurrencyController {
	private Logger log=LoggerFactory.getLogger(ConvertCurrencyController.class);
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	
	@PostMapping(path = "/convertCurrency")
	@Retry(name = "default", fallbackMethod = "getDefaultConversionFactor")
	public double convertCurrency(@RequestParam int countryCode,double amount) {
		log.info("calling getConversionFactor API");
		double conversionFactor = proxy.getConversionFactor(countryCode);
		
		return conversionFactor*amount;
		
	}
	
	public double getDefaultConversionFactor(Exception E) {
		log.info("landed in getDefaultConversionFactor method");
		return 4.5;
	}
	
}
