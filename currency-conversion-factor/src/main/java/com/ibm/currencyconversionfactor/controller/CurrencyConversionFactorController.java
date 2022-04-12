package com.ibm.currencyconversionfactor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.currencyconversionfactor.beans.ConversionFactorDetail;
import com.ibm.currencyconversionfactor.beans.ResponseStatus;
import com.ibm.currencyconversionfactor.repository.ConversionFactorRepository;

import brave.sampler.Sampler;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RequestMapping("conversionFactor")
@RestController
public class CurrencyConversionFactorController {
	@Autowired
	ConversionFactorRepository repo;
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	
	
	@Autowired
	Environment environment;
	//http://localhost:8081/addConversionFactor?countrycode=1&conversionFactor=1.2
	@ResponseBody
	@PostMapping(path = "/add")
	public ResponseStatus addConversionFactor(@RequestParam int countryCode, Double conversionFactor) {
		
		ConversionFactorDetail conFactorDetail=new ConversionFactorDetail();
		conFactorDetail.setCountrycode(countryCode);
		conFactorDetail.setConversionFactor(conversionFactor);
		repo.save(conFactorDetail);
		
		ResponseStatus responseStatus=new ResponseStatus();
		
		responseStatus.setMessage("Added successfully");
		responseStatus.setStatus("SUCCESS");
		responseStatus.setPort(environment.getProperty("local.server.port"));
		System.out.println("responseStatus:"+responseStatus.toString());
		return responseStatus;
		
	}
	
	@ResponseBody
	@PostMapping(path = "/update")
	public ResponseStatus updateConversionFactor(@RequestParam int countryCode, Double conversionFactor) {
		ConversionFactorDetail conFactorDetail=new ConversionFactorDetail();
		conFactorDetail.setCountrycode(countryCode);
		conFactorDetail.setConversionFactor(conversionFactor);
		repo.save(conFactorDetail);
		ResponseStatus responseStatus=new ResponseStatus();
		responseStatus.setMessage("Updated successfully");
		responseStatus.setStatus("SUCCESS");
		return responseStatus;
		
	}
	
	//http://localhost:8082/convertCurrency?countryCode=11&amount=4
	@ResponseBody
	@GetMapping(path = "/get")
	public Double getConversionFactor(@RequestParam int countryCode) {
		ConversionFactorDetail detail=repo.findBycountrycode(countryCode);
		if(detail!=null) {
			return detail.getConversionFactor();
		}else {
			return 0.0;
		}
		
	}

	
	
}

