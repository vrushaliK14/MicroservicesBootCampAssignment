package com.ibm.currencyconversionfactor.beans;

import javax.persistence.*;

@Entity
@Table(name = "ConversionFactorDetail")
public class ConversionFactorDetail {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		int srno;
		
		@Column (name="countrycode")
		int countrycode;
		
		@Column (name="conversionFactor")
		double conversionFactor;
		
		public int getSrno() {
			return srno;
		}
		public void setSrno(int srno) {
			this.srno = srno;
		}
		public int getCountrycode() {
			return countrycode;
		}
		public void setCountrycode(int countrycode) {
			this.countrycode = countrycode;
		}
		public double getConversionFactor() {
			return conversionFactor;
		}
		public void setConversionFactor(double conversionFactor) {
			this.conversionFactor = conversionFactor;
		}
		
		@Override
		public String toString() {
			return "ConversionFactorDetail [srno=" + srno + ", countrycode=" + countrycode + ", conversionFactor="
					+ conversionFactor + "]";
		}
		
		
}
