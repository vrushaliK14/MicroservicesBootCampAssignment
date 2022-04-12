package com.ibm.currencyconversionfactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ibm.currencyconversionfactor.beans.ConversionFactorDetail;

@Repository
public interface ConversionFactorRepository extends JpaRepository<ConversionFactorDetail, Long> {
	ConversionFactorDetail findBycountrycode(int countrycode);
}